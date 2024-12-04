package entity;

/*
 * This class is created to add the customer details while booking room for the customer
 * but, the variables are encapsulated and the values are set in the another class
 * called Addcustomer_Roombooking class in the controller package.In order to 
 * achieve the"Data hiding".Which restricts the direct access.
 * 
 */

public class Customer_details {
	 private String name;
	    private String phone;
	    private String address;
	    private String email;
	    
	    public Customer_details() {
	    	
	    }
	    
	    public Customer_details(String name, String phone, String address, String email) {
			this.name = name;
			this.phone = phone;
			this.address = address;
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}


		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}

	
	
}
