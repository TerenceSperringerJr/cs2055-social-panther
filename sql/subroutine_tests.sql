-- subroutine tests
-- tps23

-- tests that should always pass
declare
	TEST_USERID VARCHAR2 (20);
	TEST_NAME VARCHAR2 (50) := 'Sir Astral';
	TEST_PASSWORD VARCHAR (50);
	TEST_EMAIL VARCHAR2 (64) := 'astral@granseal.dom';
	TEST_DOB DATE := '01-OCT-1993';
	TEST_STAMP TIMESTAMP;
begin
	TEST_USERID := CREATE_USER(TEST_NAME, TEST_EMAIL, TEST_DOB);
	TEST_PASSWORD := TEST_USERID;
	TEST_STAMP := LOGIN(TEST_USERID, TEST_PASSWORD);
	
	
	
-- Uncomment for explicit output
DBMS_OUTPUT.put_line('REGISTERED PROFILE ID AS: ' || TEST_USERID);
DBMS_OUTPUT.put_line('LOGIN TIME: ' || TEST_STAMP);
	
	DROP_USER(TEST_USERID);
end;
/

-- TODO: make tests that should always fail
