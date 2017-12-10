-- Social@Panther
-- tps23
-- Subroutines


-- Given a name, email address, and date of birth, add a new user to the system by inserting as new entry in the profile relation.
create or replace function
CREATE_USER(USERNAME IN VARCHAR2, USER_EMAIL IN VARCHAR2, USER_DATE_OF_BIRTH IN DATE)
	return VARCHAR2
IS
	OUTPUT_STRING VARCHAR2 (255) := 'RESULT: ';
	ENTRY_ID INTEGER := -1;
	USER_PASSWORD VARCHAR2 (50);
	USER_ID VARCHAR2 (20);
begin
	SELECT COUNT(USERID) INTO ENTRY_ID FROM PROFILE;
	ENTRY_ID := ENTRY_ID + 1;
	USER_ID := USERNAME || ENTRY_ID;
	USER_PASSWORD := USER_ID;
	
	INSERT INTO PROFILE(USERID, NAME, EMAIL, PASSWORD, DATE_OF_BIRTH)
		VALUES(USER_ID, USERNAME, USER_EMAIL, USER_PASSWORD, USER_DATE_OF_BIRTH);
	
	OUTPUT_STRING := OUTPUT_STRING || SQL%ROWCOUNT;
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
	
	return USER_ID;
END CREATE_USER;
/


-- Given userID and password, login as the user in the system when an appropriate match is found.
create or replace function
LOGIN(USER_ID IN VARCHAR2, USER_PASSWORD IN VARCHAR2)
	return TIMESTAMP
IS
	OUTPUT_STRING VARCHAR2 (255) := 'RESULT: ';
	LOGIN_TIME TIMESTAMP;
begin
	UPDATE PROFILE SET LASTLOGIN = CURRENT_TIMESTAMP
		WHERE USERID = USER_ID AND PASSWORD = USER_PASSWORD;
	
	SELECT LASTLOGIN INTO LOGIN_TIME FROM PROFILE WHERE USERID = USER_ID;
	
	OUTPUT_STRING := OUTPUT_STRING || SQL%ROWCOUNT;
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
	
	return LOGIN_TIME;
END LOGIN;
/


-- Remove a user and all of their information from the system.
-- When a user is removed, the system should delete the user from the groups he or she was a member of using a trigger.
-- Note: messages require special handling because they are owned by both sender and receiver.
-- Therefore, a message is deleted only when both he sender and all receivers are deleted.
-- Attention should be paid handling integrity constraints.
create or replace procedure
DROP_USER(USER_ID in varchar2)
IS
	OUTPUT_STRING VARCHAR2 (255) := 'RESULT: ';
begin
	delete from PROFILE where USERID = USER_ID;
	--write trigger to handle all information owned solely by user on delete
	
	OUTPUT_STRING := OUTPUT_STRING || SQL%ROWCOUNT;
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
	
END DROP_USER;
/


-- Given two users (A and B), find a path, if one exists, between A and B with at most 3 hop between them. A hop is defined as a friendship between any two users.
create or replace procedure
THREE_DEGREES(USER_A in varchar2, USER_B in varchar2)
IS
	OUTPUT_STRING VARCHAR2 (255) := 'RESULT: ';
begin
	--Test Direct Friendship
	
	--Test Shared Friend(s)
	
	--Test Friends who are Friends with Friend's Friends
	
	--OUTPUT_STRING || SQL%ROWCOUNT;
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END THREE_DEGREES;
/


-- This option should cleanly shut down and exit the program after marking the time of the user's logout in the profile relation
create or replace procedure
LOG_OUT
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END LOG_OUT;
/



/* --------------------------------------- */
/* THIS STUFF NEEDS TO BE IN THE JAVA APP! */
/* --------------------------------------- */

-- Create a pending friendship from the (logged in) user to another user based on userID.
-- The application should display the name of the person that will be sent a friends request and the user should be prompted to enter a message to be sent along with the request.
-- A last confirmation should be requested of the user before an entry is inserted into the pendingFriends relation, and success or failure feedback is displayed for the user.
create or replace procedure
INITIATE_FRIENDSHIP(USER_ID in varchar2)
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END INITIATE_FRIENDSHIP;
/


-- This task should first display a formatted, numbered list of all outstanding friends and group requests with an associated messages.
-- Then, the user should be prompted for a number of the request he or she would like to confirm or given the option to confirm them all.
-- The application should move the request from the appropriate pendingFriends or pendingGroupmembers relation to the friends or groupMembership relation.
-- The remaining requests which were not selected are declined and removed from pendingFriends and pendingGroupmembers relations.
create or replace procedure
CONFIRM_FRIENDSHIP
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END CONFIRM_FRIENDSHIP;
/


-- This task supports the browsing of the user's friends and of their friends' profiles.
-- It first displays each of the user's friends' names and userIDs and those of any friend of those friends.
-- Then it allows the user to either retrieve a friend's entire profile by entering the appropriate userID or exit browsing and return to the main menu by entering 0 as a userID.
-- When selected, a friend's profile should be displayed in a nicely formatted way,
-- after which the user should be prompted to either select to retrieve another friend's profile or return to the main menu.
create or replace procedure
DISPLAY_FRIENDS
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END DISPLAY_FRIENDS;
/


-- Given a name, description, and membership limit, add a new group to the system, add the user as its first member with the role manager.
create or replace procedure
CREATE_GROUP(NAME IN VARCHAR2, DESCRIPTION IN VARCHAR2, MEMBER_LIMIT IN INTEGER)
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END CREATE_GROUP;
/


-- Given a user and a group, create a pending request of adding to group (if not violate the group's membership limit).
-- The user should be prompted to enter a message to be sent along with the request and inserted in the pendingGroupmembers relation.
create or replace procedure
INITIATE_ADDING_GROUP(USER_ID IN VARCHAR2, GROUP_ID IN VARCHAR2)
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END INITIATE_ADDING_GROUP;
/


-- With this the user can send a message to one friend given his userID.
-- The application should display the name of the recipient and the user should be prompted to enter the text of the message, which could be multi-lined.
-- Once entered, the message should be \sent" to the user by adding appropriate entries into the messages and message Recipients relations by creating a trigger.
-- The user should lastly be shown success or failure feedback.
create or replace procedure
SEND_MESSAGE_TO_USER(FRIEND_ID IN VARCHAR2)
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END SEND_MESSAGE_TO_USER;
/


-- With this the user can send a message to a recipient group, if the user is within the group.
-- Every member of this group should receive the message.
-- The user should be prompted to enter the text of the message, which could be multi-lined.
-- Then the created new message should be \sent" to the user by adding appropriate entries into the messages and messageRecipients relations by creating a trigger.
-- The user should lastly be shown success or failure feedback.
-- Note that if the user sends a message to one friend, you only need to put the friend's userID to ToUserID in the table of messages.
-- If the user wants to send a message to a group, you need to put the group ID to ToGroupID in the table of messages
-- and use a trigger to populate the messageRecipient table with proper user ID information as defined by the groupMembership relation.
create or replace procedure
SEND_MESSAGE_TO_GROUP(GROUP_ID IN VARCHAR2, MESSAGE IN VARCHAR2)
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END SEND_MESSAGE_TO_GROUP;
/


-- When the user selects this option, the entire contents of every message sent to the user should be displayed in a nicely formatted way.
create or replace procedure
DISPLAY_MESSAGES
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END DISPLAY_MESSAGES;
/


-- This should display messages in the same fashion as the previous task except that only those messages sent since the last time the user logged into the system should be displayed.
create or replace procedure
DISPLAY_NEW_MESSAGES
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END DISPLAY_NEW_MESSAGES;
/


-- Given a string on which to match any user in the system, any item in this string must be matched against any significant field of a user's profile.
-- That is if the user searches for \xyz abc", the results should be the set of all profiles that match \xyz" union the set of all profiles that matches \abc"
create or replace procedure
SEARCH_FOR_USER(SEARCH_STRING IN VARCHAR2)
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	--SELECT * FROM PROFILE WHERE ;
	
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END SEARCH_FOR_USER;
/


-- Display top K who have sent to or received the highest number of messages during for the past x months. x and K are input parameters to this function.
create or replace procedure
TOP_MESSAGES
IS
	OUTPUT_STRING VARCHAR2 (255) := 'IMPLEMENT ME!';
begin
	DBMS_OUTPUT.put_line(OUTPUT_STRING);
END TOP_MESSAGES;
/


