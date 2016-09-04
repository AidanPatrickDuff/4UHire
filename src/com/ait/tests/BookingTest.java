package com.ait.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.ait.beans.Booking;
import com.ait.beans.Users;
import com.ait.beans.Vehicle;

public class BookingTest {
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	Vehicle ford = new Vehicle("00g1234", "Ford", "Fiesta", 2.5, "2L", "Blue Fiesta", true);
	Users tomJones = new Users("Tom", "Jones", "0879994447", "tomjones@hotmail.com", "1 River Road","Ballyfermot", "Dublin", true, 1,"", true, bookings);
	Calendar myCalendar = new GregorianCalendar(2016, 7, 25);
	Date myDate = myCalendar.getTime();
	

	Booking booking = new Booking("Wheelchair access", myDate, 2, ford, tomJones, 100);
	
	@Test
	public void testConstructor() {
		Date date = new Date();
		
		assertEquals("Wheelchair access", booking.getSpecialRequests());
		assertEquals("Thursday, August 25, 2016", booking.getStartDate());
		booking.setStartDate(date);
		assertEquals(date, booking.getBookingDate());
		assertEquals(2, booking.getNumberOfDays());
		assertEquals(ford, booking.getVehicle());
		assertEquals(tomJones, booking.getUser());		
	}
	
	@Test 
	public void testSetSpecialRequests(){
		booking.setSpecialRequests("Diabetic food");
		assertEquals("Diabetic food", booking.getSpecialRequests());
	}
	
	@Test 
	public void testSetStartDate(){
		Calendar myCalendar = new GregorianCalendar(2016, 8, 25);
		Date newDate = myCalendar.getTime();
		
		booking.setStartDate(newDate);
		assertEquals("Sunday, September 25, 2016", booking.getStartDate());
	}
	
	@Test 
	public void testSetEndDate(){
		booking.setNumberOfDays(3);
		assertEquals(3, booking.getNumberOfDays());
	}
	
	@Test 
	public void testSetVehicle(){
		Vehicle volkswagon = new Vehicle("08D6543", "Renault", "Megane", 1.5, "2L", "Red Megane", true);
		booking.setVehicle(volkswagon);
		assertEquals(volkswagon, booking.getVehicle());
	}
	
	@Test 
	public void testSetUser(){
		Users johnBrown = new Users("John", "Brown", "0871234567", "johnbrown@gmail.com", "5 happy lane","Bray", "Wicklow", false, 1, "", true, bookings);
		booking.setUser(johnBrown);
		assertEquals(johnBrown, booking.getUser());
	} 
	
	@Test
	public void canEdit(){
		Booking bookin = new Booking();
		bookin.setCanEdit(true);
		assertTrue(bookin.isCanEdit());
	}
	
	@Test
	public void testTotalPrice(){
		booking.setTotalPrice(111.1);
		assertEquals(111.1, booking.getTotalPrice(),000);
	}
}






