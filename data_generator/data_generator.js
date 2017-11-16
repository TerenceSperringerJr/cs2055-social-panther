/*
	tps23
	
	Data Generator for Social@Panther
	
	User information generated by Random User Generator (https://randomuser.me/)
	Thank you, Random User Generator =)
	
	Groups generated by D20SRD Fantasy Party Generator (http://5e.d20srd.org/fantasy/name/#type=setting;setting=Party)
	Thank you, D20SRD
	
	Messages generated by Game of Thrones Quotes API (https://github.com/wsizoo/game-of-thrones-quotes)
	Thank you, Game of Thrones Quotes API
*/

"use strict";

var DATA_GENERATOR;

$(document).ready(function() {
	var randomUserAPI = 'https://randomuser.me/api/',
		fantasyGroupGeneratorAPI = 'http://5e.d20srd.org/name/rpc.cgi?type=Party',
		quotesAPI = 'https://got-quotes.herokuapp.com/quotes',
		userCount = 100,
		//friendships = 200,
		groupCount = 10,
		messageCount = 300,
		visualOutput = $("#output")[0];
	
	function DataGenerator() {
		this.messages = [];
		this.users;
		this.friends = [];
		this.groups;
		
		return;
	}
	
	DataGenerator.prototype.processData = function() {
		if((this.messages.length < messageCount) || (this.users.length < userCount)) {
			return;
		}
		
		this.makeFriends();
		
		this.createSQL();
		
		return;
	}
	
	DataGenerator.prototype.generateData = function() {
		this.getMessages();
		this.getGroups();
		this.getUsers();
		
		return;
	}
	
	DataGenerator.prototype.getMessages = function() {
		var i;
		
		for(i = 0; i < messageCount; i++) {
			$.ajax({
				type: "GET",
				url: quotesAPI,
				dataType: 'json',
				success: function(result, status, xhr) {
					DATA_GENERATOR.messages.push(result.quote.replace(/[']/g, "''"));
					DATA_GENERATOR.processData();
					
					return;
				}
			});
		}
		
		return;
	}
	
	DataGenerator.prototype.getUsers = function() {
		$.ajax({
			type: "GET",
			url: randomUserAPI + '?results=' + userCount,
			dataType: 'json',
			success: function(result, status, xhr) {
				DATA_GENERATOR.users = result.results;
				//console.log(DATA_GENERATOR.users);
				
				DATA_GENERATOR.processData();
				
				return;
			}
		});
		
		return;
	}
	
	function getRandomFriend(userID) {
		var friendID = userID;
		
		while(friendID === userID) {
			friendID = Math.floor(Math.random() * 100);
		}
		
		return friendID;
	}
	
	DataGenerator.prototype.makeFriends = function() {
		var i,
			friendID,
			friend2ID;
		
		for(i = 0; i < this.users.length; i++) {
			friendID = getRandomFriend(i);
			this.friends.push({userID: i, friendID: friendID});
			
			friend2ID = friendID;
			while(friend2ID === friendID) {
				friend2ID = getRandomFriend(i);
			}
			
			this.friends.push({userID: i, friendID: friend2ID});
		}
		
		return;
	}
	
	DataGenerator.prototype.getGroups = function() {
		/*
		$.ajax({
			type: "GET",
			dataType: "text/plain",
			xhrFields:{
				withCredentials: true
			},
			url: fantasyGroupGeneratorAPI + '&n=' + groupCount,
			error: function(xhr, status, error) {
				console.log(xhr);
				console.log(status);
				console.log(error);
				
				return;
			},
			success: function(result, status, xhr) {
				DATA_GENERATOR.groups = result;
				console.log(DATA_GENERATOR.groups);
				
				return;
			}
		});
		*/
		
		return;
	}
	
	function appendBR() {
		return visualOutput.append(document.createElement("br"));
	}
	
	DataGenerator.prototype.createSQL = function() {
		var i,
			date = new Date(),
			dateString = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(),
			div = document.createElement("div");
		
		div.innerHTML = "<strong>--INSERT USERS:</strong>";
		visualOutput.append(div);
		for(i = 0; i < this.users.length; i++) {
			div = document.createElement("div");
			div.innerHTML = "<span>INSERT INTO PROFILE (userID, name, email, password, date_of_birth) VALUES (" +
				i + ", " +
				"'" + this.users[i].name.title + ' ' + this.users[i].name.first + ' ' + this.users[i].name.last + "', " +
				"'" + this.users[i].email + "'," +
				"'" + this.users[i].login.password + "', " +
				"'" + this.users[i].dob + "');</span>";
			visualOutput.append(div);
		}
		appendBR();
		
		
		div.innerHTML = "<strong>--INSERT FRIENDSHIPS:</strong>";
		visualOutput.append(div);
		for(i = 0; i < this.friends.length; i++) {
			div = document.createElement("div");
			div.innerHTML = "<span>INSERT INTO FRIENDS (userID1, userID2, JDate, message) VALUES (" +
				this.friends[i].userID + ", " +
				this.friends[i].friendID + ", " +
				"'" + dateString + "', " +
				"'Hello, " + this.users[this.friends[i].friendID].name.title +
				' ' + this.users[this.friends[i].friendID].name.first +
				' ' + this.users[this.friends[i].friendID].name.last +
				". Please accept my friendship.');</span>";
			visualOutput.append(div);
		}
		appendBR();
		
		div.innerHTML = "<strong>--INSERT MESSAGES:</strong>";
		visualOutput.append(div);
		for(i = 0; i < this.messages.length; i++) {
			div = document.createElement("div");
			div.innerHTML = "<span>INSERT INTO MESSAGES (fromID, message, toUserID, toGroupID, dateSent) VALUES (" +
				Math.floor(Math.random() * 100) + ", " +
				"'" + this.messages[i] + "', ";
			
			if((Math.floor(Math.random() * 10) % 10) === 0) {
				div.innerHTML = "NULL," + div.innerHTML + Math.floor(Math.random() * 10) + ", ";
			}
			else {
				div.innerHTML = div.innerHTML + Math.floor(Math.random() * 100) + ", NULL, ";
			}
			
			div.innerHTML = div.innerHTML + dateString + "');</span>";
			visualOutput.append(div);
		}
		appendBR();
		
		
		return;
	}
	
	DATA_GENERATOR = new DataGenerator();
	return;
});
