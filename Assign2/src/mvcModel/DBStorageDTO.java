package mvcModel;

import java.util.ArrayList;

public class DBStorageDTO {
	public ArrayList<HotelDTO> hotels;
	public ArrayList<StaffDTO> staff;
	public ArrayList<CustomerDTO> customers;
	public ArrayList<BookingDTO> bookings;
	public ArrayList<DiscountDTO> discounts;
	
	
	public DBStorageDTO() {
		super();
		this.hotels = new ArrayList<HotelDTO>();
		this.staff = new ArrayList<StaffDTO>();
		this.customers = new ArrayList<CustomerDTO>();
		this.bookings = new ArrayList<BookingDTO>();
		this.discounts = new ArrayList<DiscountDTO>();
	}
	
	public void addAllHotels(ArrayList<HotelDTO> hotels){
		this.hotels.addAll(hotels);
	}
	public void addAllStaff(ArrayList<StaffDTO> staff) {
		this.staff = staff;
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

	public void addToStaff(StaffDTO staff){
		this.staff.add(staff);
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
	
	
	public ArrayList<StaffDTO> getAllStaff() {
		return this.staff;
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

	public void addRoomsToBooking(ArrayList<Integer> roomAssociationsID, int id) {
		for( int i : roomAssociationsID){
			for( HotelDTO h : hotels){
				for(RoomDTO r: h.getRooms()){
					if(r.getId() == i){
						for(BookingDTO b: bookings ){
							if(b.getId() == id){
								b.addRoomToBookings(r);
								b.getExtraBedCheck().put(r.getId(), false);
							}
						}
					}
				}
			}	
		}
		
		
	}
	
	public CustomerDTO findCutomer(String user){
		for(CustomerDTO c: customers){
			if(c.getUser_name().equals(user)){
				return c;
			}
		}
		return null;
	}
	
	public void addAllDiscounts(ArrayList<DiscountDTO> aD){
		this.discounts = aD;
	}
	
	public ArrayList<DiscountDTO> getAllDiscounts(){
		return this.discounts;
	}
	
	public RoomDTO findRoom(int id) {
		for(HotelDTO h: hotels){
			for(RoomDTO r: h.getRooms()){
				if(r.getId() == id){
					return r;
				}
			}
		}
		return null;
	}
	
	public ArrayList<BookingDTO> bookingsOnCustomer(int id){
		ArrayList<BookingDTO> customerBookings  =  new ArrayList<BookingDTO>();
		for( BookingDTO b : this.bookings){
			if(b.getCustomerID() == id){
				System.out.println(b.getCustomerID() + "   " + id);
				customerBookings.add(b);
			}
		}
		return customerBookings;
	}
	
}
