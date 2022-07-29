-- CREATE TABLES

CREATE TABLE user_roles (
	user_role_id SERIAL PRIMARY KEY,
	user_role_name TEXT UNIQUE NOT NULL
);

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username TEXT UNIQUE NOT NULL,
	PASSWORD TEXT NOT NULL,
	user_first_name TEXT NOT NULL,
	user_last_name TEXT NOT NULL,
	user_email TEXT UNIQUE NOT NULL,
	user_role_id_fk INT REFERENCES user_roles(user_role_id)
);

CREATE TABLE reimb_types (
	reimb_type_id SERIAL PRIMARY KEY,
	reimb_type_name TEXT UNIQUE NOT NULL
);

CREATE TABLE reimb_statuses (
	reimb_status_id SERIAL PRIMARY KEY,
	reimb_status_name TEXT
);

CREATE TABLE reimbs (
	reimb_id SERIAL PRIMARY KEY,
	reimb_amount DECIMAL(7, 2) NOT NULL,
	reimb_submitted TIMESTAMP DEFAULT NOW(),
	reimb_resolved TIMESTAMP DEFAULT NULL,
	reimb_description TEXT,
	reimb_receipt_url TEXT,
	reimb_status_id_fk INT REFERENCES reimb_statuses(reimb_status_id) DEFAULT 1,
	reimb_type_id_fk INT REFERENCES reimb_types(reimb_type_id),
	reimb_author_id_fk INT REFERENCES users(user_id),
	reimb_resolver_id_fk INT REFERENCES users(user_id) DEFAULT NULL
);

-- INSERT DATA

INSERT INTO user_roles (user_role_name)
VALUES ('Finance Manager'),
	   ('Employee');

INSERT INTO users (username, password, user_first_name, user_last_name, user_email, user_role_id_fk)
VALUES ('james.kirk', 'adminpass', 'James', 'Kirk', 'james@starfleet.com', 1),
	   ('nyota.uhura', 'employeepass', 'Nyota', 'Uhura', 'nyota@starfleet.com', 2);
	  
INSERT INTO reimb_types (reimb_type_name)
VALUES ('Lodging'),
	   ('Travel'),
	   ('Food'),
	   ('Other');
	  
INSERT INTO reimb_statuses (reimb_status_name)
VALUES ('Pending'),
	   ('Denied'),
	   ('Approved');

INSERT INTO reimbs (reimb_amount, reimb_description, reimb_receipt_url, reimb_type_id_fk, reimb_author_id_fk)
VALUES (75.29, 'Maggianos', 'https://res.cloudinary.com/hsmrn1b8m/image/upload/v1658360745/project1/istockphoto-889405434-612x612_rqjywv.jpg', 3, 2)

-- ACCESS AND MANIPULATE

SELECT * FROM reimbs;
DROP TABLE reimbs CASCADE;

