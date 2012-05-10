package com.google.code.tinypmclient;

import com.google.code.tinypmclient.internal.ClientFactory;
import com.google.code.tinypmclient.internal.MockResponse;
import com.google.code.tinypmclient.internal.resource.Tasks;
import com.google.code.tinypmclient.internal.resource.Users;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response.Status;
import java.util.List;

import static com.google.code.tinypmclient.internal.builder.TaskBuilder.task;
import static com.google.code.tinypmclient.internal.builder.UserBuilder.user;
import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.testng.Assert.fail;

/**
 * Test for TinyPM main resource.
 * @author Marcin Niebudek
 */
public class TinyPMTests {
	
	public static final String API_URL   = "http://example.com/api";
	public static final String API_TOKEN = "test-token-xx";
	
	@Mock
	private Users usersResource;
	
	@Mock
	private Tasks tasksResource;
	
	@Mock
	private ClientFactory clientFactory;
	
	private TinyPM tinyPM;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(clientFactory.getUsers()).thenReturn(usersResource);
		when(clientFactory.getTasks()).thenReturn(tasksResource);
		
		tinyPM = new TinyPM(API_URL, API_TOKEN);
		tinyPM.setClientFactory(clientFactory);
	}
	
	@Test
	public void shouldReturnAllUsers() {
		// given
		User alice = user(1).build();
		User bob = user(2).build();
		
		given(usersResource.getAllUsers()).willReturn(
				new MockResponse<List<User>>(Status.OK, asList(alice, bob))
			);		
		
		// when
		List<User> projects = tinyPM.getAllUsers();

		// then
		assertThat(projects).as("list of projects").containsExactly(alice, bob);
	}
	
	@Test
	public void shouldReturnRequestedUser() {
		// given
		User alice = user(1).build();
		
		
		given(usersResource.getUser(1)).willReturn(
				new MockResponse<User>(Status.OK, alice)
			);
		
		// when
		User user = tinyPM.getUser(1);
		
		// then
		assertThat(user).as("user").isEqualTo(alice);
	}
	
	@Test
	public void shouldReturnNullWhenUserNotFound() {
		// given
		given(usersResource.getUser(1)).willReturn(
				new MockResponse<User>(Status.NOT_FOUND)
			);
		
		// when
		User user = tinyPM.getUser(1);

		// then
		assertThat(user).as("user").isNull();
	}
	
	@Test
	public void shouldThrowExceptionOnFailureWhileGettingUser() {
		// given
		given(usersResource.getUser(1)).willReturn(
				new MockResponse<User>(Status.FORBIDDEN, null)
			);

		try {
			// when
			tinyPM.getUser(1);
			fail("exception expected to inform about some general failure");
		} catch (TinyPMException ex) {
			// everytihng is fine now
		}
	}
	
	@Test
	public void shouldReturnAllUserStoryTask() {
		// given
		Task codingTask = task(1).build();
		Task docuTask = task(2).build();
		Task testingTask = task(3).build();
		
		given(tasksResource.getAllTasksForUserStory(123)).willReturn(
				new MockResponse<List<Task>>(Status.OK, asList(codingTask, docuTask, testingTask))
			);		
		
		// when
		List<Task> storyTasks = tinyPM.getAllUserStoryTasks(123);

		// then
		assertThat(storyTasks).as("list of tasks for story").containsExactly(codingTask, docuTask, testingTask);
	}
}
