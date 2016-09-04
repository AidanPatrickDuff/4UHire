package com.ait.tests;

import com.ait.beans.Vehicle;
import com.ait.beans.VehicleDataTable;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	public void testDefaultConstructor() {
		Vehicle v = new Vehicle();
		assertEquals("default",v.getVehicleReg());
		assertEquals("default",v.getVehicleMake());
		assertEquals("default",v.getVehicleModel());
		assertEquals(0.0,v.getPricePerDay(),0.001);
		assertEquals("default",v.getEngineSize());
		assertEquals("default",v.getDetails());
		assertEquals(false,v.isDisabledAccess());
		assertEquals(false,v.isCanEdit());
	}
	
	@Test
	public void testSettersGetters(){
		Vehicle v = new Vehicle();
		v.setVehicleReg("11wh1234");
		v.setVehicleMake("Honda");
		v.setVehicleModel("Civic");
		v.setPricePerDay(85.0);
		v.setDetails("Family car");
		v.setEngineSize("1500cc");
		v.setDisabledAccess(true);
		v.setCanEdit(true);
		assertEquals("11wh1234",v.getVehicleReg());
		assertEquals("Honda",v.getVehicleMake());
		assertEquals("Civic",v.getVehicleModel());
		assertEquals(85.0,v.getPricePerDay(),0.001);
		assertEquals("1500cc",v.getEngineSize());
		assertEquals("Family car",v.getDetails());
		assertEquals(true,v.isDisabledAccess());
	}
	
	@Test
	public void testConstructorWithFields(){
		Vehicle v = new Vehicle("10OY321", "Mercedes", "ClassC", 105.90,"2500cc", "Luxury car", true);
		assertEquals("10OY321",v.getVehicleReg());
		assertEquals("Mercedes",v.getVehicleMake());
		assertEquals("ClassC",v.getVehicleModel());
		assertEquals(105.90,v.getPricePerDay(),0.001);
		assertEquals("2500cc",v.getEngineSize());
		assertEquals("Luxury car",v.getDetails());
		assertEquals(true,v.isDisabledAccess());
	}
	
	
	@Test
	public void testSearchValidInput(){
		Vehicle v = new Vehicle("10OY321", "Honda", "Civic", 85.0,"1500cc", "Family car", true);
		VehicleDataTable myDataTable = new VehicleDataTable();
		myDataTable.getVehiclesCollection().add(v);
		myDataTable.setVehicleMake("Honda");
		myDataTable.searchByMake();
		assertEquals(1, myDataTable.getFoundVehiclesCollection().size());
				
	}
	
	@Test 
	public void testSearchNotValidInput(){
		Vehicle v = new Vehicle("10OY321", "Honda", "Civic", 85.0,"1500cc", "Family car", true);
		VehicleDataTable myDataTable = new VehicleDataTable();
		myDataTable.getVehiclesCollection().add(v);
		myDataTable.setVehicleMake("Porche");
		myDataTable.searchByMake();
		assertEquals(0, myDataTable.getFoundVehiclesCollection().size());
	}
	
	
	
	
	
	
	
	
	
	

}
