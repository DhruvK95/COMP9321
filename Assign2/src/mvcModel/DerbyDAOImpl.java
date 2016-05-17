package mvcModel;

import  java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**this can be used to
 * insert
 * update
 * search
 * and all other sql operations
 * once sql is sorted initate all datatypes of table
 * ie hotel, user etc
 *
 */
public class DerbyDAOImpl  {

	static Logger logger = Logger.getLogger(DerbyDAOImpl.class.getName());
	private Connection connection;

	public DerbyDAOImpl() throws SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	//checks everything in hotels
	public ArrayList<String> allHotels(){
		ArrayList<String> cast = new ArrayList<String>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM customer";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				String total = Integer.toString(res.getInt(1)).concat(res.getString(2)).concat(res.getString(3)).concat(res.getString(5)).concat(res.getString(5)).concat(res.getString(6));
				cast.add(total);
			}
			res.close();
			stmnt.close();

		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return cast;
	}

	//gets all hotels and puts them in a hotel arraylist
	public ArrayList<HotelDTO> initHotels(){

		ArrayList<HotelDTO> hotels = new ArrayList<HotelDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT id,name,location FROM hotel";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				HotelDTO currHotel = new HotelDTO();
				currHotel.setId(res.getInt("id"));
				currHotel.setHotelName(res.getString("name"));
				currHotel.setLocation(res.getString("location"));
				hotels.add(currHotel);
			}
			res.close();
			stmnt.close();

		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return hotels;
	}

	//gets all bookings and returns them in an arraylist
	public ArrayList<BookingDTO> initBookings(){
		ArrayList<BookingDTO> bookings = new ArrayList<BookingDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT id,start_date,end_date,customer_fk,checked_in FROM booking";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				BookingDTO currBooking = new BookingDTO();
				currBooking.setId(res.getInt("id"));
				currBooking.setStartDate(res.getDate("start_date"));
				currBooking.setEndDate(res.getDate("end_date"));
				currBooking.setCustomerID(res.getInt("customer_fk"));
				currBooking.setCheckedIn(res.getBoolean("checked_in"));
				bookings.add(currBooking);
				logger.info("booking loaded with customerID:(" + ") " + currBooking.getStartDate() + "->" + currBooking.getEndDate());
			}
			res.close();
			stmnt.close();

		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return bookings;
	}


	//given booking id, return all associated rooms
	public ArrayList<Integer> getRoomAssociationsID(int bookingID){
		ArrayList<Integer> roomIDs = new ArrayList<Integer>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT r.id FROM BOOKING_ON_ROOMS bor, BOOKING b, ROOM r WHERE bor.booking_fk=b.id AND r.id = bor.room_fk AND bor.booking_fk="+bookingID;
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				roomIDs.add(res.getInt("id"));
			}
			res.close();
			stmnt.close();

		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}

		return roomIDs;


	}

	public ArrayList<StaffDTO> initStaff(){
		ArrayList<StaffDTO> staff = new ArrayList<StaffDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT id,first_name,last_name,username,password,is_owner,hotel_fk FROM staff";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				StaffDTO currStaff = new StaffDTO();
				currStaff.setId(res.getInt("id"));
				currStaff.setFirstName(res.getString("first_name"));
				currStaff.setLastName(res.getString("last_name"));
				currStaff.setUsername(res.getString("username"));
				currStaff.setPassword(res.getString("password"));
				currStaff.setIsOwner(res.getBoolean("is_owner"));
				currStaff.setHotelID(res.getInt("hotel_fk"));
				staff.add(currStaff);
			}
			res.close();
			stmnt.close();

		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return staff;
	}

	public ArrayList<CustomerDTO> initCustomers(){
		ArrayList<CustomerDTO> customers = new ArrayList<CustomerDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT id,user_name,password,first_name,last_name,email,address,cc_number,cc_name,cc_expiry,verified FROM customer";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				CustomerDTO currCustomer = new CustomerDTO();
				currCustomer.setId(res.getInt("id"));
				currCustomer.setUser_name(res.getString("user_name"));
				currCustomer.setPassword(res.getString("password"));
				currCustomer.setFirst_name(res.getString("first_name"));
				currCustomer.setLast_name(res.getString("last_name"));
				currCustomer.setEmail(res.getString("email"));
				currCustomer.setAddress(res.getString("address"));
				currCustomer.setCc_number(res.getInt("cc_number"));
				currCustomer.setCc_name(res.getString("cc_name"));
				currCustomer.setCc_expiry(res.getString("cc_expiry"));
				currCustomer.setVerified(res.getBoolean("verified"));
				customers.add(currCustomer);
			}
			res.close();
			stmnt.close();

		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return customers;
	}

	//initialise rooms inside of the hotel, for all hotels
	public ArrayList<HotelDTO> initRooms(ArrayList<HotelDTO> hotelsCurrent){
		ArrayList<HotelDTO> hotelsMod = hotelsCurrent;
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT r.id, r.hotel_fk, r.room_Availability, r.checked_in, rt.price, rt.bedType, rt.numBeds , rt.name FROM room r, room_type rt, hotel h WHERE r.room_type_fk = rt.id AND r.hotel_fk=h.id";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				RoomDTO currRoom = new RoomDTO();
				currRoom.setId(res.getInt("id"));
				currRoom.setParentHotelID(res.getInt("hotel_fk"));
				currRoom.setPrice(res.getFloat("price"));
				currRoom.setBedType(res.getBoolean("bedType"));
				currRoom.setNumBeds(res.getInt("numBeds"));
				currRoom.setName(res.getString("name"));
				currRoom.setAvailableStatus(res.getBoolean("room_Availability"));
				currRoom.setCheckedIn(res.getBoolean("checked_in"));

				for ( HotelDTO hotel: hotelsMod){
					if(hotel.getId() == currRoom.getParentHotelID()){
						hotel.addToRooms(currRoom);
						break;
					}
				}
			}
			res.close();
			stmnt.close();

		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return hotelsMod;
	}

	public ArrayList<DiscountDTO> initDiscounts(){
		ArrayList<DiscountDTO> discounts = new ArrayList<DiscountDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT d.id, rt.name, d.discount_price, d.hotel_fk, d.start_date, d.end_date FROM discount d ,room_type rt WHERE d.room_type_fk=rt.id";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				DiscountDTO currDiscount = new DiscountDTO();
				currDiscount.setId(res.getInt("id"));
				currDiscount.setTypeOfRoom(res.getString("name"));
				currDiscount.setDiscountPercent(res.getFloat("discount_price"));
				currDiscount.setParentHotelID(res.getInt("hotel_fk"));
				currDiscount.setStartDate(res.getDate("start_date"));
				currDiscount.setEndDate(res.getDate("end_date"));

				discounts.add(currDiscount);
			}
			res.close();
			stmnt.close();

		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return discounts;
	}




	//Check if valid uName and pass (Customers)
	public boolean login(String user, String pass){
		Statement stmnt;
		int r = 0;
		try {
			stmnt = connection.createStatement();
			String query_cast =
					"SELECT count(*) FROM customer WHERE user_name='"+user+"'"+"AND password='"+pass+"'";
			ResultSet res = stmnt.executeQuery(query_cast);
			if(res.next()){
				r = res.getInt(1);
			}
			res.close();
			stmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r == 1;
	}

	public CustomerDTO getCustomer(String user){
		Statement stmnt;
		CustomerDTO c = new CustomerDTO();
		try {
			stmnt = connection.createStatement();
			String query_cast = "SELECT id,user_name,password,first_name,last_name,email,address,cc_number,cc_name,cc_expiry,verified "
					+ "FROM customer WHERE user_name = '"+user+"'";
			ResultSet res = stmnt.executeQuery(query_cast);
			if(res.next()){
				c.setId(res.getInt("id"));
				c.setUser_name(res.getString("user_name"));
				c.setPassword(res.getString("password"));
				c.setFirst_name(res.getString("first_name"));
				c.setLast_name(res.getString("last_name"));
				c.setEmail(res.getString("email"));
				c.setAddress(res.getString("address"));
				c.setCc_number(res.getInt("cc_number"));
				c.setCc_name(res.getString("cc_name"));
				c.setCc_expiry(res.getString("cc_expiry"));
				c.setVerified(res.getBoolean("verified"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public void updateCustomer(String user, String field, String update){
		Statement stmnt;
		try {
			stmnt = connection.createStatement();
			String query_cast = "UPDATE customer SET " + field + " = "
					+ "'" + update + "'" +"WHERE user_name = '" + user +"'";
			stmnt.executeUpdate(query_cast);
			stmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void updateCustomer(String user, String field, int update){
		Statement stmnt;
		try {
			stmnt = connection.createStatement();
			String query_cast = "UPDATE customer SET " + field + " = "
					+update+" WHERE user_name = '" + user +"'";
			stmnt.executeUpdate(query_cast);
			stmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void addUser(String user, String pass, String fName, String email) {
		Statement stmnt;
		try {
			stmnt = connection.createStatement();
			String query_cast = "INSERT INTO customer (id, user_name, password, first_name, email, verified)"
					+ " VALUES (DEFAULT, '"+
					user+"','"+pass+"','"+fName+"','"+email+"',false )";
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int newBooking(String start, String end, int customer, boolean checkedIn){
		Statement stmnt;
		int genKey = -1;
		try {
			stmnt = connection.createStatement();
			String query_cast = "INSERT INTO booking VALUES (DEFAULT, '"+
					start+"','"+end+"',"+customer+ ","+ checkedIn + " )";
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast, stmnt.RETURN_GENERATED_KEYS);
			ResultSet rs = stmnt.getGeneratedKeys();
			rs.next();
			genKey = rs.getInt(1);
			rs.close();
			stmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genKey;
	}

	public void bookRoom(int rID, int bID) {
		Statement stmnt;
		try {
			stmnt = connection.createStatement();
			String query_cast = "INSERT INTO booking_on_rooms VALUES (DEFAULT, "+
					rID+","+bID+")";
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeRoomFromBooking(int roomToRemoveID, int bookingIDofRoom){
		Statement stmnt;
		try {
			stmnt = connection.createStatement();
			String query_cast = "DELETE FROM booking_on_rooms bor WHERE booking_fk=" + bookingIDofRoom + "AND room_fk=" + roomToRemoveID;
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeTotalBooking(int bookingIDofRoom){
		Statement stmnt;
		try {
			stmnt = connection.createStatement();
			String query_cast = "DELETE FROM booking WHERE id=" + bookingIDofRoom;
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}


