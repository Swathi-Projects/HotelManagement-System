package entity;

/*
 * This class is created to add the room details while booking room for the customer
 * but, the variables are encapsulated and the values are set in the another class
 * called Addcustomer_Roombooking class in the controller package.In order to 
 * achieve the"Data hiding".
 * 
 */




public class Booking_room {
	private int roomId ;
	private  int customerId;
	private  double pricePerNight ;
	private  int no_of_days ;
	private String checkInDate ;
	
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public int getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Booking_room(){
		
	}
	public Booking_room(int roomId, int customerId, double pricePerNight, int no_of_days, String checkInDate) {
		super();
		this.roomId = roomId;
		this.customerId = customerId;
		this.pricePerNight = pricePerNight;
		this.no_of_days = no_of_days;
		this.checkInDate = checkInDate;
	}
	
	
}
