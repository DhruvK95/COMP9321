SELECT * FROM ROOM;
SELECT r.id, r.hotel_fk, rt.price, rt.bedType, rt.numBeds , rt.name FROM room r, room_type rt, hotel h
	WHERE r.room_type_fk = rt.id AND r.hotel_fk=h.id;
	
SELECT r.id FROM BOOKING_ON_ROOMS bor, BOOKING b, ROOM r WHERE bor.booking_fk=b.id AND r.id = bor.room_fk AND bor.booking_fk=..;