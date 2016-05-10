package mvcModel;

import java.sql.Connection;
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
			String query_cast = "SELECT * FROM hotel";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				String total = Integer.toString(res.getInt(1)).concat(res.getString(2)).concat(res.getString(3));
				cast.add(total);
			}
			
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		System.out.println(cast);
		return cast;
	}

}
