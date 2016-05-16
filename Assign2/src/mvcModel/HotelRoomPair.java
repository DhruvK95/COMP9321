package mvcModel;

public class HotelRoomPair {
	public HotelDTO hotel;
	public RoomDTO room;

	public HotelRoomPair(HotelDTO h, RoomDTO r) {
		this.hotel = h;
		this.room = r;
	}
	public HotelDTO getHotel() {
		return hotel;
	}
	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}
	public RoomDTO getRoom() {
		return room;
	}
	public void setRoom(RoomDTO room) {
		this.room = room;
	}
	
}