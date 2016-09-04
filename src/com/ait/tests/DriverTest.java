package com.ait.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ait.beans.Driver;

public class DriverTest {

	@Test
	public void testConstructor() {
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		assertEquals(1, driver.getDriverID());
		assertEquals("John", driver.getFirstname());
		assertEquals("Smith", driver.getSurname());
		assertEquals("0864579832", driver.getContactNumber());
		assertEquals("DL54879", driver.getLicenseNumber());
		assertEquals("open", driver.getInsuranceDetails());
		assertEquals(3, driver.getPenaltyPoints());
		assertEquals(false, driver.isCanEdit());
	}
	
	@Test
	public void testDeafultConstructor() {
		Driver driver = new Driver();
		assertEquals(0, driver.getDriverID());
		assertEquals("", driver.getFirstname());
		assertEquals("", driver.getSurname());
		assertEquals("", driver.getContactNumber());
		assertEquals("", driver.getLicenseNumber());
		assertEquals("", driver.getInsuranceDetails());
		assertEquals(0, driver.getPenaltyPoints());
	}
	
	@Test
	public void testsetDriverId(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driver.setDriverID(3);
		assertEquals(3, driver.getDriverID());
	}
	
	@Test
	public void testsetFirstname(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driver.setFirstname("Gerry");
		assertEquals("Gerry", driver.getFirstname());
	}
	
	@Test
	public void testsetSurname(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driver.setSurname("Kenny");
		assertEquals("Kenny", driver.getSurname());
	}
	
	@Test
	public void testsetContactNumber(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driver.setContactNumber("0854578547");
		assertEquals("0854578547", driver.getContactNumber());
	}
	
	
	@Test
	public void testsetLicenseNumber(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driver.setLicenseNumber("NK654987");
		assertEquals("NK654987", driver.getLicenseNumber());
	}
	
	@Test
	public void testsetInsuranceDetails(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driver.setInsuranceDetails("Fully comp");
		assertEquals("Fully comp", driver.getInsuranceDetails());
	}
	
	@Test
	public void testsetPenaltyPoints(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driver.setPenaltyPoints(4);
		assertEquals(4, driver.getPenaltyPoints());
	}
	
	@Test
	public void testsetCanEdit(){
		Driver driver = new Driver(1, "John", "Smith", "0864579832", "DL54879", "open", 3);
		driver.setCanEdit(true);
		assertEquals(true, driver.isCanEdit());
	}
	

}
