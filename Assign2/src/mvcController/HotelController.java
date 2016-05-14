package mvcController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcModel.CustomerDTO;
import mvcModel.DBStorageDTO;
import mvcModel.DerbyDAOImpl;
import mvcModel.HotelDTO;
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
			database.addAllHotels(allHotels); //init all hotels from schema
			database.addAllCustomers(cast.initCustomers());
			database.addAllBookings(cast.initBookings());

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
		updateDetails(request,response);
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

				nextPage="home.jsp";

			} else if (action.equals("login")) {
				// Add Login stuff here....
				System.out.println("----- Login -------");
				System.out.println("username is " + request.getParameter("username"));
				System.out.println("password is " + request.getParameter("password"));
				request.setAttribute("randomRooms", getRandomRoomsHash() );
				request.setAttribute("testHotelData",cast.allHotels());
				nextPage = login(request, response);
			}

		}else{

			System.out.print("ERERERERERERERERERERERERERERERER");
			request.setAttribute("randomRooms", getRandomRoomsHash() );
			request.setAttribute("testHotelData",cast.allHotels());
			nextPage="home.jsp";
		}
		nextPage = login(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
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
		CustomerDTO curr = (CustomerDTO) request.getSession().getAttribute("CurrUser");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		nextPage="login.jsp";
		if( curr != null || (username != null && password != null && isValid(username,password) != null)){
			nextPage="details.jsp";
			if(curr == null){
				request.getSession().setAttribute("CurrUser", isValid(username,password));
			}
		}

		return nextPage;
	}
	private void updateDetails(HttpServletRequest request, HttpServletResponse response){
		String newPass = request.getParameter("newPass");
		CustomerDTO curr = (CustomerDTO) request.getSession().getAttribute("CurrUser");
		if(curr!= null && newPass != null){
			cast.updateCustomer(curr.getUser_name(), "password", newPass);
			database.refreshCustomer(cast.getCustomer(curr.getUser_name()));
			request.getSession().invalidate();
		}
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
			SendEmail verificationMail = new SendEmail(user,email);
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



}
