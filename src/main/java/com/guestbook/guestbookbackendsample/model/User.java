package com.guestbook.guestbookbackendsample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblUser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "user_type")
	private String userType;

	public User() {
	}

	public User(long userId, String userName, String password, String emailId, String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
		this.userType = userType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
