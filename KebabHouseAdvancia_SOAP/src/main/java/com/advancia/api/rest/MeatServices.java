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
import com.advancia.models.dto.kebabComponents.MeatDto;
import com.advancia.utilities.Services.MeatManager;

@Path("/meats")
public class MeatServices {
	MeatManager meatManager = new MeatManager();
	private ValidationExceptionLogic vel = new ValidationExceptionLogic();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getAllMeats_JsonOrXml() {
		try {
			List<MeatDto> listOfMeats = meatManager.fetchAllMeats();
			return Response.ok().entity(listOfMeats).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while fetching all meats");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching all meats").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats/id/3
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {
		try {
			MeatDto meat = new MeatDto(meatManager.fetchMeatById(id));

			return Response.ok().entity(meat).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No meat found with ID " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats/name/Pollo
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {
		try {
			MeatDto meat = new MeatDto(meatManager.fetchMeatByName(name));

			return Response.ok().entity(meat).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No meat found with name: " + name).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching the meat with name: " + name).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/add
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response create(MeatDto meat) {
		try {
			String validationMessage = vel.validateMeatDto(meat);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			meatManager.addMeat(meat);

			return Response.status(Status.CREATED)
					.entity("base " + meat.getMeatName() + " has been created succesfully.").build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a meat with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the meat.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while creating the meat.").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response delete(@PathParam("id") int id) {
		try {
			meatManager.deleteMeat(id);

			return Response.status(Status.NO_CONTENT).entity("meat with id: " + id + " hase been succesfully deleted.")
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("couldn't find a meat with this id: " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response update(@PathParam("id") int id, MeatDto meat) {
		try {
			String validationMessage = vel.validateMeatDto(meat);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			meat.setMeatId(id);

			meatManager.updateMeat(meat);

			return Response.status(Status.OK).entity("meat with id: " + id + " has been succesfully updated.").build();

		} catch(NullPointerException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("couldn't find an allergen with this id: " + id)
					.build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a meat with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the meat.").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while updating the meat.").build();
		}
	}

}
