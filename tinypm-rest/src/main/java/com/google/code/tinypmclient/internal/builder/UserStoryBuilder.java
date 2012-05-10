/**
 * Copyright (c) 2009 Agilers
 * All rights reserved.
 */
package com.google.code.tinypmclient.internal.builder;

import com.google.code.tinypmclient.UserStory;
import com.google.code.tinypmclient.internal.ActiveResource;

/**
 * UserStory builder with an ituitive fluent interface.
 * @author Marcin Niebudek
 */
public class UserStoryBuilder {

	private UserStory userStory;

	private UserStoryBuilder(String name) {
		userStory = new UserStory();
		userStory.setName(name);
	}

	// TODO should we add projectId as required parameter?
	public static UserStoryBuilder userStory(String name) {
		return new UserStoryBuilder(name);
	}

	public UserStoryBuilder bindWith(ActiveResource anotherResource) {
		userStory.bindWith(anotherResource);
		return this;
	}
	
	public UserStory build() {
		return userStory;
	}
	
	public UserStoryBuilder describedAs(String description) {
		userStory.setDescription(description);
		return this;
	}
	
	public UserStoryBuilder estimatedAs(double estimatedEffort) {
		userStory.setEstimatedEffort(estimatedEffort);
		return this;
	}
}
