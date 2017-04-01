create table USERS (
id int(5) NOT NULL auto_increment primary key,
user_name varchar(50),
org_code varchar(50),
passwd varchar(50),
org_id INTEGER(10),
is_inuse INTEGER(10)
);

create table ISSUE_TASK(
ID INT(10) NOT NULL auto_increment primary KEY,
ISSUE_ID VARCHAR(100),
TASK_ID VARCHAR(100),
ISSUE_NAME VARCHAR(100),
TASK_NAME VARCHAR(100),
CREATE_USER VARCHAR(100),
CREATE_DATE DATE,
KEYWORDS VARCHAR(100),
PROCESS_INSTANCE_ID VARCHAR(100)
);
