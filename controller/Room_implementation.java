package controller;
import java.util.Scanner;

import service.Dbconnection;
import service.Room;
import start.HotelManagementSystem_application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

/*
 * In this class we the add rooms and room details 
 * 
 */
public class Room_implementation extends Room implements Dbconnection{
	static Connection connection=Dbconnection.getConnection();
     final String addroom = "INSERT INTO rooms (room_type,roomclass, availability) VALUES (?,?,'Available')";
     final String view_room_details = "SELECT * FROM rooms";
    static  Scanner scanner = new Scanner(System.in);
    
    public Room_implementation() {
    	
    }
    
 /*
  * For temporarily we have 12 rooms with there details. In case, in future we have more branches
  * then we can extend the room capacity using this method. 
  */
    
    
    public void addRoom() {
        try {
            System.out.print("Enter room type (Single/Double): ");
            String roomType = scanner.next();
            System.out.print("Enter room type (Business class/Economic class): ");
            String roomclass = scanner.next();
          
            PreparedStatement stmt = connection.prepareStatement(addroom);
         
            stmt.setString(1, roomType);
            stmt.setString(2, roomclass);
            
            
            int value=stmt.executeUpdate();

            
            if(value>0) System.out.println("Room added successfully.");
            else System.out.println("Error occurred");
            
        } catch (SQLException e) {
            System.out.println("Error adding room :  "+e.getMessage() );
        }
        catch (InputMismatchException e) {
            System.out.println("Error adding room :  "+e.getMessage() );
        }
        catch (Exception e) {
            System.out.println("Error adding room :  "+e.getMessage() );
        }
    }

    
/*
 * we can view the room details available in the Hotel.Based on size, class etc.
 */

    
    public  void viewRooms() {
        try {
            Statement stmt =connection.createStatement();
            ResultSet rs = stmt.executeQuery(view_room_details);

            while (rs.next()) {
                System.out.println("Room ID: " + rs.getInt(1)) ;
                System.out.println("Room Type: " + rs.getString(2));
                System.out.println("Room class: " + rs.getString(3));
                System.out.println("Availability: " + rs.getString(4));
                       System.out.println();
                       System.out.println("********************");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing rooms: " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Error viewing rooms: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error viewing rooms: " + e.getMessage());
        }
    }
}