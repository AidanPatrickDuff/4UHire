package com.ait.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Events {
	

	private String eventType;
	private double discount;
	private String description; 
	private double price;
	boolean canEdit;
	
	public Events(){
		eventType = "not entered";
		discount = 0;
		description = "not entered";
		price = 0; 

	}
	
	
	public Events(String eventType, double discount, String description, double price) {
		this.eventType = eventType;
		this.discount = discount;
		this.description = description;
		this.price = price;
	}

	public boolean isCanEdit() {
		return canEdit;
	}


	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
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

}