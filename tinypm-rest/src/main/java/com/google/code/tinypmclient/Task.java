package com.google.code.tinypmclient;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.code.tinypmclient.internal.ActiveResource;
import com.google.code.tinypmclient.internal.binding.LongFormatDateAdapter;

@XmlRootElement
@XmlAccessorType(FIELD)
public class Task extends ActiveResource {

	private int id;

	private String name;

	private String description;

	private TaskStatus status;

	@XmlJavaTypeAdapter(LongFormatDateAdapter.class)
	private Date startedAt;

	@XmlJavaTypeAdapter(LongFormatDateAdapter.class)
	private Date completedAt;

	@XmlElementWrapper(name = "assignedUsers")
	@XmlElement(name = "user")
	private List<User> assignedUsers;

	public List<User> getAssignedUsers() {
		return assignedUsers;
	}

	public void setAssignedUsers(List<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public boolean delete() {
		return handle(clientFactory.getTasks().deleteTask(id));
	}

	public boolean update() {
		return handle(clientFactory.getTasks().updateTask(id, this));
	}
	
	@Override
	public String toString() {
		return "Task [assignedUsers=" + assignedUsers + ", completedAt="
				+ completedAt + ", description=" + description + ", id=" + id
				+ ", name=" + name + ", startedAt=" + startedAt + ", status="
				+ status + "]";
	}

}