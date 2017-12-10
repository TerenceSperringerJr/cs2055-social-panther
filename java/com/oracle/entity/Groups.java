package com.oracle.entity;

public class Groups {
	private String gID ;
	private String name;
	private String description;
	
	
	public Groups(String gID, String name, String description) { 
		this.gID = gID;
		this.name = name;
		this.description = description;
	}
	public String getgID() {
		return gID;
	}
	public void setgID(String gID) {
		this.gID = gID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
