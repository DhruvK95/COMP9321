CREATE TABLE hotel (
	id 					INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
	name				VARCHAR(40) NOT NULL,
	location			VARCHAR(40) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE room_type (
	id 					INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
	price				INTEGER NOT NULL,
	CONSTRAINT valid_price CHECK (price>=0),
	PRIMARY KEY (id)
);

CREATE TABLE room (
	id					INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
	room_type_fk		INTEGER NOT NULL,
	hotel_fk			INTEGER NOT NULL,
	status 				VARCHAR(20) NOT NULL,
-- 	booking_fk
	CONSTRAINT check_status CHECK (status='BOOKED' OR status='OCCUPIED' OR  status='AVAILABLE'),
	PRIMARY KEY (id),
	FOREIGN KEY (room_type_fk) REFERENCES room_type (id),
	FOREIGN KEY (hotel_fk) REFERENCES hotel (id)
);


CREATE TABLE customer (
	id 					INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
	user_name 			VARCHAR(40) NOT NULL,
	first_name			VARCHAR(20) NOT NULL,
	last_name			VARCHAR(20) NOT NULL,
	email				VARCHAR(100) NOT NULL,
	address				VARCHAR(200) NOT NULL,
	cc_number   		INTEGER,
	cc_name				VARCHAR(40),
	cc_expiry			VARCHAR(40),
	PRIMARY KEY (id)
);


