package mvcModel;

public class CustomerDTO {
	public int id;
	public String user_name;
	public String password;	
	public String first_name;
	public String last_name;
	public String email;
	public String address;
    //credit card stuff
	public int cc_number;
	public String cc_name;
	public String cc_expiry;
	//Has the user clicked validation link
	public Boolean verified;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCc_number() {
		return cc_number;
	}
	public void setCc_number(int cc_number) {
		this.cc_number = cc_number;
	}
	public String getCc_name() {
		return cc_name;
	}
	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}
	public String getCc_expiry() {
		return cc_expiry;
	}
	public void setCc_expiry(String cc_expiry) {
		this.cc_expiry = cc_expiry;
	}
	public boolean isVerified(){
		return verified;
	}
	public void setVerified(boolean v){
		verified = v;
	}
}
