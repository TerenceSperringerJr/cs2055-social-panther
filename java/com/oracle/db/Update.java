package com.oracle.db;
 
import com.oracle.entity.Profile;

public class Update {
	public static boolean profile(Profile o){
		String PROFILE_UPDATE_SQL="UPDATE PROFILE SET LASTLOGIN = sysdate ";
		String where=" where userID = '"+o.getUserID()+"'"; 
		return DBUtil.executeSql(PROFILE_UPDATE_SQL+where);
	}
	
	 
 
}
