package com.google.code.tinypmclient.internal.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.client.ClientResponse;

import com.google.code.tinypmclient.Project;

public interface Projects {
	@GET
	@Path("project/{id}")
	@Produces("application/xml")
	ClientResponse<Project> getProject(@PathParam("id") int id);

	@GET
	@Path("projects")
	@Produces("application/xml")
	ClientResponse<List<Project>> getAllProject();
	
	
	
}
