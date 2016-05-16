package mvcController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
import mvcModel.RoomDTO;
import mvcModel.StaffDTO;

/**
 * Servlet implementation class HotelController
 */
@WebServlet(urlPatterns="/dashboard", displayName="StaffController")
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private DerbyDAOImpl cast;
	private DBStorageDTO database;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StaffController() {
		super();
		try {
			cast = new DerbyDAOImpl();
			database = new DBStorageDTO();
			ArrayList<HotelDTO> allHotels = cast.initHotels();
			allHotels = cast.initRooms(allHotels);
			database.addAllHotels(allHotels); //init all hotels from schema
			database.addAllStaff(cast.initStaff());
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	//all control flow and interation between servlets and jsps can occur here
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String next_page = null;
		if (action == null) {
			StaffDTO user = (StaffDTO) request.getAttribute("staff_user");
			if (user == null) {
				//No staff user authenticated.
				next_page = "stafflogin.jsp";
				request.setAttribute("error", "You must log in to access that page.");
			} else {
				// User is correctly authenticated.
				if (user.getIsOwner()) {
					
				} else {
						
				}
			}
		} else if (action.equals("stafflogin")) {
			if (request.getParameter("username") == null || request.getParameter("password") == null) {
				// Bad request (didn't use the login form). Return to login page.
				next_page = "stafflogin.jsp";
			} else {
				StaffDTO user = staffLogin(request.getParameter("username"), request.getParameter("password"));
				if (user == null) {
					// bad login
					next_page = "stafflogin.jsp";
					request.setAttribute("error", "The username or password you entered was incorrect.");
				} else {
					// good login
					System.out.println("Staff authenticated: " + user.getFirstName() + " " + user.getLastName());
					request.setAttribute("staff_user", user);
					next_page = "dashboard.jsp";
				}
			}
	    }
		
		request.getRequestDispatcher("/"+next_page).forward(request, response);
	}
	

	private StaffDTO staffLogin(String username, String password) {
		for(StaffDTO s: database.getAllStaff()){
			if(s.getUsername().equals(username)){
				if(s.getPassword().equals(password)){
					return s;
				}
				return null;
			}
		}
		return null;
	}
}
