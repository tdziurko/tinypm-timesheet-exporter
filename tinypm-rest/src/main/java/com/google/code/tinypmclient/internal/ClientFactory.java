package com.google.code.tinypmclient.internal;

import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;

import com.google.code.tinypmclient.internal.resource.Iterations;
import com.google.code.tinypmclient.internal.resource.Projects;
import com.google.code.tinypmclient.internal.resource.Tasks;
import com.google.code.tinypmclient.internal.resource.UserStories;
import com.google.code.tinypmclient.internal.resource.Users;

/**
 * Remote tinyPM resource client factory (bound to specific tinyPM through the
 * application URL and authentication token).
 * 
 * @author Marcin Niebudek
 */
public class ClientFactory {

	private static final String X_TINY_PM_TOKEN = "X-tinyPM-token";

	private String applicationUrl;
	private ClientExecutor clientExecutor;

	/* cached instances */
	private Users usersClient;
	private UserStories userStoriesClient;
	private Tasks tasksClient;
	private Iterations iterationsClient;
	private Projects projectsClient;

	@SuppressWarnings("unchecked")
	public ClientFactory(final String applicationUrl, final String token) {
		this.applicationUrl = applicationUrl;

		this.clientExecutor = new ApacheHttpClientExecutor() {
			@Override
			public ClientResponse execute(ClientRequest request) throws Exception {
				request.getHeaders().add(X_TINY_PM_TOKEN, token);
				return super.execute(request);
			}
		};
	}

	public Users getUsers() {
		if (usersClient == null) {
			usersClient = createProxy(Users.class); 
		}
		
		return usersClient; 
	}

	public Iterations getIterations() {
		if (iterationsClient == null) {
			iterationsClient = createProxy(Iterations.class);
		}
		
		return iterationsClient;
	}

	public UserStories getUserStories() {
		if (userStoriesClient == null) {
			userStoriesClient = createProxy(UserStories.class);
		}
		
		return userStoriesClient;
	}

	public Tasks getTasks() {
		if (tasksClient == null) {
			tasksClient = createProxy(Tasks.class);
		}
		
		return tasksClient;
	}

	public Projects getProjects() {
		if (projectsClient == null) {
			projectsClient = createProxy(Projects.class);
		}
		return projectsClient;
	}

	/**
	 * Creates client using RESTEasy proxies generated from annotated
	 * interfaces.
	 * 
	 * @param clazz annotated interface of desired proxy client
	 * @return proxy client
	 */
	private <E> E createProxy(Class<E> clazz) {
		return ProxyFactory.create(clazz, applicationUrl, clientExecutor);
	}

}
