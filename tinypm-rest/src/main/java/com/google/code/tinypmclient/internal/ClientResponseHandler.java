/**
 * Copyright (c) 2009 Agilers
 * All rights reserved.
 */
package com.google.code.tinypmclient.internal;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.ClientResponse;

import com.google.code.tinypmclient.TinyPMException;

/**
 * Unpacks entities form response.
 * 
 * @author Maria Hotloś (maria.hotlos@agilers.com)
 */
public class ClientResponseHandler {

	public <E> E handleResponse(ClientResponse<E> response) {
		if (response.getResponseStatus() == OK) {
			return response.getEntity();
		}
		if (response.getResponseStatus() == NOT_FOUND) {
			return null;
		}
		throw new TinyPMException(response.getResponseStatus());
	}
	
	public boolean handleResponse(Response.Status status) {
		if (status == CREATED || status == OK) {
			return true;
		}
		if (status == NOT_FOUND) {
			return false;
		}
		throw new TinyPMException(status);
	}
}
