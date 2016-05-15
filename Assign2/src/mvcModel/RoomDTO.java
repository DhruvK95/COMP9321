package mvcModel;

public class RoomDTO {
	public int id;
	public int parentHotelID;
	public float price;
	public String name; //single suite etc
	public boolean bedType; // single (o) double (l)
	public int numBeds;
	public int bookingAssociation; //can be null only assigned if a booking is made for this room, 
								   //info is then stored in the booking class
	
//	public String status; //booked, status, available
	
	public int getBookingAssociation() {
		return bookingAssociation;
	}
	public void setBookingAssociation(int bookingAssociation) {
		this.bookingAssociation = bookingAssociation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
//	public int getRoomType() {
//		return roomType;
//	}
//	
//	public void setRoomType(int roomType) {
//		this.roomType = roomType;
//	}
	
	public int getParentHotelID() {
		return parentHotelID;
	}
	
	public void setParentHotelID(int parentHotelID) {
		this.parentHotelID = parentHotelID;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isBedType() {
		return bedType;
	}
	
	public void setBedType(boolean bedType) {
		this.bedType = bedType;
	}
	
	public int getNumBeds() {
		return numBeds;
	}
	
	public void setNumBeds(int numBeds) {
		this.numBeds = numBeds;
	}
	
	
	
	
}
