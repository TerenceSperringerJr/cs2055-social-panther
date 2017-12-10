package com.oracle.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  
import java.util.List;
import java.util.Map; 

import com.oracle.db.*;
import com.oracle.entity.*; 
 

public class Phase {
	  
	private static BufferedReader buff = new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void createUser() throws IOException{  
		System.out.println("Enter String as your name");
		String name = buff.readLine();
		System.out.println("Enter String as your email address");
		String email = buff.readLine();
		System.out.println("Enter String as your date of brith, such as 2012-02-24");
		String date = buff.readLine();  
		if(Insert.profile(new Profile("",name,email,"123456",date,""))){
			System.out.println("createUser successfully");
		}else{
			System.out.println("createUser fail");
		}
	}
	
	public static Profile Login(Profile userexist) throws IOException{ 
		if(userexist!=null){
			System.out.println("Already logged in, please logout first");  
			return userexist;
		}else{
		System.out.println("Enter String as your userID");
		String userID = buff.readLine();
		System.out.println("Enter String as your password");
		String password = buff.readLine();
		Map<String,String> map = Query.profile(new Profile(userID,"","",password,"","")); 
		if(map!=null){
			userexist = new Profile(map.get("USERID"),map.get("NAME"),map.get("EMAIL"),map.get("PASSWORD"),map.get("DATE_OF_BIRTH"),map.get("LASTLOGIN"));
			if(Update.profile(userexist)){
				System.out.println("LogOut successfully"); 
				return userexist;
			}else{
				System.out.println("LogOut fail");
				return null;
			} 
		}else{
			System.out.println("Login fail");
			return null;
		}
		}
	}
	
	public static void initiateFriendship(Profile userexist) throws IOException { 
		
		if(userexist==null){
			System.out.println("Not logged in, please login first");  
		}else{
			System.out.println("Your userID is "+userexist.getUserID()); 
			System.out.println("Enter String as toID"); 
			String toID = buff.readLine();
			System.out.println("Enter String as messages");
			String messages = buff.readLine(); 
			System.out.println("Are you sure to request, enter Y or N");
			String sure = buff.readLine();
			PendingFriends pf = new PendingFriends(userexist.getUserID(),toID,messages);
			boolean loop = true;
			while(loop){
				if(sure.equals("Y")){
					if(Insert.pending_friends(pf)){
						System.out.println("initiateFriendship successfully");
					}else{
						System.out.println("initiateFriendship fail");
					} 
					loop = false; 
				}else if(sure.equals("N")){
					loop = false; 
				}else{
					System.out.println("Enter Y or N");
					sure = buff.readLine();
				}
			} 
		} 
	}
	
	public void confirmFriendship(){
		
	}

	public void displayFriends(){
		
	}

	public static void createGroup() throws IOException{
		System.out.println("Enter String as group name");
		String name = buff.readLine();
		System.out.println("Enter String as group description");
		String description = buff.readLine();
		if(Insert.groups(new Groups("",name,description))){
			System.out.println("createGroup successfully");
		}else{
			System.out.println("createGroup fail");
		}
	}

	public static void initiateAddingGroup() throws IOException{
		System.out.println("Enter String as GID");
		String gID = buff.readLine();
		System.out.println("Enter String as userID");
		String userID = buff.readLine();
		System.out.println("Enter String as message");
		String message = buff.readLine();
		if(Insert.pendingGroupmembers(new PendingGroupmembers(gID,userID,message))){
			System.out.println("initiateAddingGroup successfully");
		}else{
			System.out.println("initiateAddingGroup fail");
		}
	}

	public static void sendMessageToUser(Profile userexist) throws IOException{
		if(userexist==null){
			System.out.println("Not logged in, please login first");  
		}else{  
			List<Map<String, String>> friendslist = DBUtil.getList("select * from  FRIENDS where userid1='"+userexist.getUserID()+"' or userid2='"+userexist.getUserID()+"'");
			if(friendslist.size()==0){
				System.out.println("No Friends");
			}else{
				System.out.println("You have the following friends:"); 
				for(Map<String, String> map:friendslist){ 
					if(userexist.getUserID().equals(map.get("USERID1"))){
						System.out.println("userID: "+map.get("USERID2")); 
					}else{
						System.out.println("userID: "+map.get("USERID1")); 
					} 
				}
			}
			System.out.println("Select your friend ID ");
			String toUserID  = buff.readLine();
			System.out.println("Enter String as your message:");
			String message  = buff.readLine();
			if(Insert.message(new Messages("",userexist.getUserID(),message,toUserID,"", ""))){
				System.out.println("sendMessageToUser successfully");
			}else{
				System.out.println("sendMessageToUser fail");
			}
		}
	}

	public void sendMessageToGroup(){
		
	}

	public static void displayMessages(Profile userexist){
		if(userexist==null){
			System.out.println("Not logged in, please login first");  
		}else{
			String sql = "select * from MESSAGES where msgid in (select msgid from MESSAGE_RECIPIENT where userid ='"+userexist.getUserID()+"')";
 			List<Map<String, String>> msgList = DBUtil.getList(sql);
			System.out.println("your message record : ");
			if(msgList.size() == 0){
				System.out.println("NO MESSAGES");
			}else{ 
				for(Map<String, String> map:msgList){ 
					System.out.println(map.get("DATESENT")+"    FROM:"+map.get("FROMID")+"   MESSAGE:"+map.get("MESSAGE"));
				}
			}
		}
	}

	public static void displayNewMessages(Profile userexist){
		if(userexist==null){
			System.out.println("Not logged in, please login first");  
		}else{
			System.out.println("your lastLogin : "+userexist.getLastlogin());
			String sql = "select * from MESSAGES where dateSent > to_date('"+userexist.getLastlogin()+"','YYYY-MM-DD HH24:MI:SS') and "
					+ " msgid in (select msgid from MESSAGE_RECIPIENT where userid ='"+userexist.getUserID()+"')";
 			List<Map<String, String>> msgList = DBUtil.getList(sql);
			System.out.println("your lastLogin of messages record : ");
			if(msgList.size() == 0){
				System.out.println("NO MESSAGES");
			}else{ 
				for(Map<String, String> map:msgList){ 
					System.out.println(map.get("DATESENT")+"    FROM:"+map.get("FROMID")+"   MESSAGE:"+map.get("MESSAGE"));
				}
			}
		}
	}

	public void searcherForUser(){
		
	}

	public void threeDegress(){
		
	}

	public void topMessages(){
		
	}

	public void dropUser(){
		
	}
	
	public static boolean LogOut(Profile userexist) throws IOException{
		boolean result = false;
		if(userexist==null){
			System.out.println("Not logged in, please login first"); 
			result= false;
		}else{
		System.out.println("Are you sure log out, Y or N");
		String sure = buff.readLine(); 
		 
		boolean loop = true;
		while(loop){
			if(sure.equals("Y")){ 
				System.out.println("LogOut successfully");
				result= true;
				loop = false; 
			}else if(sure.equals("N")){
				loop = false; 
				result= false;
			}else{
				System.out.println("Enter Y or N");
				sure = buff.readLine();
			}
		}  
		}
		return result;
	}
  

	
}
