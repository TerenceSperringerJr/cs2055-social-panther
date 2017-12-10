package com.oracle.entity;

public class Profile {
	private String userID;
	private String name;
	private String email ;
	private String password ;
	private String date_of_birth;
	private String lastlogin;

	 
	 
	 
	public Profile(String userID, String name, String email, String password,
			String date_of_birth, String lastlogin) { 
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.password = password;
		this.date_of_birth = date_of_birth;
		this.lastlogin = lastlogin;
	}
	public Profile() {
		// TODO Auto-generated constructor stub
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	
	
}
