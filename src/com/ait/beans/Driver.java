package com.ait.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Driver {
	 
	private int driverID;
	private String firstname;
	private String surname;
	private String contactNumber;
	private String licenseNumber;
	private String insuranceDetails;
	private int penaltyPoints;
	private boolean canEdit;
	
	
	public Driver(){
		this.driverID = 0;
		this.firstname = "";
		this.surname = "";
		this.contactNumber = "";
		this.licenseNumber = "";
		this.insuranceDetails = "";
		this.penaltyPoints = 0;
	}
	
	
	public Driver(int driverID, String firstname, String surname, String contactNumber, String licenseNumber,
			String insuranceDetails, int penaltyPoints) {
		this.driverID = driverID;
		this.firstname = firstname;
		this.surname = surname;
		this.contactNumber = contactNumber;
		this.licenseNumber = licenseNumber;
		this.insuranceDetails = insuranceDetails;
		this.penaltyPoints = penaltyPoints;
	}
	
	
	public boolean isCanEdit(){
		return canEdit;
	}
	
	public void setCanEdit(boolean canEdit){
		this.canEdit = canEdit;
	}
	
	public int getDriverID() {
		return driverID;
	}

	

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getLicenseNumber() {
		return licenseNumber;
	}


	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}


	public String getInsuranceDetails() {
		return insuranceDetails;
	}


	public void setInsuranceDetails(String insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}


	public int getPenaltyPoints() {
		return penaltyPoints;
	}


	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}  



	
	

}
