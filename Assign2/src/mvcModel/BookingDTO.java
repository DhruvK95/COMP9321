package mvcModel;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

//Booking SQL table
//CREATE TABLE booking
//(
//  id              INTEGER PRIMARY KEY NOT NULL,
//  start_date      DATE NOT NULL,
//  end_date        DATE NOT NULL,
//  hotel_fk        INTEGER NOT NULL,
//  customer_fk     INTEGER,
//  CONSTRAINT valid_booking_dates CHECK (start_date <= end_date),
//  CONSTRAINT booking_CUSTOMER_ID_fk FOREIGN KEY (customer_fk) REFERENCES CUSTOMER (ID),
//  CONSTRAINT booking_HOTEL_ID_fk FOREIGN KEY (hotel_fk) REFERENCES HOTEL (ID)
//);


public class BookingDTO {
	public int id;
	public int hotelID;
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

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
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
