package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.views.AbstractView;

import service.Dbconnection;
import service.Bookings;
import start.HotelManagementSystem_application;
/*
 Description:
 
We have implemented the interface for booking-related abstract methods to encapsulate unnecessary details and expose 
only the essential functionalities.

This design ensures better abstraction, making the system more modular,  maintainable, and aligned with
 object-oriented programming principles.
 
 */
public class Booking_implementataion implements Bookings,Dbconnection{
	static Connection connection=Dbconnection.getConnection();
	static Scanner scanner=new Scanner(System.in);
	
	
/*
 * Check out Room method will store the check out date of the customer and will make the particular room id status as available for future bookings.	
 */
	
	 public void checkOutRoom() {
		  String CHECKOUT_ROOM_QUERY = "UPDATE bookings SET check_out_date = ? WHERE room_id = ? AND check_out_date IS NULL";

	        try {
	           connection = DriverManager.getConnection(url,user,pw);
	            System.out.print("Enter room ID: ");
	            int roomid = scanner.nextInt();
	            System.out.print("Enter check-out date (YYYY-MM-DD): ");
	            String checkOutDate = scanner.next();

	            PreparedStatement stmt =connection.prepareStatement(CHECKOUT_ROOM_QUERY);
	            stmt.setString(1, checkOutDate);
	            stmt.setInt(2, roomid);	
	            int ans=stmt.executeUpdate();
	            if(ans>0) {
	            	System.out.println("checked out successfully");
	            }
	            else {
	            	System.out.println("Invalid room no.");
	            }
/*
* when we check out it will change the status as available in rooms class
*/

	            Booking_implementataion.updateRoomAvailability(roomid, "Available");
	            
	            System.out.println("Room checked out successfully.");
	        } catch (SQLException e) {
	            System.out.println("(Room  booked )or Room id or Customer_details id  incorrectly entered " );
	        }
	    }

/*
 * View bookings method will help to view the entire booking details of the hotel.	 
 */
	 
	 
	    public  void viewBookings() {
		   String VIEW_BOOKINGS_QUERY = "SELECT * 	FROM bookings";

	        try {
	            connection = DriverManager.getConnection(url,user,pw);
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery(VIEW_BOOKINGS_QUERY);
	            List<Object> users = new ArrayList<>();
	            while (rs.next()) {
	            	
//	            	int booking_id= rs.getInt("booking_id");
//	            	int Room_ID=  rs.getInt("room_id");
//	            	int Customer_ID= rs.getInt("customer_id");
//	            	String Check_in_Date= rs.getString("check_in_date");
//	            	String Check_out_Date= rs.getString("check_out_date");
//	            	users.add(booking_id);
//	            	users.add(Room_ID);
//	            	users.add(Customer_ID);
//	            	users.add(Check_in_Date);
//	            	users.add(Check_out_Date);
	                System.out.println("Booking ID: " + rs.getInt("booking_id"));
	                System.out.println(" Room ID: " + rs.getInt("room_id"));
	                System.out.println( "Customer ID: " + rs.getInt("customer_id") );
	                System.out.println("Check-in Date: " + rs.getString("check_in_date"));
	              
	                System.out.println("Check-out Date: " + rs.getString("check_out_date"));
		          System.out.println("*****************");
//System.out.println(users);
	            
	            
	            }
	           
	        } catch (SQLException e) {
	            System.out.println("Error viewing bookings: " + e.getMessage());
	        }
	    }

/*
 * Updateroom availability method is used to handle the change of room status according to the scenerio. 	    
 */
	    public static void updateRoomAvailability(int roomId, String status) throws SQLException {
		String UPDATE_ROOM_AVAILABILITY_QUERY = "UPDATE rooms SET availability = ? WHERE room_id = ?";

	    	PreparedStatement stmt = connection.prepareStatement(UPDATE_ROOM_AVAILABILITY_QUERY);
	        stmt.setString(1, status);
	        stmt.setInt(2, roomId);
	        stmt.executeUpdate();
	    }
}
