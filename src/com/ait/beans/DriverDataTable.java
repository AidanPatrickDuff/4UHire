package com.ait.beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class DriverDataTable {
	private int driverID;
	private String firstname;
	private String surname; 
	private String contactNumber;
	private String licenseNumber;
	private String insuranceDetails;
	private int penaltyPoints;
	private static boolean showSaveButton;

	public ArrayList<Driver> driversCollection = new ArrayList<Driver>(); 
		
	@PostConstruct
	public void init() {

		Driver d1 = new Driver(1, "John", "Smith", "0879250585", "DL654987", "Fully Comp", 3);
		Driver d2 = new Driver(2, "Barry", "Pratt", "0854548789", "DL321548", "Fully Comp", 0);
		Driver d3 = new Driver(3, "Kevin", "McCabe", "0875489658", "DK457203", "Third Party", 1);
			
		driversCollection.add(d1);
		driversCollection.add(d2);
		driversCollection.add(d3);
			  
	}

	
	
	public ArrayList<Driver> getDriversCollection(){
		return driversCollection;
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


		
	public String addDriver(){
		final Driver driver = new Driver(this.driverID, this.firstname, this.surname, this.contactNumber, this.licenseNumber,this.insuranceDetails, this.penaltyPoints);			
		this.driversCollection.add(driver);
		this.driverID = 0;
		this.firstname ="";
		this.surname = ""; 
		this.contactNumber = "";
		this.licenseNumber = "";
		this.insuranceDetails = "";
		this.penaltyPoints = 0;

		return "UpdateDrivers";
	}
	
	public String editDriver(Driver driver){
		driver.setCanEdit(true);
		showSaveButton = true;
		return null;
	}
	
	public String saveAction(){
		for(Driver d: driversCollection){
			d.setCanEdit(false);
		}
		showSaveButton = false;
		return null;
	}
	
	public boolean isShowSaveButton(){
		return showSaveButton;
	}
	
	public void setShowSaveButton(boolean saveButton){
		showSaveButton = saveButton;
	}
	
	public String deleteDriver(Driver driver){
		driversCollection.remove(driver);
		return null;
	}
		
		
}

