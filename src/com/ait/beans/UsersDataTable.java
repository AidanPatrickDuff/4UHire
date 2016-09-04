package com.ait.beans;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UsersDataTable {
	private static String firstName;
	private static String secondName;
	private static String contactNumber;
	private static String email;
	private String address1;
	private String address2;
	private String county;
	private boolean accountActive;
	private static int role;
	private String password; 
	private boolean isLoggedIn = false;
	private Users theLoggedOnUser;
	private boolean managerLoggedOn;
	private boolean fdsLoggedOn;
	private boolean customerLoggedOn = true;
	private static boolean showSaveButton;

	private static ArrayList<Booking> bookings = new ArrayList<Booking>();

	public static ArrayList<Users> managersCollection = new ArrayList<Users>();
	public static ArrayList<Users> frontDeskStaffCollection = new ArrayList<Users>();
	public static ArrayList<Users> customersCollection = new ArrayList<Users>();

	private static String userLoggedInEmail;
	private static ArrayList<Booking> userLoggedInBookings = new ArrayList<Booking>();
	private static ArrayList<EventBooking> userLoggedInEvents = new ArrayList<EventBooking>();

	// public static ArrayList<Users> userLoggedin = new ArrayList<Users>();

	public UsersDataTable() { 
	} 

	@PostConstruct
	public void init() {

		frontDeskStaffCollection = new ArrayList<Users>();
		Users u0 = new Users("admin", "", "", "admin", "", "", "", true, 1, "root", true, bookings);
		Users u1 = new Users("cormac", "Hallinan", "0948590", "cormac@ait.ie", "Dromore West", "Sligo", "sligo", true,
				1, "root", true, bookings);
		Users u2 = new Users("Peter", "Farrelly", "12352", "peter@ait.ie", "bastiane", "athlone", "Westmeath", true, 2,
				"root", true, bookings);
		Users u3 = new Users("Aidan", "Duff", "89848", "tom@ait.ie", "Coosan", "Athlone", "Westmeath", true, 3, "root",
				true, bookings);
		Users u4 = new Users("Jackie1", "Kell", "395820", "Jackie@ait.ie", "connaught", "Athlone", "Westmeath", true, 3,
				"root", true, bookings);

		managersCollection.add(u0);
		managersCollection.add(u1);
		frontDeskStaffCollection.add(u2);
		customersCollection.add(u3);
		customersCollection.add(u4);

	}
	
	public String canBookVehicle(){
		String loggedInStatus = "Login";
		if (isLoggedIn){
			loggedInStatus = "AddBooking";
		}
		return loggedInStatus;
	}
	
	public String canBookEvent(){
		String loggedInStatus = "Login";
		if (isLoggedIn){
			loggedInStatus = "AddEventBooking";
		}
		return loggedInStatus;
	}
	
	public String singleCustomerBookings() {
		String customerBookingsStatus = "index";
		// clear array before adding again
		userLoggedInBookings.clear();
		//if customer has no bookings
		if (BookingDataTable.bookingCollection.size() == 0) {
			if (EventBookingDataTable.eventBookingCollection.size() == 0) { //if has no bookings AND no event BOOKINGS
				customerBookingsStatus = "index";
			} else{

			}
			
		} else if(BookingDataTable.bookingCollection.size() > 0) {
			userLoggedInBookings.clear();
			
			for (Booking userBookings : BookingDataTable.bookingCollection) {
				if (userBookings.getUser().getEmail().equalsIgnoreCase(userLoggedInEmail)) {
					// add new VEHICLE booking
					userLoggedInBookings.add(userBookings);
					customerBookingsStatus = "CustomerBookings";

				}
			} // end for vehicle booking
		} 
		if (EventBookingDataTable.eventBookingCollection.size() > 0){
			// clear array before adding again
			userLoggedInEvents.clear();

			for (EventBooking userEvents : EventBookingDataTable.eventBookingCollection) {
				if (userEvents.getUser().getEmail().equalsIgnoreCase(userLoggedInEmail)) {
					// add new event to collection
					userLoggedInEvents.add(userEvents);
					customerBookingsStatus = "CustomerBookings";
				}
			} // end for event booking
		}

		return customerBookingsStatus;
	}

	public String addManager() {
		String managerStatus = "";
		boolean managerExists = true;
		for (Users u : managersCollection) {
			if (u.getEmail().equals(email)) { 
				managerExists = false;
			}
		}

		if (managerExists) {
			// account will be active, Role will be hardcoded as 1 for front
			// manager, they wont be logged in yet and an empty bookings
			final Users user = new Users(firstName, secondName, contactNumber, email, this.address1, this.address2,
					this.county, true, 1, this.password, false, bookings);
			managersCollection.add(user);

			firstName = null;
			secondName = null;
			contactNumber = null;
			email = null;
			this.address1 = null;
			this.address2 = null;
			this.county = null;
			this.password = null;
			managerStatus = "UpdateManagers";
		}

		return managerStatus;
	}

	public String addFrontDeskStaff() {
		String staffStatus = "";

		boolean eventExists = true;
		for (Users u : frontDeskStaffCollection) {
			if (u.getEmail().equals(email)) {
				eventExists = false;
			}
		}

		if (eventExists) {
			// Role will be hardcoded as 2 for front desk staff, they wont be
			// logged
			// in yet and an empty bookings
			final Users user = new Users(firstName, secondName, contactNumber, email, address1, this.address2,
					this.county, this.accountActive, 2, this.password, true, bookings);

			frontDeskStaffCollection.add(user);
			firstName = null;
			secondName = null;
			contactNumber = null;
			email = null;
			this.address1 = null;
			this.address2 = null;
			this.county = null;
			this.password = null;

			staffStatus = "UpdateFrontDeskStaff";
		}

		return staffStatus;
	}

	public String addCustomer() {
		// account will be active, Role will be hardcoded as 3 for front
		// customer, they wont be logged in yet and an empty bookings
		final Users user = new Users(this.firstName, this.secondName, this.contactNumber, this.email, this.address1,
				this.address2, this.county, true, 1, this.password, true, bookings);
		customersCollection.add(user);
		userLoggedInEmail = email;
		email = null;
		firstName = null;
		secondName = null;
		contactNumber = null; 
		this.address1 = null;
		this.address2 = null;
		this.county = null;
		this.password = null;
		fdsLoggedOn = false;
		managerLoggedOn = false;
		customerLoggedOn = true;
		return "index";
	}

	public ArrayList<Users> getManagersCollection() {
		return managersCollection;
	}

	public ArrayList<Users> getFrontDeskStaffCollection() {
		return frontDeskStaffCollection;
	}

	public ArrayList<Users> getCustomersCollection() {
		return customersCollection;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		UsersDataTable.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public boolean isAccountActive() {
		return accountActive;
	}

	public void setAccountActive(boolean accountActive) {
		this.accountActive = accountActive;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String editUser(Users users) {
		users.setCanEdit(true);
		showSaveButton = true;
		return null;
	}

	public String saveActionFrontDeskStaff() {
		for (Users users : frontDeskStaffCollection) {
			users.setCanEdit(false);
		}
		showSaveButton = false;
		return null;
	}

	public String saveActionCustomer() {
		for (Users users : customersCollection) {
			users.setCanEdit(false);
		}
		showSaveButton = false;
		return null;
	}

	public String saveActionManager() {
		for (Users users : managersCollection) {
			users.setCanEdit(false);
		}
		showSaveButton = false;
		return null;
	}

	public boolean isShowSaveButton() {
		return showSaveButton;
	}

	public void setShowSaveButton(boolean SaveButton) {
		showSaveButton = SaveButton;
	}

	public String deleteFrontDeskStaff(Users users) {
		frontDeskStaffCollection.remove(users);
		return null;
	}

	public String deleteManager(Users users) {
		// if not admin can be deleted
		if (!users.getEmail().equals("admin")) {
			managersCollection.remove(users);
		}
		return null;
	}

	public String LogIn() {
		String redirect = "invalid Login details";
		isLoggedIn = false;
		// check managers
		for (Users m : managersCollection) {
			if (m.getEmail().equals(email) && m.getPassword().equals(this.password)) {
				redirect = "index";
				isLoggedIn = true;
				managerLoggedOn = true;
				fdsLoggedOn = false;
				customerLoggedOn = false;
			}
		}
		// check front desk staff
		for (Users fds : frontDeskStaffCollection) {
			if (fds.getEmail().equals(email) && fds.getPassword().equals(this.password)) {
				redirect = "index";
				isLoggedIn = true;
				fdsLoggedOn = true;
				managerLoggedOn = false;
				customerLoggedOn = false;
			}
		}
		if (!isLoggedIn) {
			// check customer
			for (Users cust : customersCollection) {
				if (cust.getEmail().equals(email) && cust.getPassword().equals(this.password)) {
					redirect = "index";
					isLoggedIn = true;
					fdsLoggedOn = false;
					managerLoggedOn = false;
					customerLoggedOn = true;
				}
			}
		}

		userLoggedInEmail = email;
		email = null;

		return redirect;

	}

	public void setIsLoggedIn(boolean logged) {
		this.isLoggedIn = logged;
	}

	public static String getUserLoggedInEmail() {
		return userLoggedInEmail;
	}

	public static void setUserLoggedInEmail(String email) {
		userLoggedInEmail = email;
	}

	public void logOut() {
		this.isLoggedIn = false;
		this.managerLoggedOn=false;
		email = null;
		this.password = null;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public void setBooking(ArrayList<Booking> bookings) {
		UsersDataTable.bookings = bookings;
	}

	public ArrayList<Booking> getBookings() {
		return UsersDataTable.bookings;
	}

	public static void setUserLoggedInBookings(Booking bookings) {
		UsersDataTable.userLoggedInBookings.add(bookings);
	}

	public ArrayList<Booking> getUserLoggedInBookings() {
		return UsersDataTable.userLoggedInBookings;
	}

	public static ArrayList<Booking> UserLoggedInBookings() {
		return UsersDataTable.userLoggedInBookings;
	}

	public static void setUserLoggedInEvents(EventBooking events) {
		UsersDataTable.userLoggedInEvents.add(events);
	}

	public ArrayList<EventBooking> getUserLoggedInEvents() {
		return UsersDataTable.userLoggedInEvents;
	}

	public static ArrayList<EventBooking> userLoggedInEvents() {
		return UsersDataTable.userLoggedInEvents;
	}

	public static ArrayList<Booking> usersBookings() {
		return UsersDataTable.bookings;
	}

	public static String usersFirstName() {
		return firstName;
	}

	public static String usersSecondName() {
		return secondName;
	}

	public static String usersContact() {
		return contactNumber;
	}

	public static String usersEmail() {
		return email;
	}

	public static int usersRole() {
		return role;
	}

	public Users getTheLoggedOnUser() {
		return theLoggedOnUser;
	}

	public String userLoggedOn() {
		String redirect = "";
		for (Users cust : customersCollection) {
			if (cust.getEmail().equals(userLoggedInEmail)) {
				theLoggedOnUser = cust;
				redirect = "ProfileCustomer";
			}
		}

		for (Users fds : frontDeskStaffCollection) {
			if (fds.getEmail().equals(userLoggedInEmail)) {
				theLoggedOnUser = fds;
				redirect = "ProfileFrontDeskStaff";
			}
		}

		for (Users manager : managersCollection) {
			if (manager.getEmail().equals(userLoggedInEmail)) {
				theLoggedOnUser = manager;
				redirect = "ProfileManager";
			}
		}

		return redirect;
	}

	public boolean isManagerLoggedOn() {
		return managerLoggedOn;
	}

	public void setManagerLoggedOn(boolean managerLoggedOn) {
		this.managerLoggedOn = managerLoggedOn;
	}

	public boolean isFdsLoggedOn() {
		return fdsLoggedOn;
	}

	public void setFdsLoggedOn(boolean fdsLoggedOn) {
		this.fdsLoggedOn = fdsLoggedOn;
	}

	public boolean isCustomerLoggedOn() {
		return customerLoggedOn;
	}

	public void setCustomerLoggedOn(boolean customerLoggedOn) {
		this.customerLoggedOn = customerLoggedOn;
	}

}