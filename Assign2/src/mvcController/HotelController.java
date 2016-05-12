package mvcController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
			// TODO Auto-generated method stub
	}
	
	//all control flow and interation between servlets and jsps can occur here
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		String action = request.getParameter("action");
		
		
		if(action != null ){
			if(action.equals("search")){
				
			}
		}else{
			//TESTING HOTELS WORKS
			//temporarly test db interations			
			ArrayList<HotelDTO> tempSave = database.getAllHotels();
			for ( HotelDTO h : tempSave ){
				System.out.println( h.getId() + " " + h.getHotelName() + " " + h.getLocation());
				System.out.println("----------------------------------------------------------------------");
				for( RoomDTO r : h.getRooms()){
					System.out.println( "      " + r.getName() + " " + r.getId()+ " " +r.getNumBeds()+ " " +r.getParentHotelID()+ " " +r.getPrice());
				}
			
			}
			ArrayList<CustomerDTO> tempSave2 = database.getAllCustomers();
			for ( CustomerDTO c : tempSave2 ){
				System.out.println( c.getId() + " " + c.getUser_name()+ " " + c.getPassword()+ " " + c.getFirst_name()+ " " + c.last_name+ " " + c.getEmail()+ " " + c.getAddress()+ " " + c.getCc_number()+ " " + c.getCc_name()+ " " + c.getCc_expiry());
				System.out.println("----------------------------------------------------------------------");
			}
			//TESTING HOTELS WORKS
			
			request.setAttribute("testHotelData",cast.allHotels());
			nextPage="home.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
	}

}
