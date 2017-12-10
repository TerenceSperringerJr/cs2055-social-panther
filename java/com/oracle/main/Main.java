package com.oracle.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 

import com.oracle.entity.Profile;

public class Main {  
	public static void main(String args[]) throws IOException{
		
		Profile userexist = null; 
		
		String line = null;  
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
		System.out.println("======================================");
		System.out.println("please input number and press enter:");
		System.out.println("[1] createUser");
		System.out.println("[2] Login");
		System.out.println("[3] initiateFriendship");
		System.out.println("[4] createGroup"); 
		System.out.println("[5] initateAddingGroup"); 
		System.out.println("[6] sendMessageToUser");
		System.out.println("[7] displayMessages");
		System.out.println("[8] displayNewMessages"); 
		System.out.println("[9] Logout"); 
  
		line = buff.readLine();
		int keyNum = 0;
		try {
			keyNum = Integer.parseInt(line); 
		} catch (Exception e) {
			System.out.println("please input a number");
		}
		
		switch (keyNum) {
		case 1:
			System.out.println("begin createUser");  
			Phase.createUser();
			System.out.println("end createUser");
			break; 
		case 2:
			System.out.println("begin Login"); 
			userexist = Phase.Login(userexist);
 			System.out.println("end Login"); 
			break; 
		case 3:
			System.out.println("begin initiateFriendship"); 
			Phase.initiateFriendship(userexist); 
			System.out.println("end initiateFriendship");
			break; 
		case 4:
			System.out.println("begin createGroup"); 
			Phase.createGroup(); 
			System.out.println("end createGroup");
			break; 
		case 5:
			System.out.println("begin initateAddingGroup"); 
			Phase.initiateAddingGroup(); 
			System.out.println("end initateAddingGroup");
			break; 
		case 6:
			System.out.println("begin sendMessageToUser");
			Phase.sendMessageToUser(userexist);
			System.out.println("end sendMessageToUser");
			break; 
		case 7:
			System.out.println("begin displayMessages"); 
			Phase.displayMessages(userexist);
			System.out.println("end displayMessages");
			break; 
		case 8:
			System.out.println("begin displayNewMessages"); 
			Phase.displayNewMessages(userexist);
			System.out.println("end displayNewMessages");
			break;
		case 9:
			System.out.println("begin LogOut"); 
			if(Phase.LogOut(userexist)){
				userexist = null;
			} 
			System.out.println("end LogOut");
			break; 
		default: 
			break;
		}
	}
	}
	 
}
