package com.ait.beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EventsDataTable {
	private String companyName;
	private static String eventType;
	private static double discount;
	private static String description;
	private static double price;
	private static boolean showSaveButton;
	private static double totalPrice;

	public static ArrayList<Events> eventsCollection = new ArrayList<Events>();

	VehicleDataTable vDT = new VehicleDataTable();
	UsersDataTable udt = new UsersDataTable();
	Users user = new Users();

	public EventsDataTable() {

	}

	@PostConstruct
	public void init() {
		this.companyName = "4UHire";

		Events e1 = new Events("Wedding", 0, "Bridal car plus bridemaid vehicle", 200);
		Events e2 = new Events("Birthday", 0, "Limo", 75);
		Events e3 = new Events("Communion", 0, "162 Audi R10", 100);

		eventsCollection.add(e3);
		eventsCollection.add(e2);
		eventsCollection.add(e1);

	}

	public String isSelectedEvent(String eventType) {
		// this.eventType = "Communion";
		this.eventType = eventType;

		for (Events e : eventsCollection) {
			if (e.getEventType().equals(this.eventType)) {
				this.description = e.getDescription();
				this.discount = e.getDiscount();
				this.price = e.getPrice();
			}
		}

		return "ViewSingleEvent";
	}

	public String getCompanyName() {
		return companyName;
	}

	public ArrayList<Events> getEventsCollection() {
		return eventsCollection;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String addEvent() {
		String eventStatus = "";
		boolean eventExists = true;
		for (Events e : eventsCollection) {
			if (e.getEventType().equals(eventType)) {
				eventExists = false;
			}
		}

		if (eventExists) {
			final Events event = new Events(eventType, discount, description, price);
			eventsCollection.add(event);

			for (Users user : udt.getCustomersCollection()) {
				if (user.getEmail().equals(UsersDataTable.getUserLoggedInEmail())) {
					user.getEventsBookings().add(event);
				} // end IF Email
			}

			eventType = null;
			discount = 0.0;
			description = null;
			price = 0.0;
			eventStatus = "UpdateEvents";
		}

		return eventStatus;
	}

	public static String eventEventType() {
		return eventType;
	}

	public static double eventDiscount() {
		return discount;
	}

	public static String eventDescription() {
		return description;
	}

	public static double eventPrice() {
		return price;
	}

	public String addDiscount() {
		int count = 0; // to count what index to edit
		for (Events e : eventsCollection) {
			if (e.getEventType().equals(eventType)) {
				price = e.getPrice() - ((e.getPrice() / 100) * discount);
				Events event = new Events(e.getEventType(), discount, e.getDescription(), price);
				eventsCollection.set(count, event);
			}
			count++;
		}
		return null;
	}

	public String editEvent(Events event) {
		event.setCanEdit(true);
		showSaveButton = true;
		return null;
	}

	public String saveAction() {
		for (Events e : eventsCollection) {
			e.setCanEdit(false);
		}
		showSaveButton = false;
		return null;
	}

	public boolean isShowSaveButton() {
		return showSaveButton;
	}

	public void setShowSaveButton(boolean saveButton) {
		showSaveButton = saveButton;
	}

	public String deleteEvent(Events event) {
		eventsCollection.remove(event);
		return null;
	}

	public static double getTotalPrice() {
		return totalPrice;
	}

	public static void setTotalPrice(double totalPrice) {
		EventsDataTable.totalPrice = totalPrice;
	}

}