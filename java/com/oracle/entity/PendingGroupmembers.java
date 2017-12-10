package com.oracle.entity;

public class PendingGroupmembers {
	private String gID   ;
	private String userID  ;
	private String message ;
	
	
	public PendingGroupmembers(String gID, String userID, String message) { 
		this.gID = gID;
		this.userID = userID;
		this.message = message;
	}
	public String getgID() {
		return gID;
	}
	public void setgID(String gID) {
		this.gID = gID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	 
}
