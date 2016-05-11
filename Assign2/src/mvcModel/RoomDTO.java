package mvcModel;

public class RoomDTO {
	public int id;
	public int roomType;
	public int parentHotel;
	public String status; //booked, status, available
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRoomType() {
		return roomType;
	}
	
	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	
	public int getParentHotel() {
		return parentHotel;
	}
	
	public void setParentHotel(int parentHotel) {
		this.parentHotel = parentHotel;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
