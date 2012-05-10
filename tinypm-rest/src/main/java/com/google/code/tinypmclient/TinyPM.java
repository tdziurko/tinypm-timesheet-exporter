package com.google.code.tinypmclient;

import com.google.code.tinypmclient.internal.ActiveResource;
import com.google.code.tinypmclient.internal.ClientFactory;
import com.google.code.tinypmclient.internal.ClientResponseHandler;
import com.google.code.tinypmclient.internal.builder.UserStoryBuilder;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Represents a starting point to configure and call tinyPM's REST resources.
 * @author Marcin Niebudek
 */
public class TinyPM extends ActiveResource {

	/**
	 * Creates tinyPM instance connected with a given API address and using a given
	 * authentication token.
	 * 
	 * @param url remote tinyPM address
	 * @param token authentication token that has been generated in remote tinyPM
	 */
	public TinyPM(String url, String token) {
		// init default client factory and response handler
		clientFactory = new ClientFactory(url, token);
		responseHandler = new ClientResponseHandler();
	}
	
	public void bind(ActiveResource resource) {
		if (resource != null) {
			bindWith(resource);
		}
	}
	
	/* for stubbing purposes */
	protected void setClientFactory(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	/**
	 * Fetch all tinyPM users.
	 *
	 * @return list of all tinyPM users
	 */
	public List<User> getAllUsers() {
		return handle(clientFactory.getUsers().getAllUsers());
	}
	
	/**
	 * Fetch user having a given id.
	 * 
	 * @param userId id of the user
	 * @return user with given id or null if doesn't exist
	 */
	public User getUser(int userId) {
		return handle(clientFactory.getUsers().getUser(userId));
	}
	
	/**
	 * Fetch user story having a given id.
	 * 
	 * @param userStoryId the user story id
	 * @return user story with given id or null if doesn't exist
	 */
	public UserStory getUserStory(int userStoryId) {
		return handle(clientFactory.getUserStories().getUserStory(userStoryId));
	}
	
	public UserStoryBuilder createUserStory(String name) {
		return UserStoryBuilder.userStory(name).bindWith(this);
	}
	
	/**
	 * Fetch all user story tasks.
	 * 
	 * @param userStoryId user story id for which tasks should be fetched
	 * @return list of all story tasks
	 */
	public List<Task> getAllUserStoryTasks(int userStoryId) {
		return handle(clientFactory.getTasks().getAllTasksForUserStory(userStoryId));
	}
	
	/**
	 * Fetch task having a given id.
	 * 
	 * @param taskId the task id
	 * @return task with given id or null if doesn't exist
	 */
	public Task getTask(int taskId) {
		return handle(clientFactory.getTasks().getTask(taskId));
	}
	
	/**
	 * Fetch project having a given id
	 * 
	 * @param projectId the project id
	 * @return project with given id or null if doesn't exist
	 */
	public Project getProject(int projectId) {
		return handle(clientFactory.getProjects().getProject(projectId));
	}


    public Project getProject(final String code) {
        List<Project> projects = handle(clientFactory.getProjects().getAllProject());

        Project project = Iterables.find(projects, new Predicate<Project>() {
            @Override
            public boolean apply(@Nullable Project project) {
                return project.getCode().equals(code);
            }
        });

        return project;
    }


	
	/**
	 * Fetch all tinyPM projects.
	 * 
	 * @return list of all tinyPM projects
	 */
	public List<Project> getAllProjects() {
		return handle(clientFactory.getProjects().getAllProject());
	}
}