package com.oracle.entity;

public class PendingFriends {
	private String fromID;
	private String toID ;
	private String message;
	public String getFromID() {
		return fromID;
	}
	public void setFromID(String fromID) {
		this.fromID = fromID;
	}
	public String getToID() {
		return toID;
	}
	public void setToID(String toID) {
		this.toID = toID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PendingFriends(String fromID, String toID, String message) {
		super();
		this.fromID = fromID;
		this.toID = toID;
		this.message = message;
	}
	
	
}
