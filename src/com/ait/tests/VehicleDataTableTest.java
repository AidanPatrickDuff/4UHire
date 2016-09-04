package com.ait.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ait.beans.Vehicle;
import com.ait.beans.VehicleDataTable;

public class VehicleDataTableTest {
	private VehicleDataTable vehicleDataTable = new VehicleDataTable();
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

	@Before
	public void setUp() {

	}

	@Test
	public void testAdd1Vehicle() {
		vehicleDataTable.setCompanyName("4UHire");
		vehicleDataTable.setVehicleReg("16WH134");
		vehicleDataTable.setVehicleMake("Citreon");
		vehicleDataTable.setVehicleModel("C5");
		vehicleDataTable.setPricePerDay(75);
		vehicleDataTable.setEngineSize("2500cc");
		vehicleDataTable.setDetails("Family Car");
		vehicleDataTable.setDisabledAccess(true);
		vehicleDataTable.addVehicle();
		vehicleDataTable.setVehicleFound(true);
		vehicleDataTable.setNoVehicleFound(true);
		
		
		vehicleDataTable.isSelectedVehicle("16WH134");
		
		// getters
		assertEquals("4UHire", vehicleDataTable.getCompanyName());
		assertEquals("16WH134", VehicleDataTable.vehiclesReg());
		assertEquals("16WH134", vehicleDataTable.getVehicleReg());
		assertEquals("Citreon", vehicleDataTable.getVehicleMake());
		assertEquals("C5", vehicleDataTable.getVehicleModel());
		assertEquals(75, vehicleDataTable.getPricePerDay(), 0.01);
		assertEquals(75, VehicleDataTable.vehiclePricePerDay(), 0.01);
		assertEquals("2500cc", vehicleDataTable.getEngineSize());
		assertEquals("Family Car", vehicleDataTable.getDetails());
		assertTrue(vehicleDataTable.isDisabledAccess());
		assertTrue(vehicleDataTable.isVehicleFound());
		assertTrue(vehicleDataTable.isNoVehicleFound());
		vehicleDataTable.addVehicle();
		// try add another with same details, vehicle should exist adn return null
		assertEquals(null, vehicleDataTable.addVehicle());
		
	}

	@Test
	public void testAddMoreVehicle() {
		Vehicle v = new Vehicle("10OY321", "Honda", "Civic", 85.0, "1500cc", "Family car", true);
		Vehicle v1 = new Vehicle("11WH1234", "VW", "Polo", 65.00, "100cc",
				"Black Volkswagen Polo. Small sized car suitable for city trips", false);
		Vehicle v2 = new Vehicle("10OY3214", "BMW", "318i", 95.00, "2918cc",
				"BMW Touring class. Leather interior.", false);
		VehicleDataTable vehicleDataTable = new VehicleDataTable();
		vehicleDataTable.getVehiclesCollection().add(v);
		vehicleDataTable.getVehiclesCollection().add(v1);
		vehicleDataTable.getVehiclesCollection().add(v2);
		assertEquals(3, vehicleDataTable.getVehiclesCollection().size());
		
		// now remove one
		vehicleDataTable.deleteVehicle(v2);
		assertEquals(2, vehicleDataTable.getVehiclesCollection().size());
	}

	@Test
	public void testSearchValidInput() {
		Vehicle v = new Vehicle("10OY321", "Honda", "Civic", 85.0, "1500cc", "Family car", true);
		VehicleDataTable vehicleDataTable = new VehicleDataTable();
		vehicleDataTable.getVehiclesCollection().add(v);
		vehicleDataTable.setVehicleMake("Honda");
		vehicleDataTable.searchByMake();
		vehicleDataTable.editVehicle(v);
		assertTrue(v.isCanEdit());
		vehicleDataTable.saveAction();
		assertFalse(v.isCanEdit());
		assertEquals(1, vehicleDataTable.getFoundVehiclesCollection().size());

	}

	@Test
	public void testSearchNotValidInput() {
		Vehicle v = new Vehicle("10OY321", "Honda", "Civic", 85.0, "1500cc", "Family car", true);
		VehicleDataTable vehicleDataTable = new VehicleDataTable();
		vehicleDataTable.getVehiclesCollection().add(v);
		vehicleDataTable.setVehicleMake("Porche");
		vehicleDataTable.searchByMake();
		assertEquals(0, vehicleDataTable.getFoundVehiclesCollection().size());
	}

	@Test
	public void testAddArray() {
		Vehicle v = new Vehicle("10OY321", "Honda", "Civic", 85.0, "1500cc", "Family car", true);
		Vehicle v1 = new Vehicle("11WH1234", "VW", "Polo", 65.00, "100cc",
				"Black Volkswagen Polo. Small sized car suitable for city trips", false);
		Vehicle v2 = new Vehicle("10OY3214", "BMW", "318i", 95.00, "2918cc",
				"BMW Touring class. Leather interior.", false);
		vehicles.add(v);
		vehicles.add(v1);
		vehicles.add(v2);
		vehicleDataTable.setVehiclesCollection(vehicles);
		assertEquals(3, vehicleDataTable.getVehiclesCollection().size());
		vehicleDataTable.setFoundVehiclesCollection(vehicles);
		assertEquals(3, vehicleDataTable.getFoundVehiclesCollection().size());
	}

	@Test
	public void testInit() {
		vehicleDataTable.init();
		assertEquals(5, vehicleDataTable.getVehiclesCollection().size());
	}
	
	@Test
	public void showSaveButton(){
		vehicleDataTable.setShowSaveButton(true);
		assertTrue(vehicleDataTable.isShowSaveButton());
	}
}