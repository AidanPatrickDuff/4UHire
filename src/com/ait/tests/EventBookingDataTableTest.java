package com.ait.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.ait.beans.Booking;
import com.ait.beans.Driver;
import com.ait.beans.EventBooking;
import com.ait.beans.EventBookingDataTable;
import com.ait.beans.Events;
import com.ait.beans.Users;
import com.ait.beans.UsersDataTable;

public class EventBookingDataTableTest {
	EventBookingDataTable eventBooking = new EventBookingDataTable();
	Events event = new Events();
	Driver driver = new Driver();
	Users user = new Users();
	ArrayList<Booking> bookings;

	Date date = new Date();

	@Test
	public void testInit() {
		eventBooking.init();
	}

	@Test
	public void testBookingData() {
		eventBooking.setStartDate(date);
		assertEquals(date, eventBooking.getStartDate());

		eventBooking.setNumberOfDays(2);
		assertEquals(2, eventBooking.getNumberOfDays());

		eventBooking.setEvent(event);
		assertEquals(event, eventBooking.getEvent());

		eventBooking.setDriver(driver); 
		assertEquals(driver, eventBooking.getDriver());

		eventBooking.setUser(user);
		assertEquals(user, eventBooking.getUser());

		// set the email of user logged in
		UsersDataTable.setUserLoggedInEmail("pat@ait.ie");

		// need to add a customer who is maing the bookings
		user = new Users("pat", "test", "0000", "pat@ait.ie", "", "", "", true, 3, "", true, bookings);
		UsersDataTable.customersCollection.add(user);

		// should be one before added as one in collection from calling init();
		assertEquals(1, eventBooking.getEventBookingCollection().size());
		eventBooking.addEventBooking();
		
		// now lets add this event
		// before add
		// should aready be one in it from the init
		assertEquals(2, eventBooking.getEventBookingCollection().size());
		// add
		eventBooking.addEventBooking();
		// get eventscollection and check if added
		assertEquals(3, eventBooking.getEventBookingCollection().size());
		EventBooking evntbooking = new EventBooking(eventBooking.getStartDate(), eventBooking.getNumberOfDays(),
				eventBooking.getEvent(), eventBooking.getUser(), eventBooking.getDriver(), eventBooking.getTotalPrice()); //getTotalPrice added
		eventBooking.editEventBooking(evntbooking);
		eventBooking.getEventBookingCollection().add(evntbooking);
		eventBooking.deleteEventBooking(evntbooking);
		assertEquals(3, eventBooking.getEventBookingCollection().size());
	}

	@Test
	public void testShowSaveButton() {
		eventBooking.setShowSaveButton(true);
		assertTrue(eventBooking.isShowSaveButton());
		
		eventBooking.saveAction();
	}
	
	@Test
	public void testTotalPrice(){
		eventBooking.setTotalPrice(120.2);
		assertEquals(120.2, eventBooking.getTotalPrice(),000);
	}

}
