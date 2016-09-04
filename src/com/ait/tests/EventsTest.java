package com.ait.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ait.beans.Events;

public class EventsTest {
	
	@Test
	public void testDefaultConstructor(){
		Events event = new Events();
		assertEquals("not entered", event.getEventType());
		assertEquals(0, event.getDiscount(), 0.0001);
		assertEquals("not entered", event.getDescription());
		assertEquals(0, event.getPrice(), 0.0001);
		
		
	}
	
	@Test
	public void testConstructor() {
		Events event = new Events("Wedding", .10, "Deluxe Package", 150.0);
		//assertEquals(1, event.getEventID());
		assertEquals("Wedding", event.getEventType());
		assertEquals(.10, event.getDiscount(), 0.0001);
		assertEquals("Deluxe Package", event.getDescription());
		assertEquals(150.0, event.getPrice(), 0.0001);
		assertEquals(false, event.isCanEdit());
		
	}
	
	@Test
	public void testSetEventType(){
		Events event = new Events("Wedding", .10, "Deluxe Package", 150.0);
		event.setEventType("Divorce");
		assertEquals("Divorce", event.getEventType());
	}
	
	@Test
	public void testSetDiscount(){
		Events event = new Events("Wedding", .10, "Deluxe Package", 150.0);
		event.setDiscount(.20);
		assertEquals(.20, event.getDiscount(), 0.0001);
	}
	
	@Test
	public void testSetDescription(){
		Events event = new Events("Wedding", .10, "Deluxe Package", 150.0);
		event.setDescription("FancyPantsPackage");
		assertEquals("FancyPantsPackage", event.getDescription());
	}
	
	@Test
	public void testSetPrice(){
		Events event = new Events("Wedding", .10, "Deluxe Package", 150.0);
		event.setPrice(210.00);
		assertEquals(210.00, event.getPrice(), 0.0001);
	}
	
	@Test
	public void testSetCanEdit(){
		Events event = new Events("Wedding", .10, "Deluxe Package", 150.0);
		event.setCanEdit(true);
		assertEquals(true, event.isCanEdit());
	}
	
	
	

}