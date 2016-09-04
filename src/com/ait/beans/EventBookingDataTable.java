package com.ait.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EventBookingDataTable {
	private Date startDate;
	private int numberOfDays; 
	private Events event; 
	private Users user;
	private Driver driver;
	private boolean showSaveButton;
	private double totalPrice;
	
	public static ArrayList<EventBooking> eventBookingCollection = new ArrayList<EventBooking>();
	Users usr =new Users();
	@PostConstruct
	public void init(){
		Calendar myCalendar = new GregorianCalendar(2016, 7, 25);
		Date myDate = myCalendar.getTime();
		event = new Events("Communion", EventsDataTable.eventDiscount(), EventsDataTable.eventDescription(), EventsDataTable.eventPrice());
		
		user = new Users("John Joe", "McGee", "11111", "John@ait", "", "", 
				"", true, 1, "", true, UsersDataTable.usersBookings());
		
		driver = new Driver(1, "joe", "Jeeves", "contactNumber", "licenseNumber", "insuranceDetails", 2);
		
		final EventBooking eventBooking = new EventBooking(myDate, 2, event, user, driver, 120);
		
		eventBookingCollection.add(eventBooking);
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public Events getEvent() {
		return this.event;
	}
	public void setEvent(Events event) {
		this.event = event;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public String addEventBooking(){
		
		
		String email = UsersDataTable.getUserLoggedInEmail();
		UsersDataTable udt = new UsersDataTable();
		
		for(Users c : udt.getCustomersCollection()){
			if(c.getEmail().equals(email)){
				udt.setEmail(c.getEmail());
				udt.setFirstName(c.getFirstName());
			}
		}
		
		user = new Users(UsersDataTable.usersFirstName(), UsersDataTable.usersSecondName(),
				UsersDataTable.usersContact(), UsersDataTable.usersEmail(), "", "", "", true,
				UsersDataTable.usersRole(), "", true, udt.getBookings()); // get booking could be adding on multiple
		
		event = new Events(EventsDataTable.eventEventType(), EventsDataTable.eventDiscount(), EventsDataTable.eventDescription(), EventsDataTable.eventPrice());
		
		driver = new Driver(1, "joe", "smith", "contactNumber", "licenseNumber", "insuranceDetails", 2);
		this.totalPrice = EventsDataTable.eventPrice() * numberOfDays;
		final EventBooking eventBooking = new EventBooking(startDate, numberOfDays, event, user, driver, totalPrice);
		
		eventBookingCollection.add(eventBooking);
		
		for (Users user : udt.getCustomersCollection()) {
			if (user.getEmail().equals(UsersDataTable.usersEmail())) {
				//user.getBookings().add(eventBooking);
				user.getEventsBookings().add(event);
			} // end IF Email
		}
		
		this.startDate = null;
		this.numberOfDays = 0;
		
		udt.setEmail(null);
		udt.setFirstName(null);
		
		return "index";
	}
	
	public ArrayList<EventBooking> getEventBookingCollection() {
		return eventBookingCollection;
	}
	
	public boolean isShowSaveButton() {
		return showSaveButton;
	}

	public void setShowSaveButton(boolean showSaveButton) {
		this.showSaveButton = showSaveButton;
	}
	
	
	public String editEventBooking(EventBooking eventBooking){
		eventBooking.setCanEdit(true);
		showSaveButton = true;
		return null;
	}
	
	
	public String saveAction(){
		for(EventBooking eventBooking:eventBookingCollection){
			eventBooking.setCanEdit(false);
		}
		showSaveButton = false;
		return null;
	}
	
	public String deleteEventBooking(EventBooking eventBooking){
		eventBookingCollection.remove(eventBooking);
		UsersDataTable.userLoggedInEvents().remove(eventBooking);
		return null;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
