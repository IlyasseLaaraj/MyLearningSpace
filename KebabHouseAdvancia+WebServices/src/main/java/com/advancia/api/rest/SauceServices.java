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

import com.advancia.models.dto.kebabComponents.SauceDto;
import com.advancia.utilities.Services.SauceManager;

@Path("/sauces")
public class SauceServices {
	SauceManager sauceManager = new SauceManager();
	

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<SauceDto> getAllSauces_JsonOrXml() {

		List<SauceDto> listOfSauces = sauceManager.fetchAllSauces();

		return listOfSauces;
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces/id/4
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {

		SauceDto sauce = new SauceDto(sauceManager.fetchSauceById(id));

		return Response.ok().entity(sauce).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces/name/Maionese
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {

			SauceDto sauce = new SauceDto(sauceManager.fetchSauceByName(name));
			
			return Response.ok().entity(sauce).build();
	}
	
    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    public Response create(SauceDto sauce) {
       	
    	sauceManager.addSauce(sauce);
        
        return Response.status(Status.CREATED).build();
    }

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response delete(@PathParam("id") int id) {

	    sauceManager.deleteSauce(id);

	    return Response.status(Status.NO_CONTENT).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/sauces/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response update(@PathParam("id") int id, SauceDto sauce) {

		sauce.setSauceId(id);
		
		sauceManager.updateSauce(sauce);

		return Response.status(Status.OK).build();
	}
}
