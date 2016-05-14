package mvcModel;

import java.util.ArrayList;

public class DBStorageDTO {
	public ArrayList<HotelDTO> hotels;
	public ArrayList<CustomerDTO> customers;
	
	public DBStorageDTO() {
		super();
		this.hotels = new ArrayList<HotelDTO>();
		this.customers = new ArrayList<CustomerDTO>();
	}
	
	public void addAllHotels(ArrayList<HotelDTO> hotels){
		this.hotels.addAll(hotels);
	}
	
	public void addToHotels(HotelDTO hotel){
		this.hotels.add(hotel);
	}
	

	public void addToCustomers(CustomerDTO customer){
		this.customers.add(customer);
	}

	public ArrayList<HotelDTO> getAllHotels() {
		return this.hotels;
	}
	
	public ArrayList<CustomerDTO> getAllCustomers() {
		return this.customers;
	}

	public void addAllCustomers(ArrayList<CustomerDTO> customers) {
		this.customers = customers;
	}
	
	public void refreshCustomer(CustomerDTO newCus){
		CustomerDTO oldCus = null;
		for(CustomerDTO c:customers){
			if(c.getId() == newCus.getId()){
				oldCus = c;
			}
		}
		customers.remove(oldCus);
		customers.add(newCus);
	}
	
}
