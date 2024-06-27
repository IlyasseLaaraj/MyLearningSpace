package com.advancia.api.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.advancia.exceptions.InvalidAddException;
import com.advancia.exceptions.ValidationExceptionLogic;
import com.advancia.models.dto.kebabComponents.AllergenDto;
import com.advancia.models.kebabComponents.Allergen;
import com.advancia.utilities.Services.AllergenManager;

@Path("/allergens")
public class AllergenServices {

	AllergenManager allergenManager = new AllergenManager();
	private ValidationExceptionLogic vel = new ValidationExceptionLogic();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllAllergens_JsonOrXml() {
		try {
			List<AllergenDto> listOfAllergens = allergenManager.fetchAllAllergens();

			return Response.ok().entity(listOfAllergens).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while fetching all allergens");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching all allergens").build();
		}
	}

	// URI: http://localhost:8083/KebabHouseAdvancia/rest/allergens/id/{id}
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {
		try {
			Allergen allergen = allergenManager.fetchAllergenById(id);
			AllergenDto allergenDto = new AllergenDto(allergen);
			return Response.ok().entity(allergenDto).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No allergen found with ID " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/name/Soia e derivati
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {
		try {
			AllergenDto allergen = new AllergenDto(allergenManager.fetchAllergenByName(name));
			return Response.ok().entity(allergen).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No allergen found with name: " + name).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching the allergen with name: " + name).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/add
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response create(AllergenDto allergen) {
		try {
			String validationMessage = vel.validateAllergenDto(allergen);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}

			allergenManager.addAllergen(allergen);

			return Response.status(Status.CREATED)
					.entity("allergen " + allergen.getAllergenName() + " has been created succesfully.").build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("an allergen with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the allergen.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while creating the allergen.").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("id") int id) {
		try {
			allergenManager.deleteAllergen(id);

			return Response.status(Status.NO_CONTENT)
					.entity("allergen with id: " + id + " has been deleted succesfully.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("couldn't find an allergen with this id: " + id)
					.build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(@PathParam("id") int id, AllergenDto allergen) {
		try {
			String validationMessage = vel.validateAllergenDto(allergen);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}

			allergen.setAllergenId(id);

			allergenManager.updateAllergen(allergen);

			return Response.status(Status.OK)
					.entity("Allergen with id: " + allergen.getAllergenId() + " has been succesfully updated.").build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("an allergen with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while updating the allergen.").build();
			}
		} catch(NullPointerException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("couldn't find an allergen with this id: " + id)
					.build();
		}catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while updating the allergen.").build();
		}
	}
}
