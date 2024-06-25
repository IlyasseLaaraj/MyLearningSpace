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

import com.advancia.models.User;
import com.advancia.models.dto.UserDto;
import com.advancia.utilities.Services.UserManager;

@Path("/users")
public class UserServices {

	UserManager userManager = new UserManager();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/
	@GET
	@Produces({ MediaType.APPLICATION_SVG_XML, MediaType.APPLICATION_JSON })
	public Response getAllUsers_JsonOrXml() {

		List<UserDto> listOfUsers = userManager.fetchAllUsers();

		return Response.ok().entity(listOfUsers).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/id/1
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response get(@PathParam("id") int id) {

		UserDto user = new UserDto(userManager.fetchUserById(id));
		return Response.ok(user).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/name/IlyasseLaaraj
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {

		UserDto user = new UserDto(userManager.fetchUserByName(name));
		return Response.ok(user).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/add
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response create(User user) {

		userManager.addUser(user);

		return Response.status(Status.CREATED).build();
	}
	
	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response delete(@PathParam("id") int id) {

	    userManager.deleteUser(id);

	    return Response.status(Status.NO_CONTENT).build();
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public Response update(@PathParam("id") int id, User user) {

		user.setUserId(id);
		
		userManager.updateUser(user);

		return Response.status(Status.OK).build();
	}

}
