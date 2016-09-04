
package com.ait.tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.ait.beans.Driver;
import com.ait.beans.EventBooking;
import com.ait.beans.Events;
import com.ait.beans.Users;

public class EventBookingTest {
	EventBooking eventBooking = new EventBooking();
	
	//seed event too add to event booking
	Events event = new Events();
	Users user = new Users();
	Driver driver = new Driver();
	
	Calendar myCalendar = new GregorianCalendar(2016, 7, 25);
	Date myDate = myCalendar.getTime();
	

	@Test
	public void testEventBookingConstrctor() {
		eventBooking = new EventBooking(myDate, 3, event, user, driver, 100);
	}
	
	@Test
	public void testStartDate(){
		Calendar myCalendar = new GregorianCalendar(2016, 8, 25);
		Date newDate = myCalendar.getTime();
		
		eventBooking.setStartDate(newDate);
		assertEquals("Sunday, September 25, 2016", eventBooking.getStartDate());
	}
	
	@Test
	public void testNmberOfDays(){
		eventBooking.setNumberOfDays(2);
		assertEquals(2, eventBooking.getNumberOfDays());
	}
	
	@Test
	public void testEvent(){
		eventBooking.setEvent(event);
		assertEquals(event, eventBooking.getEvent());
	}
	
	@Test
	public void testUser(){
		eventBooking.setUser(user);
		assertEquals(user, eventBooking.getUser());
	}
	
	@Test
	public void testDriver(){
		eventBooking.setDriver(driver);
		assertEquals(driver, eventBooking.getDriver());
	}
	
	@Test
	public void canEdit(){
		eventBooking.setCanEdit(true);
		assertTrue(eventBooking.isCanEdit());
	}
	
	@Test
	public void testTotalPrice(){
		eventBooking.setTotalPrice(120.5);
		assertEquals(120.5, eventBooking.getTotalPrice(), 0000);
	}

}
