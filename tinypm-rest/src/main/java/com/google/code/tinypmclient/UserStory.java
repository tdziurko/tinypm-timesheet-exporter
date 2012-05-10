package com.google.code.tinypmclient;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.tinypmclient.internal.ActiveResource;

@XmlRootElement
@XmlAccessorType(FIELD)
public class UserStory extends ActiveResource {

	private int id;

	private int position;

	private String name;

	private String description;

	private double estimatedEffort;

	private Priority priority;

	private Iteration iteration;

	private Color color;

	private User acceptingUser;

	private User owner;

	private AcceptanceStatus acceptanceStatus;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getEstimatedEffort() {
		return estimatedEffort;
	}

	public void setEstimatedEffort(double estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Iteration getIteration() {
		return iteration;
	}

	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public User getAcceptingUser() {
		return acceptingUser;
	}

	public void setAcceptingUser(User acceptingUser) {
		this.acceptingUser = acceptingUser;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public AcceptanceStatus getAcceptanceStatus() {
		return acceptanceStatus;
	}

	public void setAcceptanceStatus(AcceptanceStatus acceptanceStatus) {
		this.acceptanceStatus = acceptanceStatus;
	}

	public boolean delete() {
		return handle(clientFactory.getUserStories().deleteUserStory(id));
	}

	public boolean update() {
		return handle(clientFactory.getUserStories().updateUserStory(id, this));
	}
	
	public List<Task> getTasks() {
		return handle(clientFactory.getTasks().getAllTasksForUserStory(id));
	}
	//XXX it has to be this way, we do not have project id in UserStory data or any other way to find it
	public boolean create(int projectId) {
		return handle(clientFactory.getUserStories().createUserStory(projectId, this));
	}
}
