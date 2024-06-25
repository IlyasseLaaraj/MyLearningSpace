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

import com.advancia.models.kebabComponents.Base;
import com.advancia.utilities.Services.BaseManager;

@Path("/bases")
public class BaseServices {

	public BaseManager baseManager = new BaseManager();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases

	@GET
	@Produces({ MediaType.APPLICATION_SVG_XML, MediaType.APPLICATION_JSON })
	public List<Base> getAllBases_JsonOrXml() {

		List<Base> listOfBases = baseManager.fetchAllBases();

		return listOfBases;
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/id/1
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {

		Base base = baseManager.fetchBaseById(id);

		return Response.ok().entity(base).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/name/Pane arabo
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {

		Base base = baseManager.fetchBaseByName(name);

		return Response.ok().entity(base).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/add
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response create(Base base) {

		System.out.println(base);

		baseManager.addBase(base);

		return Response.status(Status.CREATED).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response delete(@PathParam("id") int id) {

	    baseManager.deleteBase(id);

	    return Response.status(Status.NO_CONTENT).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/bases/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response update(@PathParam("id") int id, Base base) {

		base.setBaseId(id);
		
		baseManager.updateBase(base);

		return Response.status(Status.OK).build();
	}
	
}

