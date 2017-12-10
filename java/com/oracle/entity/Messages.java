package com.oracle.entity;

public class Messages {
	private String msgID ;
	private String fromID;
	private String message   ;
	private String toUserID ;
	private String toGroupID;
	private String dateSent ;
	
	
	public Messages(String msgID, String fromID, String message,
			String toUserID, String toGroupID, String dateSent) {
		super();
		this.msgID = msgID;
		this.fromID = fromID;
		this.message = message;
		this.toUserID = toUserID;
		this.toGroupID = toGroupID;
		this.dateSent = dateSent;
	}
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getFromID() {
		return fromID;
	}
	public void setFromID(String fromID) {
		this.fromID = fromID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToUserID() {
		return toUserID;
	}
	public void setToUserID(String toUserID) {
		this.toUserID = toUserID;
	}
	public String getToGroupID() {
		return toGroupID;
	}
	public void setToGroupID(String toGroupID) {
		this.toGroupID = toGroupID;
	}
	public String getDateSent() {
		return dateSent;
	}
	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}
	
	
}
