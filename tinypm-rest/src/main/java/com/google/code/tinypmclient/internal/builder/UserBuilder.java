package com.google.code.tinypmclient.internal.builder;

import com.google.code.tinypmclient.User;

/**
 * User fluent builder.
 * @author Marcin Niebudek
 */
public class UserBuilder {

	private User user;

	private UserBuilder() {
		user = new User();
	}
	
	private UserBuilder(int id) {
		this();
		user.setId(id);
	}

	public static UserBuilder user() {
		return new UserBuilder();
	}
	
	public static UserBuilder user(int id) {
		return new UserBuilder(id);
	}

	public User build() {
		return user;
	}
	
}
