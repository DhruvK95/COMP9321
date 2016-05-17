package mvcController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcModel.BookingDTO;
import mvcModel.DBStorageDTO;
import mvcModel.DerbyDAOImpl;
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	//all control flow and interation between servlets and jsps can occur here
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String next_page = null;
		if (action == null) {
			// Page refresh or first login. Do nothing extra.

		} else if (action.equals("checkin")) {
			// Checkin request made on one of the un-checked bookings
			BookingDTO booking = null;
			System.out.println("checkin: " + request.getParameter("booking_id"));
			for (BookingDTO b: database.getAllBookings()) {
				if (b.getId() == Integer.parseInt(request.getParameter("booking_id"))) {
					booking = b;
					break;
				}
			}
			if (booking == null) {
				request.setAttribute("message", "An invalid booking ID was given.");
			} else {
				booking.setCheckedIn(true);
				for (RoomDTO r: booking.getAllRooms()) {
					r.setCheckedIn(true);
				}
				request.setAttribute("message", "Booking ID:" + request.getParameter("booking_id") + " sucessfully checked in.");
			}

		} else if (action.equals("checkout")) {
			// Checkout request made on one of the un-checked bookings
			BookingDTO booking = null;
			System.out.println("checkout: " + request.getParameter("booking_id"));
			for (BookingDTO b: database.getAllBookings()) {
				if (b.getId() == Integer.parseInt(request.getParameter("booking_id"))) {
					booking = b;
					break;
				}
			}
			if (booking == null) {
				request.setAttribute("message", "An invalid booking ID was given.");
			} else {
				for (RoomDTO r: booking.getAllRooms()) {
					r.setCheckedIn(false);
				}
				//remove booking
				database.getAllBookings().remove(booking);
				request.setAttribute("message", "Booking ID:" + request.getParameter("booking_id") + " sucessfully checked out and removed.");
			}

		} else if (action.equals("stafflogin")) {
			if (request.getParameter("username") == null || request.getParameter("password") == null) {
				// Bad request (didn't use the login form). Return to login page.
				next_page = "stafflogin.jsp";
			} else {
				StaffDTO user = staffLogin(request.getParameter("username"), request.getParameter("password"));
				if (user == null) {
					// bad login
					request.setAttribute("error", "The username or password you entered was incorrect.");
				} else {
					// good login
					System.out.println("Staff authenticated: " + user.getFirstName() + " " + user.getLastName());
					request.getSession().setAttribute("staff_user", user);
				}
			}
		}

		StaffDTO user = (StaffDTO) request.getSession().getAttribute("staff_user");
		if (user == null) {
			//No staff user authenticated.
			next_page = "stafflogin.jsp";

			// Don't override existing error message
			if (request.getAttribute("error") == null) {
				request.setAttribute("error", "You must log in to access that page.");
			}
		} else {
			if (user.getIsOwner()) {
				doOwner(user, request, response);
				next_page = "ownerdashboard.jsp";
			} else {
				doManager(user, request, response);
				next_page = "managerdashboard.jsp";
			}
		}

		request.getRequestDispatcher("/"+next_page).forward(request, response);
	}



	private void doManager(StaffDTO user, HttpServletRequest request, HttpServletResponse response) {
		ArrayList<BookingDTO> checked_in = new ArrayList<BookingDTO>();
		ArrayList<BookingDTO> not_checked_in = new ArrayList<BookingDTO>();
		ArrayList<RoomDTO> occupied_rooms = new ArrayList<RoomDTO>();


		for (HotelDTO h: database.getAllHotels()) {
			if (h.getId() == user.getHotelID()) {
				// Only show rooms for the current manager's hotel
				for (RoomDTO r: h.getRooms()) {
					if (r.isCheckedIn()) {
						occupied_rooms.add(r);
					}
				}
				// skip the rest of the hotels.
				break;
			}
		}

		for (BookingDTO b:database.getAllBookings()) {
			// Only show bookings for the current manager's hotel
			if (b.getAllRooms().size() > 0) {
				if (b.getAllRooms().get(0).getParentHotelID() == user.getHotelID()){
					if (b.getCheckedIn()) {
						checked_in.add(b);
					} else {
						not_checked_in.add(b);
					}
				}
			}
		}
		request.setAttribute("checked_in", checked_in);
		request.setAttribute("not_checked_in", not_checked_in);
		request.setAttribute("occupied_rooms", occupied_rooms);
	}

	private void doOwner(StaffDTO user, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

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
