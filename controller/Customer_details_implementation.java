package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import service.Room;
import service.Customer;
import service.Dbconnection;
/*
 Description:
  
  We have provided the implementation for the abstract methods in the bookings abstract class to hide unnecessary details and expose only the relevant functionalities. This approach enhances abstraction, ensuring that the system focuses on essential operations while maintaining clarity and modularity in the code structure.
 
 */
public class Customer_details_implementation extends Customer implements Dbconnection{
	static Connection connection=Dbconnection.getConnection();
	static Scanner scanner=new Scanner(System.in);
  
    
	
/*
 * If you want to view the customer details of your booking.you can get the details by customer Id or full details can be retrived.
 */
	
    @Override
    public void viewCustomers(){
    	System.out.println("1.) View All customers ");
    	System.out.println("2.) View by Customer Id ");

System.out.println("Enter the number:");
int value=scanner.nextInt();
    	
    	switch(value) {
    	
    	case 1:
 
        try {
        	final String VIEW_CUSTOMERS= "SELECT * FROM customers";

            connection = DriverManager.getConnection(url,user,pw);
    	
            Statement stmt =connection.createStatement();
            ResultSet rs = stmt.executeQuery(VIEW_CUSTOMERS);

            while (rs.next()) {
                System.out.println("Customer_details ID: " + rs.getInt(1));
                System.out.println("Name : " + rs.getString(2));
                System.out.println("Phone number: " + rs.getString(3));
                System.out.println("Email Id: " + rs.getString(4));
                System.out.println("Address: " + rs.getString(5));
                System.out.println("Room Id: "+rs.getInt(6));
                System.out.println();
                System.out.println("***************");
            }
            break;
        } catch (SQLException e) {
            System.out.println("Error viewing customers: " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Error viewing customers: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error viewing customers: " + e.getMessage());
        }
    
    	
    	case 2:
    		
    		
    		try {

    	System.out.println("Enter the customer Id=");
    	int customer_id=scanner.nextInt();
    			String query1="select * from customers where customer_id=?"  ;
    			PreparedStatement pst = connection.prepareStatement(query1);
    			pst.setInt(1, customer_id);
    			ResultSet rs=pst.executeQuery();
    		
    		
    	            while (rs.next()) {
    	                System.out.println("Customer_details ID: " + rs.getInt(1));
    	                System.out.println("Name : " + rs.getString(2));
    	                System.out.println("Phone number: " + rs.getString(3));
    	                System.out.println("Email Id: " + rs.getString(4));
    	                System.out.println("Address: " + rs.getString(5));
    	                System.out.println("Room Id: "+rs.getInt(6));
    	                System.out.println();
    	                System.out.println("***************");
    	            }	
    	            break;
    			}
    			catch(Exception e){
    				System.out.println("Incorrect customer Id");
    				
    			}
    	

    	default:
    		System.out.println("invalid option");
    		break;
    	}
    }
}




