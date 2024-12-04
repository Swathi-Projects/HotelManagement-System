package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 
The database connection is established through an interface because the same variables
like URL, username, and password are required consistently throughout the application. 

These values should not be modified for any reason, so they are declared as final.
The connection is made static to ensure that the same instance is reused across the 
project, which improves efficiency. 

This is particularly beneficial for applications that do not require multiple connections.

In cases where multiple connections are needed, a separate interface and corresponding connection can be created to handle each scenario effectively.

 */


public interface Dbconnection {
	
	public final String url="jdbc:mysql://localhost:3306/hotel";
	public final String user="root";
	public final String pw="Swathi@2";
	
	 static Connection getConnection() {
	        Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(url, user, pw);	        } catch (SQLException e) {
	            System.out.println("Error establishing database connection: " + e.getMessage());
	        }
	        return connection;
	    }
}
