--Social@Panther
--subroutine tests
--tps23

--Tests in this file will print errors upon failure

declare
	ALL_ERRORS integer := 0;
	
	-- General usage tests that should always pass and will print errors if they do not
	function GENERAL_TEST
		return integer
	is
		TEST_ERRORS integer := 0;
		TEST_USERID VARCHAR2 (20);
		TEST_FRIEND_ID varchar2 (20);
		TEST_FRIENDSHIP SUBROUTINES.FRIENDSHIP_DEGREE;
		TEST_NAME VARCHAR2 (50) := 'SirAstral';
		TEST_PASSWORD VARCHAR2 (50);
		TEST_EMAIL VARCHAR2 (64) := 'astral@granseal.dom';
		TEST_DOB DATE := TO_DATE('01-OCT-1993');
		TEST_LOGGED_IN boolean;
		TEST_DELETED boolean;
		TEST_LOGOUT_TIME timestamp;
	begin
		--CREATE_USER
		TEST_USERID := CREATE_USER(TEST_NAME, TEST_EMAIL, TEST_DOB);
		TEST_PASSWORD := TEST_USERID;
		
		if TEST_USERID = NULL then
			DBMS_OUTPUT.put_line('Error: GENERAL_TEST Failed CREATE_USER(' || TEST_NAME || ', ' || TEST_EMAIL || ', ' || TEST_DOB || ')');
			TEST_ERRORS := TEST_ERRORS + 1;
		end if;
		
		
		--LOGIN
		TEST_LOGGED_IN := LOGIN(TEST_USERID, TEST_PASSWORD);
		
		if TEST_LOGGED_IN = false then
			DBMS_OUTPUT.put_line('Error: GENERAL_TEST Failed LOGIN(' || TEST_USERID || ', ' || TEST_PASSWORD || ')');
			TEST_ERRORS := TEST_ERRORS + 1;
		end if;
		
		
		--THREE_DEGREES no relationship
		select USERID into TEST_FRIEND_ID from PROFILE where USERID = '1';
		TEST_FRIENDSHIP := THREE_DEGREES(TEST_USERID, TEST_FRIEND_ID);
		
		if TEST_FRIENDSHIP.USER1 <> null then
			DBMS_OUTPUT.put_line('Error: GENERAL_TEST Failed THREE_DEGREES(' || TEST_USERID || ', ' || TEST_FRIEND_ID || ') for false positive');
			TEST_ERRORS := TEST_ERRORS + 1;
		end if;
		
		--make friends with TEST_FRIEND_ID
		--INITIATE_FRIENDSHIP
		if INITIATE_FRIENDSHIP(TEST_USERID, TEST_FRIEND_ID, 'Dead men tell no tales.') = false then
			DBMS_OUTPUT.put_line('Error: GENERAL_TEST Failed INITIATE_FRIENDSHIP(' || TEST_USERID || ', ' || TEST_FRIEND_ID || ', Dead men tell no tales.)');
			TEST_ERRORS := TEST_ERRORS + 1;
		else
			--CONFIRM_FRIENDSHIP
			if CONFIRM_FRIENDSHIP(TEST_USERID, TEST_FRIEND_ID) = false then
				DBMS_OUTPUT.put_line('Error: GENERAL_TEST Failed CONFIRM_FRIENDSHIP(' || TEST_USERID || ', ' || TEST_FRIEND_ID || ')');
				TEST_ERRORS := TEST_ERRORS + 1;
			end if;
		end if;
		
		
		--THREE_DEGREES direct friendship
		/*
		if (TEST_FRIENDSHIP.USER1 = null) or (TEST.FRIENDSHIP_DEGREE.USER2 = null) then
			DBMS_OUTPUT.put_line('Error: GENERAL_TEST Failed THREE_DEGREES(' || TEST_USERID || ', ' || TEST_FRIEND_ID || ') for false positive');
			TEST_ERRORS := TEST_ERRORS + 1;
		end if;
		*/
		
		--THREE_DEGREES common friend
		
		--THREE_DEGREES friends are friends
		
		
		--LOGOUT
		if TEST_LOGGED_IN = true then
			TEST_LOGOUT_TIME := LOG_OUT(TEST_USERID);
			
			if TEST_LOGOUT_TIME = NULL then
				DBMS_OUTPUT.put_line('Error: GENERAL_TEST Failed LOG_OUT(' || TEST_USERID || ')');
				TEST_ERRORS := TEST_ERRORS + 1;
			end if;
		end if;
		
		--DROP USER
		TEST_DELETED := DROP_USER(TEST_USERID);
		
		if TEST_DELETED = false then
			DBMS_OUTPUT.put_line('Error: GENERAL_TEST Failed DROP_USER(' || TEST_USERID || ')');
			TEST_ERRORS := TEST_ERRORS + 1;
		end if;
		
		return TEST_ERRORS;
	end;

--TODO: make REJECTION_TEST
	--Rejection tests that should always fail (prints errors if they succeed)
	function REJECTION_TEST
		return integer
	is
		TEST_ERRORS integer := 0;
	begin
		return 0;
	end;

begin
	ALL_ERRORS := ALL_ERRORS + GENERAL_TEST();
	ALL_ERRORS := ALL_ERRORS + REJECTION_TEST();
	
	--Print summary
	if ALL_ERRORS > 0 then
		DBMS_OUTPUT.put_line('All tests had '|| ALL_ERRORS ||' error(s)!!');
	else
		DBMS_OUTPUT.put_line('All tests passed with no errors =)');
	end if;
end;
/
