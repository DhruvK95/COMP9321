package mvcModel;

import java.util.ArrayList;

public class DBStorageDTO {
	public ArrayList<HotelDTO> hotels;
	public ArrayList<CustomerDTO> customers;
	public ArrayList<BookingDTO> bookings;
	
	public DBStorageDTO() {
		super();
		this.hotels = new ArrayList<HotelDTO>();
		this.customers = new ArrayList<CustomerDTO>();
		this.bookings = new ArrayList<BookingDTO>();
	}
	
	public void addAllHotels(ArrayList<HotelDTO> hotels){
		this.hotels.addAll(hotels);
	}
	
	public void addAllCustomers(ArrayList<CustomerDTO> customers) {
		this.customers = customers;
	}
	
	public void addAllBookings(ArrayList<BookingDTO> bookings) {
		this.bookings = bookings;
	}
	
	public void addToHotels(HotelDTO hotel){
		this.hotels.add(hotel);
	}

	public void addToCustomers(CustomerDTO customer){
		this.customers.add(customer);
	}
	
	public void addToBookings(BookingDTO booking){
		this.bookings.add(booking);
	}

	public ArrayList<HotelDTO> getAllHotels() {
		return this.hotels;
	}
	
	public ArrayList<CustomerDTO> getAllCustomers() {
		return this.customers;
	}
	
	public ArrayList<BookingDTO> getAllBookings() {
		return this.bookings;
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
