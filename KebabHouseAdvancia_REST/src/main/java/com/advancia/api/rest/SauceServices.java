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
import com.advancia.models.dto.kebabComponents.SauceDto;
import com.advancia.utilities.Services.SauceManager;

@Path("/sauces")
public class SauceServices {
	SauceManager sauceManager = new SauceManager();
	private ValidationExceptionLogic vel = new ValidationExceptionLogic();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getAllSauces_JsonOrXml() {
		try {
			List<SauceDto> listOfSauces = sauceManager.fetchAllSauces();

			return Response.ok().entity(listOfSauces).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while fetching all bases");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching all sauces").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces/id/4
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {
		try {
			SauceDto sauce = new SauceDto(sauceManager.fetchSauceById(id));

			return Response.ok().entity(sauce).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No sauce found with ID " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces/name/Maionese
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {
		try {
			SauceDto sauce = new SauceDto(sauceManager.fetchSauceByName(name));

			return Response.ok().entity(sauce).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No sauce found with name: " + name).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching the sauce with name: " + name).build();
		}
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response create(SauceDto sauce) {
		try {
			String validationMessage = vel.validateSauceDto(sauce);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			sauceManager.addSauce(sauce);

			return Response.status(Status.CREATED)
					.entity("sauce " + sauce.getSauceName() + " has been created succesfully.").build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a sauce with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the sauce.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while creating the sauce.").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response delete(@PathParam("id") int id) {
		try {
			sauceManager.deleteSauce(id);

			return Response.status(Status.NO_CONTENT).entity("sauce with id: " + id + " hase been succesfully deleted.")
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("couldn't find a sauce with this id: " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response update(@PathParam("id") int id, SauceDto sauce) {
		try {
			String validationMessage = vel.validateSauceDto(sauce);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			sauce.setSauceId(id);

			sauceManager.updateSauce(sauce);

			return Response.status(Status.OK).entity("sauce with id: " + id + " has been succesfully updated.").build();

		} catch(NullPointerException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("couldn't find an allergen with this id: " + id)
					.build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a sauce with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the sauce.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while creating the sauce.").build();
		}
	}
}
