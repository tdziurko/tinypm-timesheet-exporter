/**
 * Copyright (c) 2009 Agilers
 * All rights reserved.
 */
package com.google.code.tinypmclient.internal;

import org.jboss.resteasy.client.core.BaseClientResponse;

public class MockResponse<E> extends BaseClientResponse<E> {
	private Status status;
	private E entity;

	public MockResponse(Status status, E entity) {
		super(null);
		this.status = status;
		this.entity = entity;
	}

	public MockResponse(Status status) {
		this(status, null);
	}

	@Override
	public int getStatus() {
		return status.getStatusCode();
	}

	@Override
	public E getEntity() {
		return entity;
	}
}
