package com.ait.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import com.ait.beans.Booking;
import com.ait.beans.Driver;
import com.ait.beans.EventBooking;
import com.ait.beans.Events;
import com.ait.beans.Users;
import com.ait.beans.UsersDataTable;
import com.ait.beans.Vehicle;

public class UsersTest {
	private Users user = new Users();
	private ArrayList<Booking> bookings = new ArrayList<Booking>();

	private ArrayList<Users> users = new ArrayList<Users>();

	Calendar myCalendar = new GregorianCalendar(2016, 7, 25);
	Date myDate = myCalendar.getTime();

	// default vehicle for a bookings
	Vehicle vehicle = new Vehicle("161D940", "audi", "A4", 100, "1000cc", "lovely car", false);
	// create default bookings
	Booking booking = new Booking("Child Set", myDate, 2, vehicle, user, 100);
	Events event = new Events("wedding", 0, "big day", 100);
	Driver driver = new Driver();
	EventBooking eventBooking = new EventBooking(myDate, 0, event, user, driver, 100);

	@Before
	public void setUp() {
		user = new Users("john", "Doe", "00948", "john@ait.ie", "address", "address2", "county", true, 1, "password",
				true, bookings);
	}

	// create a user
	@Test
	public void testCreateUser() {
		// will test set
		user.setFirstName("cormac");
		user.setSecondName("hallinan");
		user.setContactNumber("09475");
		user.setEmail("cormac@ait.ie");
		user.setAddress1("ballykilcash");
		user.setAddress2("Dromore West");
		user.setCounty("Sligo");
		user.setAccountActive(true);
		user.setRole(1);
		user.setPassword("root");
		user.setLoggedIn(true);
		user.setCanEdit(true);

		// test if proper values return => will test get as well
		assertEquals("cormac", user.getFirstName());
		assertEquals("hallinan", user.getSecondName());
		assertEquals("09475", user.getContactNumber());
		assertEquals("cormac@ait.ie", user.getEmail());
		assertEquals("ballykilcash", user.getAddress1());
		assertEquals("Dromore West", user.getAddress2());
		assertEquals("Sligo", user.getCounty());
		assertTrue(user.isAccountActive());
		assertEquals("root", user.getPassword());
		assertEquals(1, user.getRole());
		assertTrue(user.isLoggedIn());
		assertTrue(user.isCanEdit());

	}

	// test user bookings
	@Test
	public void testUserBookings() {
		assertEquals(0, user.getNumberOfBooking(bookings));

		bookings.add(booking);
		// user.setBookings(booking);
		assertEquals(1, user.getNumberOfBooking(bookings));

		bookings.add(booking);
		// user.setBookings(booking);
		assertEquals(2, user.getNumberOfBooking(bookings));

	}

	// test user booking an event
	@Test
	public void testUserEventbooking() {
		assertEquals(0, user.getEventsBookings().size());
		user.setEventsBookings(event);
		assertEquals(1, user.getEventsBookings().size());
	}

	// test logout
	@Test
	public void testLogOut() {
		assertFalse(user.logout());
	}

	// test users roles
	@Test
	public void testUserRoles() {
		assertEquals(1, user.getRole()); // role 1 hes Management
		// set him to 2 => Front Desk Staff
		user.setRole(2);
		assertEquals(2, user.getRole()); // role 2 hes Front Desk Staff
		user.setRole(3);
		assertEquals(3, user.getRole()); // role 3 hes Customer
		// any user that manages to get a role over 3 i.e 4,5,6...
		// should return 0 and is invalid user
		user.setRole(4);
		assertEquals(0, user.getRole());
	}

	@Test
	public void TestBookings() {
		user.setBookings(booking);
		assertEquals(bookings, user.getBookings());
	}

}
