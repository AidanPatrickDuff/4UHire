package com.ait.beans;


import java.util.Date;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Booking {
	private String specialRequests;
	private Date startDate;
	private int numberOfDays;
	private Vehicle vehicle;
	private Users customer;
	private boolean canEdit;
	private double totalPrice;

	public Booking(String specialRequests, Date startDate, int numberOfDays, Vehicle vehicle, Users user, double price) {
		this.specialRequests = specialRequests;
		this.startDate = startDate;
		this.numberOfDays = numberOfDays;
		this.vehicle = vehicle;
		this.customer = user; 
		this.totalPrice = price;
	}
	
	public Booking(){
		
	}
	

	public boolean isCanEdit() {
		return canEdit;
	}


	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}


	public String getSpecialRequests() {
		return specialRequests;
	}

	public void setSpecialRequests(String specialRequests) {
		this.specialRequests = specialRequests;
	}

	public String getStartDate() {
		//to display date as string
		return DateUtils.formatDate(startDate);
	}
	
	public Date getBookingDate(){
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Users getUser() {
		return customer;
	}

	public void setUser(Users customer) {
		this.customer = customer;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
