package com.webflix.users.api.v1.resources;

import com.webflix.users.models.entities.UserEntity;
import com.webflix.users.services.beans.UserDataBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

	@Inject
	private UserDataBean userDataBean;

	@GET
	public Response getUsers() {
		List<UserEntity> list = userDataBean.getUsersRawData();
		return Response.ok(list).build();
	}

}
