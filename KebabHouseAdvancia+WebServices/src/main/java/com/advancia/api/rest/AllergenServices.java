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

import com.advancia.models.kebabComponents.Allergen;
import com.advancia.utilities.Services.AllergenManager;

@Path("/allergens")
public class AllergenServices {

	AllergenManager allergenManager = new AllergenManager();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/
	@GET
	@Produces({ MediaType.APPLICATION_SVG_XML, MediaType.APPLICATION_JSON })
	public List<Allergen> getAllAllergens_JsonOrXml() {

		List<Allergen> listOfAllergens = allergenManager.fetchAllAllergens();

		return listOfAllergens;
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/id/1
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("id") int id) {

		Allergen allergen = allergenManager.fetchAllergenById(id);

		return Response.ok().entity(allergen).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/name/Soia e derivati
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {

		Allergen allergen = allergenManager.fetchAllergenByName(name);

		return Response.ok().entity(allergen).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/add
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response create(Allergen allergen) {

		System.out.println(allergen);

		allergenManager.addAllergen(allergen);

		return Response.status(Status.CREATED).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response delete(@PathParam("id") int id) {

	    allergenManager.deleteAllergen(id);

	    return Response.status(Status.NO_CONTENT).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/update
	@PUT
	@Path("/update")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response update(Allergen allergen) {

		allergenManager.updateAllergen(allergen);

		return Response.status(Status.OK).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/allergens/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response update(@PathParam("id") int id, Allergen allergen) {

		allergen.setAllergenId(id);
		
		allergenManager.updateAllergen(allergen);

		return Response.status(Status.OK).build();
	}

}
