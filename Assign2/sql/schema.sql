DROP TABLE hotel;
CREATE TABLE hotel (
  id 						INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
  name					VARCHAR(40) NOT NULL,
  location			VARCHAR(40) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO hotel VALUES (DEFAULT,'leonHotel','LA');
INSERT INTO hotel VALUES (DEFAULT,'hotelB','ny');
INSERT INTO hotel VALUES (DEFAULT,'hotelC','syd');
