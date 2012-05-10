package com.google.code.tinypmclient.internal.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.ClientResponse;

import com.google.code.tinypmclient.Task;

public interface Tasks {
	@GET
	@Path("task/{id}")
	@Produces("application/xml")
	ClientResponse<Task> getTask(@PathParam("id") int id);
	
	@PUT
	@Path("task/{id}")
	@Consumes("application/xml")
	Response.Status updateTask(@PathParam("id")int id, Task task);

	@DELETE
	@Path("task/{id}")
	@Consumes("application/xml")
	Response.Status deleteTask(@PathParam("id") int id);
	
	
	@GET
	@Path("userstory/{userStoryId}/tasks")
	@Produces("application/xml")
	ClientResponse<List<Task>> getAllTasksForUserStory(@PathParam("userStoryId") int userStoryId);
	
	@POST
	@Path("/userstory/{userStoryId}/task")
	@Consumes("application/xml")
	void createTask(@PathParam("userStoryId") int userStoryId, Task task);
}
