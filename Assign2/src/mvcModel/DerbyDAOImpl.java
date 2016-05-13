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
	
	
	public ArrayList<CustomerDTO> initCustomers(){
		ArrayList<CustomerDTO> customers = new ArrayList<CustomerDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT id,user_name,password,first_name,last_name,email,address,cc_number,cc_name,cc_expiry FROM customer";
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
			String query_cast = "SELECT r.id, r.hotel_fk, rt.price, rt.bedType, rt.numBeds , rt.name FROM room r, room_type rt, hotel h WHERE r.room_type_fk = rt.id AND r.hotel_fk=h.id";
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
	
}
