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
import com.advancia.models.dto.kebabComponents.IngredientDto;
import com.advancia.utilities.Services.IngredientManager;

@Path("/ingredients")
public class IngredientServices {
	IngredientManager ingrManager = new IngredientManager();
	private ValidationExceptionLogic vel = new ValidationExceptionLogic();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getAllIngredients_JsonOrXml() {
		try {
			List<IngredientDto> listOfIngredients = ingrManager.fetchAllIngredients();
			return Response.ok().entity(listOfIngredients).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while fetching all ingredients");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching all ingredients").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/id/8
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {
		try {
			IngredientDto ingredient = new IngredientDto(ingrManager.fetchIngredientById(id));

			return Response.ok().entity(ingredient).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No ingredient found with ID " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/name/Pomodoro
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {
		try {
			IngredientDto ingredient = new IngredientDto(ingrManager.fetchIngredientByName(name));

			return Response.ok().entity(ingredient).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No ingredient found with name: " + name).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching the ingredient with name: " + name).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/add
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response create(IngredientDto ingredient) {
		try {
			String validationMessage = vel.validateIngredientDto(ingredient);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			ingrManager.addIngredient(ingredient);

			return Response.status(Status.CREATED)
					.entity("ingredient " + ingredient.getIngredientName() + " has been created succesfully.").build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a ingredient with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the ingredient.").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while creating the ingredient.").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response delete(@PathParam("id") int id) {
		try {
			ingrManager.deleteIngredient(id);

			return Response.status(Status.NO_CONTENT)
					.entity("ingredient with id: " + id + " has been deleted succesfully.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("couldn't find an ingredient with this id: " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response update(@PathParam("id") int id, IngredientDto ingredient) {
		try {
			String validationMessage = vel.validateIngredientDto(ingredient);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			ingredient.setIngredientId(id);

			ingrManager.updateIngredient(ingredient);

			return Response.status(Status.OK).entity("Ingredient with id: " + id + " has been succesfully updated.")
					.build();
		
		} catch(NullPointerException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("couldn't find an allergen with this id: " + id)
					.build();
		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a ingredient with this name already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the ingredient.").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while updating the ingredient.").build();
		}
	}
}
