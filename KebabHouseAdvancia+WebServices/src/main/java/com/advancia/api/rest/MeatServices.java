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

import com.advancia.models.kebabComponents.Meat;
import com.advancia.utilities.Services.MeatManager;

@Path("/meats")
public class MeatServices {
	MeatManager meatManager = new MeatManager();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Meat> getAllMeats_JsonOrXml() {

		List<Meat> listOfMeats = meatManager.fetchAllMeats();

		return listOfMeats;
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats/id/3
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {

		Meat meat = meatManager.fetchMeatById(id);

		return Response.ok().entity(meat).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats/name/Pollo
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {

			
			Meat meat = meatManager.fetchMeatByName(name);
			
			return Response.ok().entity(meat).build();

	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/add
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response create(Meat meat) {

		System.out.println(meat);

		meatManager.addMeat(meat);

		return Response.status(Status.CREATED).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response delete(@PathParam("id") int id) {

	    meatManager.deleteMeat(id);

	    return Response.status(Status.NO_CONTENT).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/meats/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response update(@PathParam("id") int id, Meat meat) {

		meat.setMeatId(id);
		
		meatManager.updateMeat(meat);

		return Response.status(Status.OK).build();
	}

}
