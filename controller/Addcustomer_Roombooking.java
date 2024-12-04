package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entity.Booking_room;
import entity.Customer_details;
import service.Bookings;
import service.Dbconnection;
import start.HotelManagementSystem_application;
/*
 Description :
 
We have already implemented the functionality for adding rooms and customer details, ensuring that the implementation
is encapsulated to maintain data hiding.

This approach adheres to object-oriented principles,safeguarding the data by restricting direct access and exposing only the necessary methods for interaction.

*/


public class Addcustomer_Roombooking  implements Dbconnection{
   	static Connection connection = Dbconnection.getConnection();
	static Scanner scanner=new Scanner(System.in);
	 
    public static void addCustomer() {
    	 final String add_customer_query = "INSERT INTO customers (name, phone, email,address,room_id) VALUES (?, ?, ?,?,?)";

	            System.out.print("Enter customer name: ");
	            String name = scanner.next();
	            System.out.print("Enter phone number: ");
	            String phone = scanner.next();
	            System.out.print("Enter email: ");
	            String email = scanner.next();
	            System.out.print("Enter address: ");
	            String address = scanner.next();
	           
	       final    String room_id_list="select room_id from rooms where availability='available'";
	            try {
		            connection = DriverManager.getConnection(url,user,pw);

	                Statement stmt =connection.createStatement();
	                ResultSet rs = stmt.executeQuery(room_id_list);
                	ArrayList<Integer> users= new ArrayList();
                	
                	while (rs.next()) {
                      
                        int room_id = rs.getInt(1);
                        users.add(room_id);
                       
                    }
                	 System.out.println("Rooms available: " + users);
                	 System.out.println("Enter the roomId: ");
     	            int room_id=scanner.nextInt();	 
	            PreparedStatement stmt1 = connection.prepareStatement(add_customer_query);
	            stmt1.setString(1, name);
	            stmt1.setString(2, phone);
	            stmt1.setString(3, email);
	            stmt1.setString(4, address);
	            stmt1.setInt(5, room_id);
	            stmt1.executeUpdate();

	            System.out.println("Customer_details added successfully.");
	            Customer_details c=new Customer_details(name,phone,address,email);
	        } catch (SQLException e) {
	            System.out.println("Error adding customer: " + e.getMessage());
	        }
	        catch (InputMismatchException e) {
	            System.out.println("Error adding customer: " + e.getMessage());
	        }
	        catch (Exception e) {
	            System.out.println("Error adding customer: " + e.getMessage());
	        }
	            
    }

    

	    public static void bookRoom() {//brfnc
	        try {
	        	//view 
	        	
	        	 System.out.print("Enter room ID: ");
		         int roomId = scanner.nextInt();
		         
		final  String roomid_check="select availability from rooms where room_id = ?";      
		  PreparedStatement stmt1 = connection.prepareStatement(roomid_check);
		  stmt1.setInt(1, roomId);
		ResultSet rs1 = stmt1.executeQuery(); 
        while (rs1.next()) {     
        String status=rs1.getString(1); 
                      
	         
		   if(status.equals("Available")) {
			   /*
				  * if the room is available means then then we can retrieve the customer id from roomId        
				  */
			         
		final   String customerid_check="select customer_id from customers where room_id = ?" ;   	
			    
			   PreparedStatement stmt2 = connection.prepareStatement(customerid_check);
				  stmt2.setInt(1, roomId);
				ResultSet rs = stmt2.executeQuery(); 
		              while (rs.next()) {     
		            	  System.out.println("Customer Id is :" +rs.getInt(1));
		              }  ;
			    
			        	  System.out.print("Enter customer ID: ");
				            int customerId = scanner.nextInt();
				   
			            System.out.print("Enter price per day: ");
			            double pricePerNight = scanner.nextDouble();
			            System.out.print("Enter No of days: ");
			            int no_of_days = scanner.nextInt();
			            System.out.print("Enter check-in date (YYYY-MM-DD): ");
			            String checkInDate = scanner.next();
			            
    final String BOOK_ROOM_QUERY = "INSERT INTO bookings (room_id, customer_id ,price_per_night,noOfDays,check_in_date) VALUES (?, ?, ?,?,?)";
		            
			            PreparedStatement stmt = connection.prepareStatement(BOOK_ROOM_QUERY);
			            stmt.setInt(1, roomId);
			            stmt.setInt(2, customerId);
			            stmt.setDouble(3, pricePerNight);
			            stmt.setInt(4, no_of_days);
			            stmt.setString(5, checkInDate);
			            int ans=stmt.executeUpdate();
			            	//
			            if(ans>0) {
			            	System.out.println("booked successfully");
			 Booking_implementataion.updateRoomAvailability(roomId, "Booked");
			 Booking_room b=new Booking_room(roomId,customerId,pricePerNight,no_of_days,checkInDate);

			            }
			            else {
			            	System.out.println("room not available try another room");
			            }	

			          
			        } else {
		            	System.out.println("room not available try another room");
		            }  
		   }
        }catch (SQLException e) {
	            System.out.println(" Details incorrectly  entered " +e.getMessage());
	        }
	    }}


		

	   


