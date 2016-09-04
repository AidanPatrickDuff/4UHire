package com.ait.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;

import com.ait.beans.Booking;
import com.ait.beans.BookingDataTable;
import com.ait.beans.Users;
import com.ait.beans.UsersDataTable;
import com.ait.beans.Vehicle;

public class BookingDataTableTest {
	BookingDataTable booking = new BookingDataTable();
	UsersDataTable user =new UsersDataTable();
	
    Date date = new Date();
	
	ArrayList<Booking> userBooking = new ArrayList<Booking>();
	Vehicle ford = new Vehicle("00g1234", "Ford", "Fiesta", 2.5, "2L", "Blue Fiesta", true);
	Users tomJones = new Users("Pat", "Jones", "0948", "tom@ait.ie", "Dublin Rd", "Athlone", "W Meath", true, 1, "root", true, userBooking);
	Booking book = new Booking();
	@Test 
	public void testNumberOfDays(){
		booking.init();
		
		booking.setNumberOfDays(3);
		booking.setSpecialRequests("Child Seat");
		booking.setStartDate(date);
		
		assertEquals(3, booking.getNumberOfDays());
		assertEquals("Child Seat", booking.getSpecialRequests());
		assertEquals(date, booking.getStartDate());
		assertEquals("4UHire", booking.getCompanyName());
		
		//need to add a Customer to test
		user.init();
		UsersDataTable.customersCollection.add(tomJones);
		assertEquals(3, user.getCustomersCollection().size());
		assertEquals("tom@ait.ie", user.getCustomersCollection().get(2).getEmail());
		user.getCustomersCollection();
		user.setRole(1);
		UsersDataTable.setUserLoggedInEmail("tom@ait.ie");
		assertEquals("tom@ait.ie", UsersDataTable.getUserLoggedInEmail());
		//get booking before add
		assertEquals(4, booking.getBookingCollection().size());
		
		// now remove one to test remove
		Booking bookingRemove = new Booking(booking.getSpecialRequests(), booking.getStartDate(), booking.getNumberOfDays(), ford, tomJones, 100);
		booking.deleteBooking(bookingRemove);
		assertEquals(4, booking.getBookingCollection().size());
		
		
		//when values are set we can test adding a booking
		UsersDataTable.setUserLoggedInEmail("tom@ait.ie");
		booking.addBooking();
		//now should be 4
		assertEquals(5, booking.getBookingCollection().size());
		
	}	
	
	@Test
	public void testShowButton(){
		booking.setShowSaveButton(true);
		assertTrue(booking.isShowSaveButton());
	}
	
	@Test
	public void testSaveAction(){
		BookingDataTable.bookingCollection.add(book);
		booking.saveAction();
		assertFalse(booking.isShowSaveButton());
		booking.editBooking(book);
	}
}






