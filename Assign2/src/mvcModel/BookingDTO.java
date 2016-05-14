package mvcModel;

import java.util.Date;

//Booking SQL table
//CREATE TABLE booking
//(
//  id              INTEGER PRIMARY KEY NOT NULL,
//  start_date      DATE NOT NULL,
//  end_date        DATE NOT NULL,
//  room_fk        INTEGER NOT NULL,
//  customer_fk     INTEGER,
//  CONSTRAINT valid_booking_dates CHECK (start_date <= end_date),
//  CONSTRAINT booking_CUSTOMER_ID_fk FOREIGN KEY (customer_fk) REFERENCES CUSTOMER (ID),
//  CONSTRAINT booking_ROOM_ID_fk FOREIGN KEY (room_fk) REFERENCES ROOM (ID)
//);


public class BookingDTO {
	public int id;
	public int roomID;
	public int customerID;
	public Date startDate;
	public Date endDate;
	
	public BookingDTO() {
		super();
		startDate = new Date();
		endDate = new Date();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date c) {
		startDate = c;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date c) {
		endDate = c;
	}
}
