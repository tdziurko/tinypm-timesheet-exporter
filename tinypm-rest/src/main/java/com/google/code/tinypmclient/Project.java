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
import com.google.code.tinypmclient.internal.binding.DateAdapter;

@XmlRootElement
@XmlAccessorType(FIELD)
public class Project extends ActiveResource {
	
	private int id;
	
	private String code;
	
	private String name;
	
	private String description;
	
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date startDate;
	
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date targetFinishDate;
	
	private User owner;
	
	private int iterationLength;
	
	private String defaultTask;
	
	private boolean active;
	
	@XmlElementWrapper(name = "estimates")
	@XmlElement(name = "value")
	private List<Float> estimates;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getTargetFinishDate() {
		return targetFinishDate;
	}

	public void setTargetFinishDate(Date targetFinishDate) {
		this.targetFinishDate = targetFinishDate;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getIterationLength() {
		return iterationLength;
	}

	public void setIterationLength(int iterationLength) {
		this.iterationLength = iterationLength;
	}

	public String getDefaultTask() {
		return defaultTask;
	}

	public void setDefaultTask(String defaultTask) {
		this.defaultTask = defaultTask;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Float> getEstimates() {
		return estimates;
	}

	public void setEstimates(List<Float> estimates) {
		this.estimates = estimates;
	}
	
	@Override
	public String toString() {
		return "Project [active=" + active + ", code=" + code
				+ ", defaultTask=" + defaultTask + ", description="
				+ description + ", estimates=" + estimates + ", id=" + id
				+ ", iterationLength=" + iterationLength + ", name=" + name
				+ ", owner=" + owner + ", startDate=" + startDate
				+ ", targetFinishDate=" + targetFinishDate + "]";
	}
	
}
