package mvcController;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcModel.BookingDTO;
//import com.sun.tools.doclets.internal.toolkit.util.SourceToHTMLConverter;
import mvcModel.CustomerDTO;
import mvcModel.DBStorageDTO;
import mvcModel.DerbyDAOImpl;
import mvcModel.DiscountDTO;
import mvcModel.HotelDTO;
import mvcModel.HotelRoomPair;
import mvcModel.RoomDTO;

/**
 * Servlet implementation class HotelController
 */
@WebServlet(urlPatterns="/home", displayName="HotelController")
public class HotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//cast type can be used to interact with db
	//see  model derb implementsation fro more details
	private DerbyDAOImpl cast;
	private DBStorageDTO database;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelController() {
		//uses model derby initiator to create connection to database
		super();
		try {
			/*
			DBSStorageDTO database = this.getServletConfig().getServletContext().getAtttibute("dB");
			if(database == null){
			*/
			cast = new DerbyDAOImpl();
			database = new DBStorageDTO();
			ArrayList<HotelDTO> allHotels = cast.initHotels();
			allHotels = cast.initRooms(allHotels);
			database.addAllStaff(cast.initStaff());
			database.addAllHotels(allHotels); //init all hotels from schema
			database.addAllCustomers(cast.initCustomers());
			database.addAllBookings(cast.initBookings());
			for(BookingDTO b : database.getAllBookings()){
				database.addRoomsToBooking(cast.getRoomAssociationsID(b.getId()), b.getId() );
			}
			database.addAllDiscounts(cast.initDiscounts());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET");
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("POST");
		processRequest(request, response);
		// TODO Auto-generated method stub
	}

	//all control flow and interation between servlets and jsps can occur here
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		String action = request.getParameter("action");
		System.out.println(request.getParameter("action"));
		verify(request,response);
		//updateDetails(request,response);
		registerUser(request,response);
		if(action != null ){
			if(action.equals("search")){

			} else if (action.equals("toRegister")){

				System.out.println("username is " + request.getParameter("username"));
				System.out.println("password is " + request.getParameter("password"));
				System.out.println("first_name is " + request.getParameter("first_name"));
				System.out.println("last_name is " + request.getParameter("last_name"));
				System.out.println("email is " + request.getParameter("email"));
				System.out.println("address is " + request.getParameter("address"));
				System.out.println("cc_number is " + request.getParameter("cc_number"));
				System.out.println("cc_name is " + request.getParameter("cc_name"));
				System.out.println("cc_expiry is " + request.getParameter("cc_expiry"));
				request.setAttribute("randomRooms", getRandomRoomsHash() );
				request.setAttribute("testHotelData",cast.allHotels());
				nextPage="home.jsp";
			} else if (action.equals("roomSearch")) {
				
				System.out.println("----- Room Search -------");
				System.out.println("check_in_date is " + request.getParameter("check_in_date"));
				System.out.println("check_out_date is " + request.getParameter("check_out_date"));
				System.out.println("city is " + request.getParameter("city"));
				System.out.println("max_price is " + request.getParameter("max_price"));
				System.out.println("number_of_rooms is " + request.getParameter("number_of_rooms"));

				String strNumRooms = request.getParameter("number_of_rooms");
				Integer number_of_rooms = Integer.parseInt(strNumRooms);

				String startDateString;
				String endDateString;

				startDateString = request.getParameter("check_in_date");
				endDateString = request.getParameter("check_out_date");

				DateFormat start_df = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat end_df = new SimpleDateFormat("dd/MM/yyyy");

				Date startDate = new Date();
				Date end_Date = new Date();

				try {
					startDate = start_df.parse(startDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				try {
					end_Date = end_df.parse(endDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				String cityToCheck = request.getParameter("city");
				double maxPrice = Double.parseDouble(request.getParameter("max_price"));

				//Calc unavaliable rooms
				request.setAttribute("searchRooms", searchRooms(startDate, end_Date, cityToCheck, maxPrice, number_of_rooms));
				Map<RoomDTO, HotelDTO> resultsMap = searchRooms(startDate, end_Date, cityToCheck, maxPrice, number_of_rooms);

				Integer numAvRooms = resultsMap.size() - getUnAvaliableRooms(resultsMap);


				request.setAttribute("numAvRooms", numAvRooms);
				request.setAttribute("randomRooms", getRandomRoomsHash() );
				request.setAttribute("testHotelData",cast.allHotels());
				nextPage="searchResults.jsp";

			} else if (action.equals("login")) {
				// Add Login stuff here....
				System.out.println("----- Login -------");
				System.out.println("username is " + request.getParameter("username"));
				System.out.println("password is " + request.getParameter("password"));
				request.setAttribute("randomRooms", getRandomRoomsHash() );
				request.setAttribute("testHotelData",cast.allHotels());

				nextPage = login(request, response);
			}else if(action.equals("logoutNo") || action.equals("logoutYes")){
				if(action.equals("logoutYes")){
					request.getSession().invalidate();
				}else{
					
				}
				request.setAttribute("randomRooms", getRandomRoomsHash() );
				request.setAttribute("testHotelData",cast.allHotels());
				nextPage="home.jsp";
			} else if (action.equals("profileUpdate")) {
				System.out.println("----- profileUpdate -------");
				updateProfile(request, response);
				nextPage="profile.jsp";
			}else if(action.equals("bookingSubmit")){
				System.out.println("----- bookingSubmit -------");
				String[] checkboxes = request.getParameterValues("roomsBookingsOptions");
				System.out.println(Arrays.toString(checkboxes));
				nextPage="bookingResults.jsp";
			}

		}else{
			 			//TESTING HOTELS WORKS
			 			//temporarly test db interations
			 			ArrayList<HotelDTO> tempSave = database.getAllHotels();
			 			for ( HotelDTO h : tempSave ){
			 				System.out.println( h.getId() + " " + h.getHotelName() + " " + h.getLocation());
			 				System.out.println("--------------------------------h--------------------------------------");
			 				for( RoomDTO r : h.getRooms()){
			 					System.out.println( "      " + r.getName() + " " + r.getId()+ " " +r.getNumBeds()+ " " +r.getParentHotelID()+ " " +r.getPrice() + " " + r.getAvailableStatus());
			 				}

			 			}
			 			ArrayList<CustomerDTO> tempSave2 = database.getAllCustomers();
			 			for ( CustomerDTO c : tempSave2 ){
			 				System.out.println( c.getId() + " " + c.getUser_name()+ " " + c.getPassword()+ " " + c.getFirst_name()+ " " + c.last_name+ " " + c.getEmail()+ " " + c.getAddress()+ " " + c.getCc_number()+ " " + c.getCc_name()+ " " + c.getCc_expiry());
			 				System.out.println("--------------------------------c--------------------------------------");
			 			}
			 			ArrayList<BookingDTO> tempSave3 = database.getAllBookings();
			 			for ( BookingDTO b : tempSave3 ){
			 				System.out.println( b.getId() + " " + b.getStartDate() + " " + b.getEndDate() + " " + b.getCustomerID());
			 				System.out.println("---------------------------------b-------------------------------------");
			 				for(RoomDTO r : b.getAllRooms()){
			 					System.out.println( "      " + r.getName() + " " + r.getId()+ " " +r.getNumBeds()+ " " +r.getParentHotelID()+ " " +r.getPrice());
			 				}
			 			}
			 			ArrayList<DiscountDTO> tempSave4 = database.getAllDiscounts();
			 			for ( DiscountDTO d : tempSave4 ){
			 				System.out.println("----------------------------------d------------------------------------");			 				
			 				System.out.println(d.getId() + " "+ d.getTypeOfRoom() + " " + d.getParentHotelID() + " " + d.getStartDate() + " " +d.getEndDate());
			 			}
			 			//TESTING HOTELS WORKS
			System.out.print("ERERERERERERERERERERERERERERERER");
			request.setAttribute("randomRooms", getRandomRoomsHash() );
			request.setAttribute("specialDeals", getSpecialDealsHash());
			nextPage="home.jsp";
		}
//		nextPage = login(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
	}

	public Map<HotelRoomPair, DiscountDTO> getSpecialDealsHash(){
		Map<HotelRoomPair, DiscountDTO> listOfDeals = new HashMap<HotelRoomPair,DiscountDTO>();
		for(DiscountDTO d : database.getAllDiscounts()){
			for( HotelDTO h: database.getAllHotels()){
				if( d.getParentHotelID() == h.getId()){
					for(RoomDTO r : h.getRooms()){
						if(r.getName().equals(d.getTypeOfRoom())){
							HotelRoomPair dT = new HotelRoomPair(h,r);
							listOfDeals.put(dT, d);
						}
					}
				}
			}
			
		}
		System.out.print(listOfDeals.size());
		return listOfDeals;
	}
	
	public Map<RoomDTO,HotelDTO> getRandomRoomsHash(){

		Map<RoomDTO,HotelDTO> randList = new HashMap<RoomDTO,HotelDTO>();
		ArrayList<String> checkAgainst = new ArrayList<String>();
		checkAgainst.add("Sydney");checkAgainst.add("Brisbane");checkAgainst.add("Melbourne");checkAgainst.add("Perth");checkAgainst.add("Adelaide");checkAgainst.add("Hobart");

		for(HotelDTO h : database.getAllHotels()){
			if(checkAgainst.contains(h.getLocation())){
				ArrayList<RoomDTO> rooms = h.getRooms();
				Random rand= new Random();
				System.out.println(rooms.size());
				int hotelNo = rand.nextInt(rooms.size());
				RoomDTO r = rooms.get(hotelNo);

				if(!randList.containsKey(r)){
					System.out.println(r.getName() + r.getParentHotelID());

					randList.put(r,h);
				}
				checkAgainst.remove(h.getLocation());
			}
		}
		return randList;
	}

	public CustomerDTO isValid(String username, String password){
		for(CustomerDTO c: database.getAllCustomers()){
			if(c.getUser_name().equals(username)){
				System.out.println("Pass:" + c.getPassword());
				if(c.getPassword().equals(password)){
					return c;
				}
				return null;
			}
		}
		return null;
	}
	
	private String login(HttpServletRequest request, HttpServletResponse response){
		String nextPage = "";
		CustomerDTO curr = (CustomerDTO) request.getSession().getAttribute("currUser");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		nextPage="login.jsp";
		if( curr != null || (username != null && password != null && isValid(username,password) != null)){
			nextPage="home.jsp";
			if(curr == null){
				request.getSession().setAttribute("currUser", isValid(username,password));
			}
		}else{
			request.setAttribute("loginError", true);
		}

		return nextPage;
	}
	
	private int staffLogin(String username, String password) {
		//Returns a staff members id if login is successful, otherwise -1
		return -1;
	}
	
	private void updateProfile(HttpServletRequest request, HttpServletResponse response){
		CustomerDTO curr = (CustomerDTO) request.getSession().getAttribute("currUser");
		if(curr == null) return;
		String pass = request.getParameter("password");
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String addr = request.getParameter("address");
		String ccNum = request.getParameter("cc_number");
		String ccNam = request.getParameter("cc_name");
		String ccExp = request.getParameter("cc_expiry");
		System.out.println("Fn: "+ fName );
		if(pass != null && pass.length() > 0){
			cast.updateCustomer(curr.getUser_name(), "password", pass);
		}
		if(fName != null && fName.length() > 0){
			cast.updateCustomer(curr.getUser_name(), "first_name", fName);
		}
		if(lName != null && lName.length() > 0){
			cast.updateCustomer(curr.getUser_name(), "last_name", lName);
		}
		if(email != null && email.length() > 0){
			cast.updateCustomer(curr.getUser_name(), "email", email);
		}
		if(addr != null && addr.length() > 0){
			cast.updateCustomer(curr.getUser_name(), "address", email);
		}
		if(ccNum != null && ccNum.length() > 0){
			int ccInt = Integer.parseInt(ccNum.trim());
			cast.updateCustomer(curr.getUser_name(), "cc_number", ccInt);
		}
		if(ccNam != null && ccNam.length() > 0){
			cast.updateCustomer(curr.getUser_name(), "cc_name", ccNam);
		}
		if(ccExp != null && ccExp.length() > 0){
			cast.updateCustomer(curr.getUser_name(), "cc_expiry", ccExp);
		}
		System.out.println("Fn: "+ curr.getFirst_name() );
		database.refreshCustomer(cast.getCustomer(curr.getUser_name()));
		request.getSession().setAttribute("currUser", database.findCutomer(curr.getUser_name()));
	}
	
	
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response){
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String addr = request.getParameter("address");
		String ccNum = request.getParameter("cc_number");
		String ccNam = request.getParameter("cc_name");
		String ccExp = request.getParameter("cc_expiry");
		if(user != null && !userExists(user) && pass!= null &&
				fName != null && lName != null && email != null && addr != null
				&& ccNam != null && ccExp != null){
			System.out.println("about to add new user");
			cast.addUser(user, pass, fName, lName, email, addr,
					Integer.parseInt(ccNum), ccNam, ccExp);
			database.refreshCustomer(cast.getCustomer(user));
			SendEmail verificationMail = new SendEmail(user,email,request);
		}

	}

	private boolean userExists(String user) {
		for(CustomerDTO c:database.customers){
			if(c.getUser_name().equals(user)){
				return true;
			}
		}
		return false;
	}

	private void verify(HttpServletRequest request, HttpServletResponse response) {
		String user = request.getParameter("verify");
		if(user != null && userExists(user)){
			for(CustomerDTO c:database.customers){
				if(c.getUser_name().equals(user)){
					if(c.isVerified()){
						return;
					}else{
						cast.updateCustomer(user, "verified", "true");
						c.setVerified(true);
						request.getSession().setAttribute("currUser", c);
					}
				}
			}
		}
	}


	public Map<RoomDTO,HotelDTO> searchRooms (Date startDate, Date endDate, String cityToCheck, Double maxPrice,
											  Integer numberOfRooms) {
		Map<RoomDTO, HotelDTO> resultList = new HashMap<RoomDTO, HotelDTO>();
		ArrayList<HotelRoomPair> resultArrayList = new ArrayList<HotelRoomPair>();

		for (HotelDTO h: database.getAllHotels()) {
			if (h.getLocation().contains(cityToCheck)) { // City check
				System.out.println(h.getHotelName() + "is in City " + h.getLocation());
				ArrayList<RoomDTO> rooms = h.getRooms();
				for (RoomDTO r: rooms) {
					System.out.println("Checking room " + r.getName());
					if (r.getPrice() <= maxPrice && roomIsAvaliableInRange(startDate, endDate, r)) {
						HotelRoomPair resultPair = new HotelRoomPair(h, r);
						resultArrayList.add(resultPair);

						resultList.put(r, h);
					}
				}
			}
		}

		// Number of rooms check
		if (resultList.size() < numberOfRooms) {
			System.out.println("Number of results is less than the number of rooms required");
		}
		System.out.println("Size of results is " + resultList.size());
		System.out.println("Result list is " + resultArrayList);
		
		return resultList;
	}

	public boolean roomIsAvaliableInRange (Date startDate, Date endDate, RoomDTO roomToCheck) {
		boolean result = false;
		Date currDate = startDate;
		int avaliableDays = 0;
		int totalDays = 0;


		while (currDate.compareTo(endDate) <= 0) { // Check the availability in the date range
//			System.out.println("Checking room " + roomToCheck.getName() + " for date " + currDate.toString());
			totalDays++;
			if (roomIsAvaliableOnDate(currDate, roomToCheck)) {
				avaliableDays++;
			}

			// Advance the loop
			Calendar c = Calendar.getInstance();
			c.setTime(currDate);
			c.add(Calendar.DATE, 1);
			currDate = c.getTime();
		}

		// If room is avaliable for the number of days in the date range, return true
		if (avaliableDays == totalDays) {
			result = true;
		}
		return result;
	}

	public boolean roomIsAvaliableOnDate (Date dateToCheck, RoomDTO roomToCheck) {
		// Check if that room is ever booked
		ArrayList<Integer> bookedRoomsIds = new ArrayList<Integer>();
		for (BookingDTO b1 : database.getAllBookings()) {
			for (RoomDTO r1: b1.getAllRooms()) {
				bookedRoomsIds.add(r1.getId());
			}
		}

		if (!bookedRoomsIds.contains(roomToCheck.getId())) {
			return true;
		}

		// Return true if the room is available on the date.
		for (BookingDTO b : database.getAllBookings()) {
			for (RoomDTO r: b.getAllRooms()) {
				if (roomToCheck.getId() == r.getId()) {
					if ((dateToCheck.compareTo(b.getStartDate()) < 0) || (dateToCheck.compareTo(b.getEndDate()) > 0)) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	public Integer getUnAvaliableRooms (Map<RoomDTO,HotelDTO> myMap) {
		Integer numberUnavaliable = 0;
		for (RoomDTO r : myMap.keySet()) {
			if (!r.getAvailableStatus()) {
				numberUnavaliable++;
			}
		}
		return numberUnavaliable;
	}

}


// Leon's search
/*
	public boolean roomIsAvaliableInRange1 (Date startDate, Date endDate, RoomDTO roomToCheck) {
		for (BookingDTO b : database.getAllBookings()) {
			for(RoomDTO r : b.getAllRooms()){

				if(r.getId() == roomToCheck.getId()){
					//.System.out.println("eeee" + r.getId());

					if((b.getStartDate().after(startDate) && b.getEndDate().after(endDate)) || (b.getStartDate().before(startDate) && b.getEndDate().before(endDate))){
						return true;
					}else{
						return false;
					}
					//and end
				}
			}
		}
		return true;
	}
 */