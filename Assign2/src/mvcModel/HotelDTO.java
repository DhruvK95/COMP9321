package mvcModel;

import java.util.ArrayList;

public class HotelDTO {
	public int id;
	public String hotelName;
	public String location; //city
	public ArrayList<RoomDTO> rooms;

	public HotelDTO() {
		super();
		rooms = new ArrayList<RoomDTO>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void addToRooms(RoomDTO room){
		this.rooms.add(room);
	}
	
	public ArrayList<RoomDTO> getRooms() {
		return rooms;
	}

	public String getLocationForID(Integer id) {

		return null;
	}

	@Override
	public String toString() {
		return hotelName;
	}
}
