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

//		if(request.getSession().getAttribute("db") == null){
//			System.out.println(database.getAllHotels().get(2).getHotelName());
//			request.getSession().setAttribute("db", database);
//		}else{
//			database = (DBStorageDTO) request.getSession().getAttribute("db");
//			System.out.println(database.getAllHotels().get(2).getHotelName());
//
//		}

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
				nextPage="home.jsp";
			}

		}else{
//			//TESTING HOTELS WORKS
//			//temporarly test db interations
//			ArrayList<HotelDTO> tempSave = database.getAllHotels();
//			for ( HotelDTO h : tempSave ){
//				System.out.println( h.getId() + " " + h.getHotelName() + " " + h.getLocation());
//				System.out.println("----------------------------------------------------------------------");
//				for( RoomDTO r : h.getRooms()){
//					System.out.println( "      " + r.getName() + " " + r.getId()+ " " +r.getNumBeds()+ " " +r.getParentHotelID()+ " " +r.getPrice());
//				}
//
//			}
//			ArrayList<CustomerDTO> tempSave2 = database.getAllCustomers();
//			for ( CustomerDTO c : tempSave2 ){
//				System.out.println( c.getId() + " " + c.getUser_name()+ " " + c.getPassword()+ " " + c.getFirst_name()+ " " + c.last_name+ " " + c.getEmail()+ " " + c.getAddress()+ " " + c.getCc_number()+ " " + c.getCc_name()+ " " + c.getCc_expiry());
//				System.out.println("----------------------------------------------------------------------");
//			}
//			//TESTING HOTELS WORKS

			System.out.print("ERERERERERERERERERERERERERERERER");
			request.setAttribute("randomRooms", getRandomRoomsHash() );
			request.setAttribute("testHotelData",cast.allHotels());
			nextPage="home.jsp";
		}

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
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
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
			database.refreshCustomer(cast.getCustomer(curr.getId()));
			request.getSession().invalidate();
		}
	}


}
