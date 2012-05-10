package com.google.code.tinypmclient;

import java.util.List;


public class TestClient {

	public static void main(String[] args) {
		TinyPM tinyPM = new TinyPM("http://demo.tinypm.com/api", "8709725e52ecec50e4ece9e8f718c355");
		
		/* 
		 * getting task
		 */
		Task task = tinyPM.getTask(2);
		System.out.println(task);
		
		
		/*
		 *geting all user story tasks 
		 */		
		UserStory us = tinyPM.getUserStory(16);
		List<Task> tasks = us.getTasks();
		for(Task t: tasks) {
			System.out.println(t);
		}
		
		/*
		 * creating story through builder 
		 */
		UserStory attachedUserStory = tinyPM.createUserStory("API Driven Story")
			.describedAs("As a user I want to...")
			.estimatedAs(2.0)
			.build();
		System.out.println(attachedUserStory.create(1));;
		
		/* 
		 * binding deserialized objects back to tinyPM 
		 */
		UserStory detachedUserStory = new UserStory(); // or some JSON.deserialize(jsonString);
		detachedUserStory.setName("API Driven Story");
		detachedUserStory.setDescription("As a user I want to...");
		detachedUserStory.setEstimatedEffort(2.0);
		detachedUserStory.bindWith(tinyPM);
		System.out.println(detachedUserStory.create(1));
		
	}
}
