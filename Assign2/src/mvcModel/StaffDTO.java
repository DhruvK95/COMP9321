package mvcModel;

//CREATE TABLE staff
//(
//  id              INTEGER PRIMARY KEY NOT NULL,
//  first_name      VARCHAR(20),
//  last_name       VARCHAR(20),
//  username        VARCHAR(50) NOT NULL UNIQUE,
//  password        VARCHAR(40) NOT NULL,
//  is_owner        boolean NOT NULL,
//);

public class StaffDTO {
	public int id;
	public String first_name;
	public String last_name;
	public String username;
	public String password;
	public Boolean is_owner;
	public int hotel_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsOwner() {
		return is_owner;
	}

	public void setIsOwner(Boolean is_owner) {
		this.is_owner = is_owner;
	}

	public int getHotelID() {
		return hotel_id;
	}

	public void setHotelID(int hotel_id) {
		this.hotel_id = hotel_id;
	}
}
