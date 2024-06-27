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
import com.advancia.models.dto.UserDto;
import com.advancia.utilities.Services.UserManager;

@Path("/users")
public class UserServices {

	UserManager userManager = new UserManager();
	private ValidationExceptionLogic vel = new ValidationExceptionLogic();

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/
	@GET
	@Produces({ MediaType.APPLICATION_SVG_XML, MediaType.APPLICATION_JSON })
	public Response getAllUsers_JsonOrXml() {
		try {
			List<UserDto> listOfUsers = userManager.fetchAllUsers();

			return Response.ok().entity(listOfUsers).build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while fetching all users");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching all users").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/id/1
	@GET
	@Path("id/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response get(@PathParam("id") int id) {
		try {
			UserDto user = new UserDto(userManager.fetchUserById(id));
			return Response.ok(user).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No user found with ID " + id).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/name/IlyasseLaaraj
	@GET
	@Path("name/{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response get(@PathParam("name") String name) {
		try {
			UserDto user = new UserDto(userManager.fetchUserByName(name));
			return Response.ok(user).build();
		} catch (EntityNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("No user found with usernaeme " + name).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while fetching the user with username: " + name).build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/add
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response create(UserDto user) {
		try {
			String validationMessage = vel.validateUserDto(user);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			userManager.addUser(user);

			return Response.status(Status.CREATED)
					.entity("user " + user.getUsername() + " has been created succesfully.").build();

		} catch (InvalidAddException e) {
			e.printStackTrace();
			switch (e.getReason()) {
			case CONTRAINT_VIOLATION:
				return Response.status(Response.Status.BAD_REQUEST).entity("a user with this username already exist")
						.build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("An unexpected error occurred while creating the user.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while creating the user.").build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/delete/{id}
	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response delete(@PathParam("id") int id) {
		try {
			userManager.deleteUser(id);

			return Response.status(Status.NO_CONTENT).entity("user with id: " + id + " hase been succesfully deleted.")
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("couldn't find a user with this id: " + id)
					.build();
		}
	}

	// URI:
	// http://localhost:8083/KebabHouseAdvancia/rest/users/update/id
	@PUT
	@Path("/update/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML })
	public Response update(@PathParam("id") int id, UserDto user) {
		try {
			String validationMessage = vel.validateUserDto(user);
			if (validationMessage != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(validationMessage).build();
			}
			user.setUserId(id);

			userManager.updateUser(user);

			return Response.status(Status.OK).entity("user with id: " + id + " has been succesfully updated.").build();
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
						.entity("An unexpected error occurred while updating the user.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("An unexpected error occurred while updating the user.").build();
		}
	}

}
