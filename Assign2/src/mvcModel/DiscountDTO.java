package mvcModel;

import java.sql.Date;


public class DiscountDTO {
	public int id;
	public String typeOfRoom;
	public float discountPercent;
	public int parentHotelID;
	public Date startDate;
	public Date endDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeOfRoom() {
		return typeOfRoom;
	}
	public void setTypeOfRoom(String typeOfRoom) {
		this.typeOfRoom = typeOfRoom;
	}
	public int getParentHotelID() {
		return parentHotelID;
	}
	public void setParentHotelID(int parentHotelID) {
		this.parentHotelID = parentHotelID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date date) {
		this.startDate = date;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public float getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}
}
