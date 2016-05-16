SELECT * FROM room;
SELECT r.id, r.hotel_fk, rt.price, rt.bedType, rt.numBeds , rt.name FROM room r, room_type rt, hotel h
	WHERE r.room_type_fk = rt.id AND r.hotel_fk=h.id;
SELECT d.id, rt.name, d.discount_price, d.hotel_fk, d.start_date, d.end_date FROM discount d ,room_type rt WHERE d.room_type_fk=rt.id;
SELECT r.id FROM BOOKING_ON_ROOMS bor, BOOKING b, ROOM r WHERE bor.booking_fk=b.id AND r.id = bor.room_fk AND bor.booking_fk=..;



SELECT b.id, rt.name, h.name FROM BOOKING b, BOOKING_ON_ROOMS bor, ROOM r, ROOM_TYPE rt, HOTEL h WHERE r.HOTEL_FK=h.id AND bor.booking_fk=b.id AND r.ROOM_TYPE_FK=rt.ID AND bor.ROOM_FK=r.ID;