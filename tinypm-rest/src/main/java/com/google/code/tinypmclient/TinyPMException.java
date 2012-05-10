package com.google.code.tinypmclient;

import javax.ws.rs.core.Response.Status;

public class TinyPMException extends RuntimeException {
	private static final long serialVersionUID = -7274739002291259020L;
	private final Status status;
	
	public TinyPMException(Status status) {
		super("Response status: " + status.name() + " (" + status.getStatusCode() + ")");
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
}
