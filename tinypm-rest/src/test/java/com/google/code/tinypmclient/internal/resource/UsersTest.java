/**
 * Copyright (c) 2009 Agilers
 * All rights reserved.
 */
package com.google.code.tinypmclient.internal.resource;

import com.google.code.tinypmclient.User;
import org.testng.annotations.Test;

import static com.google.code.tinypmclient.internal.resource.ProxyBuilder.proxy;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Maria Hotloś (maria.hotlos@agilers.com)
 */
public class UsersTest {

	@Test
	public void shouldGetUser() {
		// given response returned by tinyPM server
		String response = "<user><id>1</id><name>Alice</name><login>alice</login><email>alice@nowhere.com</email><active>true</active></user>";
		
		// when
		User alice = proxy(Users.class, response).getUser(1).getEntity();
		
		// then
		assertThat(alice.getId()).isEqualTo(1);
		assertThat(alice.getName()).isEqualTo("Alice");
	}
	
}
