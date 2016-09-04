 package com.ait.beans;



import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EventBooking {
	private Date startDate;
	private int numberOfDays;
	private Events event;
	private Users user;
	private Driver driver;
	private boolean canEdit;
	private double totalPrice;
	
	public EventBooking(){
		
	}
	
	public EventBooking(Date startDate, int numberOfDays, Events event, Users user, Driver driver, double total) {
		this.startDate = startDate;
		this.numberOfDays = numberOfDays;
		this.event = event;
		this.user = user;
		this.driver = driver;
		this.totalPrice = total;
	}
	
	public String getStartDate() {
		return DateUtils.formatDate(startDate);
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
		return event;
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

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
}
