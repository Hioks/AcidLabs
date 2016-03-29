package com.acidlabs.bo;

public class User {
	
	private String username;
	private String image;
	private String message;
	
	public User(){
		super();
	}
	
	public User(String username, String image) {
		super();
		this.username = username;
		this.image = image;
	}
	
	public User(String message) {
		super();
		this.message = message;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
