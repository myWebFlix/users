package com.webflix.users.api.v1.resources;

import com.webflix.users.models.entities.UserEntity;
import com.webflix.users.services.beans.UserDataBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

	@Inject
	private UserDataBean userDataBean;

	@GET
	@Path("/users")
	public Response getUsers() {
		List<UserEntity> list = userDataBean.getUsersRawData();
		System.out.println("get users");
		return Response.ok(list).build();
	}

	@GET
	@Path("/auth")
	public Response getUser(@HeaderParam("ID-Token") String idTokenString) {
		Integer userId = userDataBean.manageUser(idTokenString);

		if (userId != null) {

			//UserEntity user = userDataBean.getUsersRawData();

			System.out.println("User ID: " + userId);

			return Response.ok(userId).build();

		} else {

			return Response.status(Response.Status.UNAUTHORIZED).build();

		}
	}

	//@POST

}
