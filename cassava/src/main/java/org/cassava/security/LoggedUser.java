package org.cassava.security;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class LoggedUser implements HttpSessionBindingListener {

	private int id;

	private String username;
	
	private String firstName ;
	
	private String lastName ;
	
	private String picture;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private ActiveUserStore activeUserStore;

	public LoggedUser(int id, String username, ActiveUserStore activeUserStore) {
		this.id = id;
		this.username = username;
		this.activeUserStore = activeUserStore;
	}
	
	public LoggedUser(int id, String username, String firstName, String lastName, ActiveUserStore activeUserStore) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.activeUserStore = activeUserStore;
	}

	public LoggedUser(int id, String username, String firstName, String lastName, ActiveUserStore activeUserStore, String picture) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.activeUserStore = activeUserStore;
		this.picture = picture;
	}

	public LoggedUser() {
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (!users.contains(user.getUsername())) {
			users.add(user.getUsername());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();

		
		if (user != null)
			users.remove(user.getUsername());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}