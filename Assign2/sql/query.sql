SELECT * FROM CUSTOMER;
SELECT r.id, r.hotel_fk, rt.price, rt.bedType, rt.numBeds , rt.name FROM room r, room_type rt, hotel h
	WHERE r.room_type_fk = rt.id AND r.hotel_fk=h.id;