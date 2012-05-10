package com.google.code.tinypmclient.internal.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.client.ClientResponse;

import com.google.code.tinypmclient.User;

public interface Users {
	@GET
	@Path("user/{id}")
	@Produces("application/xml")
	ClientResponse<User> getUser(@PathParam("id") int id);
	
	@GET
	@Path("users")
	@Produces("application/xml")
	ClientResponse<List<User>> getAllUsers();
}
