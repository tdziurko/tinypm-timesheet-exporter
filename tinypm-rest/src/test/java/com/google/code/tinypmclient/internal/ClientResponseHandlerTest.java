package com.google.code.tinypmclient.internal;

import com.google.code.tinypmclient.TinyPMException;
import com.google.code.tinypmclient.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response.Status;
import java.util.List;

import static com.google.code.tinypmclient.internal.builder.UserBuilder.user;
import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.fail;

public class ClientResponseHandlerTest {
	
	public static final String API_URL   = "http://example.com/api";
	public static final String API_TOKEN = "test-token-xx";
	
	private ClientResponseHandler responseHandler;
	
	@BeforeClass
	public void setup() {
		responseHandler = new ClientResponseHandler();
	}
	
	@Test
	public void shouldUnpackListOfEntitiesFromValidResponse() {
		// given
		User alice = user(1).build();
		User bob = user(2).build();
		
		// when
		List<User> users = responseHandler.handleResponse(new MockResponse<List<User>>(Status.OK, asList(alice, bob)));

		// then
		assertThat(users).as("list of users").containsExactly(alice, bob);
	}
	
	@Test
	public void shouldUnpackEntityFromValidResponse() {
		// given
		User alice = new User();
		
		// when
		User user = responseHandler.handleResponse(new MockResponse<User>(Status.OK, alice));
		
		// then 
		assertThat(user).as("user").isEqualTo(alice);
	}
	
	@Test
	public void shouldReturnNullWhenResponseStatusNotFound() {
		// when
		User user = responseHandler.handleResponse(new MockResponse<User>(Status.NOT_FOUND));

		// then
		assertThat(user).as("user").isNull();
	}
	
	@Test
	public void shouldThrowExceptionOnFatalStatuses() {
		try {
			// when
			responseHandler.handleResponse(new MockResponse<User>(Status.FORBIDDEN));
			fail("exception expected to inform about some general failure");
		} catch (TinyPMException ex) {
			// everything is fine now
		}
	}	
}
