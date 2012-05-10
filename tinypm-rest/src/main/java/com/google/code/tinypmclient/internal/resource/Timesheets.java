package com.google.code.tinypmclient.internal.resource;

import com.google.code.tinypmclient.internal.Activity;
import org.jboss.resteasy.client.ClientResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

public interface Timesheets {

    @GET
    @Path("iteration/{iterationId}/timesheet")
    @Produces("application/xml")
    ClientResponse<List<Activity>> getTimesheetForIteration(@PathParam("iterationId") int iterationId);
}
