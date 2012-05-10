package com.google.code.tinypmclient;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.tinypmclient.internal.ActiveResource;

@XmlRootElement
@XmlAccessorType(FIELD)
public class User extends ActiveResource {

	private int id;

	private String name;

	private String login;

	private String email;

	private boolean active;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [active=" + active + ", email=" + email + ", id=" + id
				+ ", login=" + login + ", name=" + name + "]";
	}

}
