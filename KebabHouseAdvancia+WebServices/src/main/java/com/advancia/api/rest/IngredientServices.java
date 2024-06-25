package com.advancia.api.rest;

import java.util.List;

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

import com.advancia.models.kebabComponents.Ingredient;
import com.advancia.utilities.Services.IngredientManager;

@Path("/ingredients")
public class IngredientServices {
	IngredientManager ingrManager = new IngredientManager();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients
	@GET
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public List<Ingredient> getAllIngredients_JsonOrXml() {

		List<Ingredient> listOfIngredients = ingrManager.fetchAllIngredients();

		return listOfIngredients;
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/id/8
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {

		Ingredient ingredient = ingrManager.fetchIngredientById(id);

		return Response.ok().entity(ingredient).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/name/Pomodoro
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {

		Ingredient ingredient = ingrManager.fetchIngredientByName(name);

		return Response.ok().entity(ingredient).build();

	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/add
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response create(Ingredient ingredient) {

		System.out.println(ingredient);

		ingrManager.addIngredient(ingredient);

		return Response.status(Status.CREATED).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response delete(@PathParam("id") int id) {

	    ingrManager.deleteIngredient(id);

	    return Response.status(Status.NO_CONTENT).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/ingredients/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response update(@PathParam("id") int id, Ingredient ingredient) {

		ingredient.setIngredientId(id);
		
		ingrManager.updateIngredient(ingredient);

		return Response.status(Status.OK).build();
	}
}
