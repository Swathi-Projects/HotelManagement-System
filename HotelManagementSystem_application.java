package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controller.Addcustomer_Roombooking;
import controller.Booking_implementataion;
import controller.Customer_details_implementation;
import controller.Room_implementation;
import service.Customer;
import service.Dbconnection;

import java.sql.Statement;

/*
  Project overview:
 
 -> This project aims to develop a clear, maintainable, and user-friendly website for efficient hotel management. 
 The system streamlines operations by first retrieving room and customer details. It then allows the booking of 
 specific rooms for customers and seamlessly records check-out events.
 -> Additionally, the system maintains comprehensive  customer and booking histories, ensuring accurate record-keeping and smooth operations. 
 This design not only enhances efficiency but also delivers an intuitive and satisfying user experience.
 */


public class HotelManagementSystem_application implements Dbconnection {

	static Connection connection=Dbconnection.getConnection();
	static Scanner scanner;

    public static void main(String[] args) {
        try {
          
            connection = DriverManager.getConnection(url,user,pw);
           scanner = new Scanner(System.in);
            
            int choice;        
            do {
                System.out.println("\n--- Hotel Management System ---");
                System.out.println("1. View Rooms");
                System.out.println("2. Add Customer details");
                System.out.println("3. View Customer details");
                System.out.println("4. Book Room");
                System.out.println("5. Check Out Room ");
                System.out.println("6. View Bookings");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
              
                switch (choice) {

                    case 1 : 
                    	Room_implementation r1=new Room_implementation();
                    	r1.viewRooms();
                    	break;
                    
                    case 2 :
                    	Addcustomer_Roombooking.addCustomer();
                    	break;
                    case 3 :
                    	Customer_details_implementation c=new Customer_details_implementation();
                    	c.viewCustomers();
                    	break;                    	
                    case 4 : 
                    	Addcustomer_Roombooking.bookRoom();
                  
                    break;
                    case 5 : 
                    	Booking_implementataion b=new Booking_implementataion();
                    	b.checkOutRoom();
                    	break;
                    case 6 :
                    	Booking_implementataion b1=new Booking_implementataion();
                    	b1.viewBookings();
                    	break;
                    case 7 :
                    	System.out.println("Exiting the program. Goodbye!");
                    	break;
                    default : 
                    	System.out.println("Invalid choice. Please try again.");
                    	break;
                
                
            } 
                    }while (choice != 7);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage()) ; 
            
    }
    }
}




