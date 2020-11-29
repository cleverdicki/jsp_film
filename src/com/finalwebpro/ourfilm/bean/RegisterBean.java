package com.finalwebpro.ourfilm.bean;

public class RegisterBean {
	private String name_user;
	private String email_user;
	private String password_user;
	
	public RegisterBean() {
		super();
	}
	
	public RegisterBean(String name_user, String email_user, String password_user) {
		super();
		this.name_user = name_user;
		this.email_user = email_user;
		this.password_user = password_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	public String getPassword_user() {
		return password_user;
	}

	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}
	
}
