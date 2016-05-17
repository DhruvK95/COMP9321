ALTER TABLE ROOM DROP CONSTRAINT ROOM_HOTEL_ID_FK;
ALTER TABLE ROOM DROP CONSTRAINT ROOM_ROOM_TYPE_ID_FK;
ALTER TABLE DISCOUNT DROP CONSTRAINT DISCOUNT_HOTEL_ID_FK;
ALTER TABLE DISCOUNT DROP CONSTRAINT DISCOUNT_ROOM_TYPE_FK;
ALTER TABLE BOOKING DROP CONSTRAINT BOOKING_CUSTOMER_ID_FK;
ALTER TABLE BOOKING_ON_ROOMS DROP CONSTRAINT ROOM_ID_FK;
ALTER TABLE BOOKING_ON_ROOMS DROP CONSTRAINT ROOM_BOOKING_ID_FK;
ALTER TABLE STAFF DROP CONSTRAINT STAFF_HOTEL_ID_FK;

DROP TABLE HOTEL;
DROP TABLE CUSTOMER;
DROP TABLE STAFF;
DROP TABLE ROOM_TYPE;
DROP TABLE BOOKING;
DROP TABLE ROOM;
DROP TABLE BOOKING_ON_ROOMS;
DROP TABLE DISCOUNT;


CREATE TABLE hotel (
  id 						INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  name					VARCHAR(40) NOT NULL,
  location			VARCHAR(40) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE customer (
  id 						INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  user_name 		VARCHAR(40) NOT NULL,
  password  		VARCHAR(40) NOT NULL,
  first_name		VARCHAR(20) NOT NULL,
  last_name			VARCHAR(20),
  email					VARCHAR(100),
  address				VARCHAR(200),
  cc_number   	INTEGER,
  cc_name				VARCHAR(40),
  cc_expiry			VARCHAR(40),
  verified			boolean,
  PRIMARY KEY (id)
);


CREATE TABLE staff (
  id              INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  first_name      VARCHAR(20),
  last_name       VARCHAR(20),
  username        VARCHAR(50) NOT NULL UNIQUE,
  password        VARCHAR(40) NOT NULL,
  is_owner        boolean NOT NULL,
  hotel_fk        INTEGER,
  CONSTRAINT staff_HOTEL_ID_FK FOREIGN KEY (hotel_fk) REFERENCES hotel (id),
  PRIMARY KEY (id)
);


CREATE TABLE room_type (
  id 						INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  price						FLOAT NOT NULL,
  bedType					BOOLEAN NOT NULL,	--Single, Double
  numBeds					INTEGER NOT NULL,
  name						VARCHAR(20),
  CONSTRAINT valid_bed 		CHECK(numBeds > 0),
  CONSTRAINT valid_price 	CHECK(price>=0),
  PRIMARY KEY (id)
);


CREATE TABLE booking (
  id              INTEGER PRIMARY KEY NOT NULL NOT NULL GENERATED ALWAYS AS IDENTITY,
  start_date      DATE NOT NULL,
  end_date        DATE NOT NULL,
  customer_fk     INTEGER,
  checked_in	  BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT valid_booking_dates CHECK (start_date <= end_date),
  CONSTRAINT booking_CUSTOMER_ID_fk FOREIGN KEY (customer_fk) REFERENCES CUSTOMER (ID)
);
  

CREATE TABLE room (
  id					INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  room_type_fk			INTEGER NOT NULL,
  hotel_fk				INTEGER NOT NULL,
  room_Availability		BOOLEAN NOT NULL,
  checked_in			BOOLEAN NOT NULL DEFAULT FALSE,
  
  PRIMARY KEY (id),
  CONSTRAINT room_ROOM_TYPE_ID_FK FOREIGN KEY (room_type_fk) REFERENCES room_type (id),
  CONSTRAINT room_HOTEL_ID_FK FOREIGN KEY (hotel_fk) REFERENCES hotel (id)
);


CREATE TABLE booking_on_rooms
(
 id					INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
 room_fk 			INTEGER,
 booking_fk			INTEGER,

 CONSTRAINT room_ID_FK FOREIGN KEY (room_fk) REFERENCES room (id),
 CONSTRAINT room_BOOKING_ID_FK FOREIGN KEY (booking_fk) REFERENCES booking (id)
);


CREATE TABLE discount (
  id              INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  room_type_fk    INTEGER NOT NULL,
  discount_price  FLOAT NOT NULL,
  hotel_fk        INTEGER NOT NULL,
  start_date      DATE NOT NULL,
  end_date        DATE NOT NULL,
  CONSTRAINT valid_dates CHECK (start_date <= end_date),
  CONSTRAINT discount_ROOM_TYPE_FK FOREIGN KEY (room_type_fk) REFERENCES room_type (id),
  CONSTRAINT discount_HOTEL_ID_FK FOREIGN KEY (hotel_fk) REFERENCES hotel(id),
  PRIMARY KEY (id)
);


--two hotels in each city, suburb included in name
INSERT INTO hotel VALUES (DEFAULT,'Parramatta Star ','Sydney');
INSERT INTO hotel VALUES (DEFAULT,'Town Hall Star ','Sydney');

INSERT INTO hotel VALUES (DEFAULT,'Willawong Bonanza','Brisbane');
INSERT INTO hotel VALUES (DEFAULT,'SunnyBank Bonanza','Brisbane');

INSERT INTO hotel VALUES (DEFAULT,'ParkVille Merry','Melbourne');
INSERT INTO hotel VALUES (DEFAULT,'Elwood Merry','Melbourne');

INSERT INTO hotel VALUES (DEFAULT,'Brentwood Pleasure','Perth');
INSERT INTO hotel VALUES (DEFAULT,'Calista Pleasure','Perth');

INSERT INTO hotel VALUES (DEFAULT,'Glenside Amazement','Adelaide');
INSERT INTO hotel VALUES (DEFAULT,'Leabrook Amazement','Adelaide');

INSERT INTO hotel VALUES (DEFAULT,'BridgeWater Horror','Hobart');
INSERT INTO hotel VALUES (DEFAULT,'Howden Horror','Hobart');

INSERT INTO customer VALUES (DEFAULT,'LeonisCool','123', 'Leon','Augustine', 'laugustine1@gmail.com', 'pert', 123456, 'Leon', '4/4/2015', true );
INSERT INTO customer VALUES (DEFAULT,'EdlanisShit','123', 'Edlan', 'Policarpio','eldanpolicarpioschool@gmail.com', 'hobar', 123456, 'Edlan', '4/4/2015', true);

INSERT INTO staff VALUES(DEFAULT, 'Mister', 'Manager', 'manager1', 'password', false, 1);
INSERT INTO staff VALUES(DEFAULT, 'Madame', 'Owner', 'owner1', 'password', true, null);

--Make Room Types
INSERT INTO room_type VALUES (DEFAULT,  80, FALSE, 1, 'Single');
INSERT INTO room_type VALUES (DEFAULT, 130, FALSE, 2, 'Twin');
INSERT INTO room_type VALUES (DEFAULT, 150, TRUE, 1, 'Queen');
INSERT INTO room_type VALUES (DEFAULT, 200, TRUE, 1, 'Executive');
INSERT INTO room_type VALUES (DEFAULT, 320, TRUE, 2, 'Suite');

--Single Rooms (Hotels 1 - 8, odd rooms have 2)
INSERT INTO room VALUES (DEFAULT, 1, 1, FALSE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 1, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 3, 1, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 2, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 3, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 3, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 4, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 5, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 5, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 6, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 7, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 7, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 8, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 9, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 1, 9, TRUE, DEFAULT);

--Double Rooms (Even num Hotels)
INSERT INTO room VALUES (DEFAULT, 2, 2, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 2, 4, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 2, 6, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 2, 8, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 2, 10, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 2, 12, TRUE, DEFAULT);

--Queen Rooms (multiples of 3)
INSERT INTO room VALUES (DEFAULT, 3, 3, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 3, 6, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 3, 9, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 3, 12, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 3, 12, TRUE, DEFAULT); --2 queen rooms

--Executive Rooms
INSERT INTO room VALUES (DEFAULT, 4, 9, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 4, 10, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 4, 11, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 4, 12, TRUE, DEFAULT);

--Suite
INSERT INTO room VALUES (DEFAULT, 5, 5, TRUE, DEFAULT);
INSERT INTO room VALUES (DEFAULT, 5, 6, TRUE, DEFAULT);

-- roombookings 
-- Make some test bookings
INSERT INTO booking VALUES (DEFAULT , '2016-01-01','2016-01-02' ,1, DEFAULT);
INSERT INTO booking VALUES (DEFAULT , '2016-01-04','2016-01-06' ,2, DEFAULT);

-- rooms in a booking
-- booking 1 one of each type
INSERT INTO booking_on_rooms VALUES (DEFAULT, 1, 1);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 15, 1);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 21, 1);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 26, 1);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 30, 1);
-- booking 2 random, but using room already booked with differend date range
INSERT INTO booking_on_rooms VALUES (DEFAULT, 1, 2);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 27, 2);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 20, 2);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 28, 2);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 11, 2);
INSERT INTO booking_on_rooms VALUES (DEFAULT, 9, 2);

INSERT INTO discount VALUES (DEFAULT, 1, 30, 1, '2016-01-01', '2016-01-02');
