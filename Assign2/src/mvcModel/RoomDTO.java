package mvcModel;

public class RoomDTO {
	public int id;
	public int parentHotelID;
	public float price;
	public String name; //single suite etc
	public boolean bedType; // single (o) double (l)
	public int numBeds;

	
//	public String status; //booked, status, available
	

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
