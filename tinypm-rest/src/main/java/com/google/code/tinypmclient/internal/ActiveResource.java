package com.google.code.tinypmclient.internal;

import java.util.List;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.ClientResponse;


/**
 * Abstract resource able to connect to tinyPM.
 * @author Marcin Niebudek
 */
public abstract class ActiveResource {

	protected transient ClientFactory clientFactory;
	
	protected transient ClientResponseHandler responseHandler;
	
	public void bindWith(ActiveResource anotherResource) {
		if (anotherResource != null) {
			this.clientFactory = anotherResource.clientFactory;
			this.responseHandler = anotherResource.responseHandler;
		}
	}
	
	protected <E extends ActiveResource> E handle(ClientResponse<E> response) {
		return wave(responseHandler.handleResponse(response));
	}
	
	protected <E extends ActiveResource> List<E> handle(ClientResponse<List<E>> response) {
		return wave(responseHandler.handleResponse(response));
	}
	
	protected boolean handle(Response.Status status) {
		return responseHandler.handleResponse(status);
	}
	
	/**
	 * Waves a list of ActiveResources binding them this resource (and it's 
	 * connetion settings).
	 * 
	 * @param resources list of ActiveResource objects
	 * @return the same list but with all resources bound to this resource's settings.
	 */
	protected <E extends ActiveResource> List<E> wave(List<E> resources) {
		if (resources != null) {
			for (ActiveResource resource : resources) {
				wave(resource);
			}
		}
		
		return resources;
	}
	
	/**
	 * Waves another ActiveResource binding it with this resource (and it's 
	 * connetion settings).
	 * 
	 * @param resource another ActiveResource object
	 * @return resource waved with this resource's connection settings.
	 */
	protected <E extends ActiveResource> E wave(E resource) {
		if (resource != null) {
			resource.bindWith(this);
		}
		
		return resource;
	}
}
