package com.oracle.db;

import com.oracle.entity.*;

public class Insert { 
	
	
	public static boolean profile(Profile o){
		String PROFILE_INSERT_SQL="INSERT INTO PROFILE (NAME,EMAIL,PASSWORD,DATE_OF_BIRTH,LASTLOGIN) VALUES ";
		String values="('"+o.getName()+"','"+o.getEmail()+"','"+o.getPassword()+"',to_date('"+o.getDate_of_birth()+"','YYYY-MM-DD'),'"+o.getLastlogin()+"')"; 
		return DBUtil.executeSql(PROFILE_INSERT_SQL+values);
	} 

	public static boolean pending_friends(PendingFriends o){
		String PENDING_FRIENDS_INSERT_SQL="INSERT INTO PENDING_FRIENDS (FROMID,TOID,MESSAGE) VALUES ";
		String values ="('"+o.getFromID()+"','"+o.getToID()+"','"+o.getMessage()+"')";
		return DBUtil.executeSql(PENDING_FRIENDS_INSERT_SQL+values);
	}

	public static boolean message(Messages o){
		String MESSAGES_INSERT_SQL="INSERT INTO MESSAGES (FROMID,MESSAGE,TOUSERID,TOGROUPID,DATESENT) VALUES ";
		String values ="('"+o.getFromID()+"','"+o.getMessage()+"','"+o.getToUserID()+"','"+o.getToGroupID()+"',sysdate)";
		return DBUtil.executeSql(MESSAGES_INSERT_SQL+values);
	}
	 
	public static boolean groups(Groups o){
		String GROUPS_INSERT_SQL="INSERT INTO GROUPS (NAME,DESCRIPTION) VALUES ";
		String values ="('"+o.getName()+"','"+o.getDescription()+"')";
		return DBUtil.executeSql(GROUPS_INSERT_SQL+values);
	}

	
	public static boolean pendingGroupmembers(PendingGroupmembers o){
		String PENDING_GROUP_MEMBERS_INSERT_SQL="INSERT INTO PENDING_GROUP_MEMBERS (GID,USERID,MESSAGE) VALUES ";
		String values ="('"+o.getgID()+"','"+o.getUserID()+"','"+o.getMessage()+"')";
		return DBUtil.executeSql(PENDING_GROUP_MEMBERS_INSERT_SQL+values);
	}

}
