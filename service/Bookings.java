package service;

import java.sql.Connection;
import java.sql.SQLException;
/*
 *  In this we have made the booking list of rooms and customers and check 
 * out details in interface. 
 * so, that Incase in future if you add details like new branches details, 
 * new branch room maintaining details you can  add the default method in
 *  interface. so, it won't affect the other class while adding the extra features....
 *
 */

public interface Bookings {
	
	abstract void viewBookings();
	abstract void checkOutRoom();

}

