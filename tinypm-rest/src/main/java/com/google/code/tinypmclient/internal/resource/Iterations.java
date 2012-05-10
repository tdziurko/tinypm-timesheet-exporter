package com.google.code.tinypmclient.internal.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.client.ClientResponse;

import com.google.code.tinypmclient.Iteration;

public interface Iterations {
	@GET
	@Path("project/{projectId}/iterations")
	@Produces("application/xml")
	ClientResponse<List<Iteration>> getAllIterations(@PathParam("projectId") int projectId);
}
