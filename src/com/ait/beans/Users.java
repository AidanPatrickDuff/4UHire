package com.ait.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Users {
	private String firstName;
	private String secondName;
	private String contactNumber;
	private String email;
	private String address1;
	private String address2;
	private String county;
	private boolean accountActive;
	private int role;
	private String password;
	private boolean canEdit;
	private boolean isLoggedIn = false;
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private ArrayList<Events> eventsBookings = new ArrayList<Events>();

	public ArrayList<Users> usersCollectionList = new ArrayList<Users>();

	public Users() {
	}

	public Users(String firstName, String secondName, String contactNumber, String email, String address1,
			String address2, String county, boolean accountActive, int role, String password, boolean isLoggedIn,
			ArrayList<Booking> bookings) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.contactNumber = contactNumber;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
		this.county = county;
		this.accountActive = accountActive;
		this.role = role;
		this.setPassword(password);
		this.isLoggedIn = isLoggedIn;
		this.bookings = bookings;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
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
		this.email = email;
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
		// Role must be 1 - 3
		// 1 = Management
		// 2 = Front Desk Staff
		// 3 = Customer
		if (role > 3) {
			role = 0; // role of 0 means its insufficient
		}
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

	public int getNumberOfBooking(ArrayList<Booking> bookings) {
		return bookings.size();
	}
	
	public void setBookings(Booking bookings) {
		this.bookings.add(bookings);
	}

	public ArrayList<Booking> getBookings() {
		return this.bookings;
	}

	public void setEventsBookings(Events events) {
		this.eventsBookings.add(events);
	}

	public ArrayList<Events> getEventsBookings() {
		return this.eventsBookings;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public boolean logout() {
		this.isLoggedIn = false;
		return isLoggedIn;

	}

}
