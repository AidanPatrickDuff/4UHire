package com.ait.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ait.beans.Driver;
import com.ait.beans.DriverDataTable;
import com.ait.beans.Events;

public class DriverDataTableTest {
	DriverDataTable driverDataTable = new DriverDataTable();
	
	@Test
	public void testInit(){
		driverDataTable.init();
		
		driverDataTable.getDriversCollection();
	}
	
	@Test
	public void testsetDriverId(){
		driverDataTable.setDriverID(3);
		assertEquals(3, driverDataTable.getDriverID());
	}
	
	@Test
	public void testsetFirstname(){
		driverDataTable.setFirstname("Gerry");
		assertEquals("Gerry", driverDataTable.getFirstname());
	}
	
	@Test
	public void testsetSurname(){
		driverDataTable.setSurname("Kenny");
		assertEquals("Kenny", driverDataTable.getSurname());
	}
	
	@Test
	public void testsetContactNumber(){
		driverDataTable.setContactNumber("0854578547");
		assertEquals("0854578547", driverDataTable.getContactNumber());
	}
	
	
	@Test
	public void testsetLicenseNumber(){
		driverDataTable.setLicenseNumber("NK654987");
		assertEquals("NK654987", driverDataTable.getLicenseNumber());
	}
	
	@Test
	public void testsetInsuranceDetails(){
		driverDataTable.setInsuranceDetails("Fully comp");
		assertEquals("Fully comp", driverDataTable.getInsuranceDetails());
	}
	
	@Test
	public void testsetPenaltyPoints(){
		driverDataTable.setPenaltyPoints(4);
		assertEquals(4, driverDataTable.getPenaltyPoints());
	}
	
	@Test
	public void testAddDriver(){
		assertEquals("UpdateDrivers", driverDataTable.addDriver());
	}
	
	@Test
	public void testEditDriver(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		assertEquals(null,driverDataTable.editDriver(driver));
		assertEquals(true, driverDataTable.isShowSaveButton());
	}
	
	@Test
	public void testSaveAction(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driverDataTable.getDriversCollection().add(driver);
		assertEquals(null,driverDataTable.saveAction());
		assertEquals(false, driverDataTable.isShowSaveButton());
		assertEquals(false, driverDataTable.getDriversCollection().get(0).isCanEdit());
		
	}
	
	@Test
	public void testSetShowSaveButton(){
		assertEquals(false, driverDataTable.isShowSaveButton());
		driverDataTable.setShowSaveButton(true);
		assertEquals(true, driverDataTable.isShowSaveButton());
	}
	
	@Test 
	public void testDeleteDriver(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driverDataTable.getDriversCollection().add(driver);
		assertEquals(1, driverDataTable.getDriversCollection().size());
		driverDataTable.deleteDriver(driver);
		assertEquals(0, driverDataTable.getDriversCollection().size());
		
	}

}
