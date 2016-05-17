package mvcModel;

import java.util.ArrayList;
import java.util.Date;

public class BookingDTO {
	public int id;
	public int customerID;
	public Date startDate;
	public Date endDate;
	public Boolean checkedIn;
	public ArrayList<RoomDTO> roomsInBooking;

	public BookingDTO() {
		super();
		startDate = new Date();
		endDate = new Date();
		roomsInBooking = new ArrayList<RoomDTO>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public void addRoomToBookings(RoomDTO r){
		roomsInBooking.add(r);
	}
	public void addAllRoomsBookings(ArrayList<RoomDTO> aR){
		roomsInBooking.addAll(aR);
	}

	public ArrayList<RoomDTO> getAllRooms(){
		return this.roomsInBooking;
	}




}
