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
public class BookingDataTable {
	private String companyName = "4UHire";
	private String specialRequests;
	private Date startDate;
	private int numberOfDays;
	private boolean showSaveButton;
	private double totalPrice;
	
	// private EventsDataTable event = new EventsDataTable();
	// private ArrayList<Events> allEvents = event.getEventsCollection();
	public static ArrayList<Booking> bookingCollection = new ArrayList<Booking>();
 
	VehicleDataTable vDT = new VehicleDataTable();
	UsersDataTable udt = new UsersDataTable();

	@PostConstruct
	public void init() {
		Calendar myCalendar = new GregorianCalendar(2016, 7, 25);
		Date myDate = myCalendar.getTime();

		Users tomJones = new Users("Tom", "Jones", "0879994447", "tomjones@hotmail.com", "1 River Road", "Ballyfermot",
				"Dublin", true, 1, "", true, bookingCollection);
		Vehicle audi = new Vehicle("11d", "Audi", "A4", 100, "100cc", "wow", false);

		Booking b1 = new Booking("Wheelchair access", myDate, 2, audi, tomJones, 120);
		Booking b2 = new Booking("Food", myDate, 3, audi, tomJones, 155);
		Booking b3 = new Booking("No Smoking", myDate, 4, audi, tomJones, 200);

		bookingCollection.add(b1);
		bookingCollection.add(b2);
		bookingCollection.add(b3);
	}

	public String getCompanyName() {
		return companyName;
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

	public ArrayList<Booking> getBookingCollection() {
		return bookingCollection;
	}

	UsersDataTable uDT = new UsersDataTable();

	public String addBooking() {
		String email = UsersDataTable.getUserLoggedInEmail();

		for (Users c : udt.getCustomersCollection()) {
			if (c.getEmail().equals(email)) {
				udt.setEmail(c.getEmail());
				udt.setFirstName(c.getFirstName());
			}
		}

		Users u = new Users(UsersDataTable.usersFirstName(), UsersDataTable.usersSecondName(),
				UsersDataTable.usersContact(), UsersDataTable.usersEmail(), "", "", "", true,
				UsersDataTable.usersRole(), "", true, udt.getBookings()); 

		Vehicle v = new Vehicle(VehicleDataTable.vehiclesReg(), VehicleDataTable.vehiclesMake(), VehicleDataTable.vehiclesModel(),
				VehicleDataTable.vehiclePricePerDay(), vDT.getEngineSize(), vDT.getDetails(), vDT.isDisabledAccess());
		this.totalPrice = VehicleDataTable.vehiclePricePerDay() * this.numberOfDays;
		final Booking booking = new Booking(this.specialRequests, this.startDate, this.numberOfDays, v, u, this.totalPrice);
		bookingCollection.add(booking);

		// now add the booking to the user
		// get collection of all users
		for (Users user : uDT.getCustomersCollection()) {
			if (user.getEmail().equals(UsersDataTable.usersEmail())) {
				user.getBookings().add(booking);
			} // end IF Email
		}

		this.specialRequests = null;
		this.startDate = null;
		this.numberOfDays = 0;

		udt.setEmail(null);
		udt.setFirstName(null);

		return "index";
	}

	public String getSpecialRequests() {
		return specialRequests;
	}

	public void setSpecialRequests(String specialRequests) {
		this.specialRequests = specialRequests;
	}

	public boolean isShowSaveButton() {
		return showSaveButton;
	}

	public void setShowSaveButton(boolean showSaveButton) {
		this.showSaveButton = showSaveButton;
	}
	
	public String editBooking(Booking booking){
		booking.setCanEdit(true);
		showSaveButton = true;
		return null;
	}
	
	
	public String saveAction(){
		for(Booking bookings:bookingCollection){
			bookings.setCanEdit(false);
		}
		
		showSaveButton = false;
		return null;
	}
	
	public String deleteBooking(Booking booking){
		bookingCollection.remove(booking);
		UsersDataTable.UserLoggedInBookings().remove(booking);
		return null;
	}
	

}
