package mvcModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * This class looks up the database via JNDI and returns a connection to the DAO Implementation class
 * 
 */
public class DBConnectionFactory {
	
	static Logger logger = Logger.getLogger(DBConnectionFactory.class.getName());
	private static DBConnectionFactory factory = null;
	private DataSource ds = null;
	private InitialContext ctx;
	private DBConnectionFactory(){
		try
		{
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cs9321");
			logger.info("Database found:"+ds.toString());
		} catch(Exception e) {
            e.printStackTrace();
		}
	}
	
	public DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection() throws SQLException{
		
		if(factory==null)
			factory = new DBConnectionFactory();
		Connection conn = factory.getDataSource().getConnection();
		
		return conn;
	}

}
