package com.ait.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ait.beans.Booking;
import com.ait.beans.BookingDataTable;
import com.ait.beans.EventBooking;
import com.ait.beans.EventBookingDataTable;
import com.ait.beans.Events;
import com.ait.beans.EventsDataTable;
import com.ait.beans.Users;
import com.ait.beans.UsersDataTable;
import com.ait.beans.Vehicle;

public class UsersDataTableTest { 
	
	public UsersDataTable userDt;
	public Users user;
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	
	@Before
	public void setUp() {
		userDt = new UsersDataTable();
	}
	
	@Test
	public void testInit(){
		userDt.init();
	}
	
	@Test // add users to datatable
	public void testaddUser(){
		bookings = new ArrayList<Booking>();
		
		//must set first
		userDt.setFirstName("cormac");
		userDt.setSecondName("hallinan");
		userDt.setContactNumber("09475");
		userDt.setEmail("cormac@ait.ie");
		userDt.setAddress1("ballykilcash");
		userDt.setAddress2("Dromore West");
		userDt.setCounty("Sligo");
		userDt.setAccountActive(true);
		userDt.setRole(1);
		userDt.setPassword("root");
		userDt.setLoggedIn(true);
		userDt.setBooking(bookings);
		
		// test if proper values return => will test get as well
		assertEquals("cormac", userDt.getFirstName());
		assertEquals("hallinan", userDt.getSecondName());
		assertEquals("09475", userDt.getContactNumber());
		assertEquals("cormac@ait.ie", userDt.getEmail());
		assertEquals("ballykilcash", userDt.getAddress1());
		assertEquals("Dromore West", userDt.getAddress2());
		assertEquals("Sligo", userDt.getCounty());
		assertTrue(userDt.isAccountActive());
		assertEquals("root", userDt.getPassword());
		assertEquals(1, userDt.getRole());
		
		userDt.getFrontDeskStaffCollection();
		userDt.getManagersCollection();
		userDt.getCustomersCollection();
		userDt.setEmail("new@email.com");
		//Successful log in of any user returns to index page
		assertEquals("UpdateManagers", userDt.addManager());
		assertEquals("UpdateFrontDeskStaff", userDt.addFrontDeskStaff());
		assertEquals("index", userDt.addCustomer());
		
		assertEquals(bookings, userDt.getBookings());
		
		//create user to test editUser
		Users u0 = new Users("tom", "", "", "toms@ait.ie", "", "", "", true, 1, "root", true, bookings);
		userDt.editUser(u0);
		assertTrue(u0.isCanEdit());
		UsersDataTable.frontDeskStaffCollection.add(u0);
		userDt.saveActionFrontDeskStaff();
		assertFalse(u0.isCanEdit());
		
		UsersDataTable.managersCollection.add(u0);
		userDt.saveActionManager();
		assertFalse(u0.isCanEdit());
		userDt.deleteManager(u0);
		assertEquals(5,UsersDataTable.frontDeskStaffCollection.size());
		//user detail methods
		assertEquals(bookings, UsersDataTable.usersBookings());
		UsersDataTable.usersFirstName();
		UsersDataTable.usersSecondName();
		UsersDataTable.usersContact();
		assertEquals(1, UsersDataTable.usersRole());
		UsersDataTable.customersCollection.add(u0);
		userDt.saveActionCustomer();
		assertFalse(u0.isCanEdit());
		userDt.deleteFrontDeskStaff(u0);
		assertEquals(4,UsersDataTable.frontDeskStaffCollection.size());
		// log out and test
		userDt.logOut();
		//check if email and password cleared for 
		assertEquals(null, UsersDataTable.usersEmail());
		
	
	}
	
	@Test
	public void testWhatUserLogIn(){
		UsersDataTable.setUserLoggedInEmail("test@ait.ie");
		// log in customer
		user = new Users("Tom", "Test", "12345", "test@ait.ie", null, null, null, true, 1, "root", true, bookings);
		UsersDataTable.customersCollection.add(user);
		
		assertEquals("ProfileFrontDeskStaff", userDt.userLoggedOn());

		// now give him a role of 2 => front desk staff
		userDt.setRole(2);
		UsersDataTable.frontDeskStaffCollection.add(user);
		assertEquals("ProfileFrontDeskStaff", userDt.userLoggedOn());
		
		// now give him a role of 3 => manager
		userDt.setRole(3);
		UsersDataTable.managersCollection.add(user);
		assertEquals("ProfileManager", userDt.userLoggedOn());
	}
	
	@Test
	public void testUserLoggedInBookings(){
		// clear arrayLists to at start to clear all data from inits so can be tested from start
		// test what happens when user has 0 EVENT BOOKINGS
		EventBookingDataTable.eventBookingCollection.clear();
		// test what happens when user has 0 VEHICLE BOOKINGS
		BookingDataTable.bookingCollection.clear();
		assertEquals(0, BookingDataTable.bookingCollection.size());
		assertEquals(0, EventBookingDataTable.eventBookingCollection.size());
		//index means no booking has been added yet
		assertEquals("index", userDt.singleCustomerBookings());
		// log in set to test against
		UsersDataTable.setUserLoggedInEmail("pat@ait.ie");
		// set the user a vehicle booking and event bookings
		Vehicle vehicle = new Vehicle();
		// just need to assign email to check its the person logged in
		user = new Users(null, null, null, "pat@ait.ie", null, null, null, true, 3, null, true, null);
		
		Booking vehicleBooking = new Booking("no", null, 2, vehicle, user, 100);
		BookingDataTable.bookingCollection.add(vehicleBooking);
		assertEquals(1, BookingDataTable.bookingCollection.size());
		UsersDataTable.setUserLoggedInBookings(vehicleBooking);
		//if there is a vehicle booking "CustomerBookings" is returned
		assertEquals("CustomerBookings", userDt.singleCustomerBookings());
		userDt.getTheLoggedOnUser();
		assertEquals(1,userDt.getUserLoggedInBookings().size());
		
		// now add an event booking
		Events event = new Events("testEvent", 0, "testing", 100);
		EventBooking eventBooking = new EventBooking(null, 3, event, user, null, 100);
		EventBookingDataTable.eventBookingCollection.add(eventBooking);
		UsersDataTable.setUserLoggedInEvents(eventBooking);
		assertEquals(1,UsersDataTable.userLoggedInEvents().size());
		// check one booking for each (1 event, 1 vehicle booking)
		assertEquals(1,UsersDataTable.UserLoggedInBookings().size());
		assertEquals(1,userDt.getUserLoggedInEvents().size());
		//if there is a EVENT booking "CustomerBookings" is returned
		assertEquals("CustomerBookings", userDt.singleCustomerBookings());
		
	}
	
	@Test
	public void testLogIn(){
		userDt.setIsLoggedIn(false);
		// add manager to mansgers colection
		user = new Users("Tom", "Test", "12345", "test@ait.ie", null, null, null, true, 1, "root", true, bookings);
		UsersDataTable.managersCollection.add(user);
		//set email and pass of user trying to log in
		userDt.setEmail("test@ait.ie");
		userDt.setPassword("root");

		assertEquals("index", userDt.LogIn());

	}
	
	@Test
	public void testLogInFrontDeskStaff(){
		UsersDataTable.managersCollection.clear();
		userDt.setIsLoggedIn(false);
		// add manager to frontDeskStaff colection
		user = new Users("Tom", "Test", "12345", "test@ait.ie", null, null, null, true, 2, "root", true, bookings);
		UsersDataTable.frontDeskStaffCollection.add(user);
		//set email and pass of user trying to log in
		userDt.setEmail("test@ait.ie");
		userDt.setPassword("root");
		// check
		assertEquals("index", userDt.LogIn());
	}
	
	@Test
	public void testLogInCustomer(){
		userDt.setIsLoggedIn(false);
		// add to customer colection
		user = new Users("pat", "Test", "12345", "p@ait.ie", null, null, null, true, 3, "root", true, bookings);
		UsersDataTable.customersCollection.add(user);
		//set email and pass of user trying to log in
		userDt.setEmail("p@ait.ie");
		userDt.setPassword("root");
		// check
		assertEquals("index", userDt.LogIn());
	}
	
	@Test
	public void testShowButton(){
		userDt.setShowSaveButton(true);
		assertTrue(userDt.isShowSaveButton());
	}
	
	@Test
	public void testCustomerLoggedIn(){
		//shoudl be true initally
		assertTrue(userDt.isCustomerLoggedOn());
		userDt.setCustomerLoggedOn(false);
		assertFalse(userDt.isCustomerLoggedOn());
	}
	
	@Test
	public void testFrontDeskStaffLoggedIn(){
		// intially false
		assertFalse(userDt.isFdsLoggedOn());
		//now set to true and check
		userDt.setFdsLoggedOn(true);
		assertTrue(userDt.isFdsLoggedOn());
	}
	
	@Test
	public void testMansgerLoggedIn(){
		//initally false
		assertFalse(userDt.isManagerLoggedOn());
		//now set to true and check
		userDt.setManagerLoggedOn(true);
		assertTrue(userDt.isManagerLoggedOn());
	}

}
