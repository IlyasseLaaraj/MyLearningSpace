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
import com.advancia.models.dto.kebabComponents.BaseDto;
import com.advancia.utilities.Services.BaseManager;

@Path("/bases")
public class BaseServices {

	private BaseManager baseManager = new BaseManager();
	private ValidationExceptionLogic vel = new ValidationExceptionLogic();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllBases_JsonOrXml() {
		try {
			List<BaseDto> listOfBases = baseManager.fetchAllBases();

			return Response.ok().entity(listOfBases).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while fetching all bases");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching all bases").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/id/1
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {
		try {
			BaseDto base = new BaseDto(baseManager.fetchBaseById(id));

			return Response.ok().entity(base).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No base found with ID " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/name/Pane arabo
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {
		try {
			BaseDto base = new BaseDto(baseManager.fetchBaseByName(name));

			return Response.ok().entity(base).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No base found with name: " + name).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching the base with name: " + name).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/add
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response create(BaseDto base) {
		try {
			String validationMessage = vel.validateBaseDto(base);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			baseManager.addBase(base);

			return Response.status(Status.CREATED)
					.entity("base " + base.getBaseName() + " has been created succesfully.").build();

		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a base with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the base.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while creating the base.").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("id") int id) {
		try {
			baseManager.deleteBase(id);

			return Response.status(Status.NO_CONTENT).entity("base with id: " + id + " has  succesfully deleted.")
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("couldn't find a base with this id: " + id)
					.build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(@PathParam("id") int id, BaseDto base) {
		try {
			String validationMessage = vel.validateBaseDto(base);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			base.setBaseId(id);

			baseManager.updateBase(base);

			return Response.status(Status.OK).entity("base with id: " + id + " has been succesfully updated.").build();
		} catch (NullPointerException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("couldn't find an allergen with this id: " + id)
					.build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a base with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while updating the base.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while updating the base.").build();
		}
	}
}
