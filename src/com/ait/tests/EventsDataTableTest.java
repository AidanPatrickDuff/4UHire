package com.ait.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ait.beans.Events;
import com.ait.beans.EventsDataTable;
import com.ait.beans.Users;
import com.ait.beans.UsersDataTable;

public class EventsDataTableTest {
	EventsDataTable eventsDataTable = new EventsDataTable();
	Events events = new Events();
	Users user = new Users();

	@Before
	public void testInit() {
		eventsDataTable.init();

	}

	@Test
	public void testAddEventData() {
		// create event for user test email
		UsersDataTable.setUserLoggedInEmail("test@ait.ie");
		assertEquals("test@ait.ie", UsersDataTable.getUserLoggedInEmail());
		user = new Users();
		user.setEmail("test@ait.ie");
		// add test to list of customers
		UsersDataTable.customersCollection.add(user);
		
		assertEquals("4UHire", eventsDataTable.getCompanyName());

		eventsDataTable.setEventType("Communion");
		assertEquals("Communion", eventsDataTable.getEventType());

		eventsDataTable.setDiscount(5.0);
		assertEquals(5.0, eventsDataTable.getDiscount(), 0.001);

		eventsDataTable.setDescription("big limo");
		assertEquals("big limo", eventsDataTable.getDescription());

		eventsDataTable.setPrice(100.0);
		assertEquals(100.0, eventsDataTable.getPrice(), 0.001);

		assertEquals("Communion", EventsDataTable.eventEventType());
		assertEquals(5.0, EventsDataTable.eventDiscount(), 0.0000001);
		assertEquals("big limo", EventsDataTable.eventDescription());
		assertEquals(100, EventsDataTable.eventPrice(), 0.0000001);
		
		eventsDataTable.isSelectedEvent("Communion");

		eventsDataTable.setEventType("Communion1");
		assertEquals(21, eventsDataTable.eventsCollection.size());
		// add events details set should be added
		eventsDataTable.addEvent();
		// get collection and check size added one more
		assertEquals(22, eventsDataTable.eventsCollection.size());

	}
	
	@Test
	public void testAddDiscount(){
		// set event type for discount to be added to 
		eventsDataTable.setEventType("Wedding");
		// set what the disconut 
		eventsDataTable.setDiscount(5.0);
		eventsDataTable.addDiscount();
		assertEquals(190, eventsDataTable.getPrice(),000);
	}
	
	@Test
	public void testEditEvent(){
		assertEquals(null,eventsDataTable.editEvent(events));
		assertEquals(true, eventsDataTable.isShowSaveButton());
	}
	
	
	@Test
	public void testSaveAction(){
		assertEquals(null,eventsDataTable.saveAction());
		assertEquals(false, eventsDataTable.isShowSaveButton());
		
	}
	
	@Test
	public void testSetShowSaveButton(){
		assertEquals(false, eventsDataTable.isShowSaveButton());
		eventsDataTable.setShowSaveButton(true);
		assertEquals(true, eventsDataTable.isShowSaveButton());
	}
	
	@Test 
	public void testDeleteEvent(){
		
		Events myEvent = new Events();
		eventsDataTable.getEventsCollection().add(myEvent);
		assertEquals(4, eventsDataTable.getEventsCollection().size());
		eventsDataTable.deleteEvent(myEvent);
		assertEquals(3, eventsDataTable.getEventsCollection().size());
		
	}
	
	@Test
	public void testTotalPrice(){
		eventsDataTable.setTotalPrice(125.5);
		assertEquals(125.5, eventsDataTable.getTotalPrice(), 000);
		
	}
	
	

}
