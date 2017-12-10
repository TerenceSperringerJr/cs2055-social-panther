package com.oracle.db;

import java.util.Map;

import com.oracle.entity.Profile;

public class Query {

	public static Map<String, String> profile(Profile profile) {
		String sql = "select * from profile where userID ='"+profile.getUserID()+"' and password='"+profile.getPassword()+"'";
		return DBUtil.getObject(sql); 
	}

}
