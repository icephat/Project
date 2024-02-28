package org.cassava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

/**
 * Userinfield generated by hbm2java
 */
public class UserInField implements java.io.Serializable {

	@JsonIgnore
	private UserInFieldId id;
	@JsonIgnore
	private Field field;
	@JsonIgnore
	private User user;

	public UserInField() {
	}

	public UserInField(UserInFieldId id, Field field, User userInField) {
		this.id = id;
		this.field = field;
		this.user = userInField;
	}

	public UserInFieldId getId() {
		return this.id;
	}

	public void setId(UserInFieldId id) {
		this.id = id;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
