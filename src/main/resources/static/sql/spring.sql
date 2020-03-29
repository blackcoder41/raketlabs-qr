DROP TABLE IF EXISTS users, user_roles, persistent_logins;

CREATE TABLE IF NOT EXISTS "users" (
  "id" SERIAL,
  "username" VARCHAR(45) NOT NULL,
  "password" VARCHAR(200) NOT NULL,
  PRIMARY KEY ("id")
);


CREATE TABLE IF NOT EXISTS "user_roles" (
  "id" SERIAL,
  "username" varchar(45) NOT NULL,
  "role" varchar(45) NOT NULL,
  PRIMARY KEY ("id")
);


CREATE TABLE IF NOT EXISTS "persistent_logins" (
  "username" varchar(64) NOT NULL,
  "series" varchar(64) NOT NULL,
  "token" varchar(64) NOT NULL,
  "last_used" timestamp NOT NULL,
  PRIMARY KEY ("series")
);

INSERT INTO USERS(USERNAME,PASSWORD) VALUES('admin','$2a$10$Ii8O.wtxIeYQuXcbhcQ/WOngxwb1KrnAVOOLi6SaW8KfPWq7O6tsa');

INSERT INTO USER_ROLES(USERNAME,ROLE) VALUES('admin','ROLE_ADMIN');

SELECT * FROM users, user_roles, persistent_logins;