/**
 * Copyright (c) 2009 Agilers
 * All rights reserved.
 */
package com.google.code.tinypmclient.internal.resource;

import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.jboss.resteasy.client.core.BaseClientResponse.BaseClientResponseStreamFactory;
import org.jboss.resteasy.util.CaseInsensitiveMap;
import org.mockito.Mock;

import javax.ws.rs.core.HttpHeaders;
import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Maria Hotloś (maria.hotlos@agilers.com)
 */
public class ProxyBuilder {

	public static <E> E proxy(Class<E> clazz, String responseXML) {
		return ProxyFactory.create(clazz, "", new ClientExecutorStub<E>(responseXML));
	}
	
	private static class ClientExecutorStub<E> implements ClientExecutor {

		@Mock
		private BaseClientResponseStreamFactory responseFactory;

		private ClientExecutorStub(String responseXML) {
			initMocks(this);
			try {
				// instead of requesting from server via http let's just give the xml
				when(responseFactory.getInputStream()).thenReturn(new ByteArrayInputStream(responseXML.getBytes("UTF-8")));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		public ClientResponse<E> execute(ClientRequest request) throws Exception {

			BaseClientResponse<E> response = new BaseClientResponse<E>(responseFactory);
			response.setProviderFactory(request.getProviderFactory());
			
			final CaseInsensitiveMap<String> headers = new CaseInsensitiveMap<String>();
			headers.add(HttpHeaders.CONTENT_ENCODING, "");
			response.setHeaders(headers);
			
			return response;
		}
	}
	
}
