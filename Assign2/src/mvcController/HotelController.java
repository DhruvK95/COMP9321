package mvcController;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcModel.DerbyDAOImpl;

/**
 * Servlet implementation class HotelController
 */
@WebServlet(urlPatterns="/home", displayName="HotelController")
public class HotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//cast type can be used to interact with db
	//see  model derb implementsation fro more details
    private DerbyDAOImpl cast;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelController() {
    	//uses model derby initiator to create connection to database
        super();
		try {
			cast = new DerbyDAOImpl();
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
			//temporarly test db interations
			request.setAttribute("testHotelData",cast.allHotels());
			nextPage="home.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
		rd.forward(request, response);
	}

}
