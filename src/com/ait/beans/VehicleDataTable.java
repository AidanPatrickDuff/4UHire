package com.ait.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.annotation.PostConstruct;

@ManagedBean
@SessionScoped
public class VehicleDataTable {
	private String companyName;
	private static String vehicleReg;
	private static String vehicleMake;
	private static String vehicleModel;
	private static double pricePerDay;
	private String engineSize;
	private String details;
	private boolean disabledAccess;
	private boolean vehicleFound;
	private boolean noVehicleFound;
	private static boolean showSaveButton;
	private ArrayList<Vehicle> vehiclesCollection = new ArrayList<Vehicle>();
	private ArrayList<Vehicle> foundVehiclesCollection = new ArrayList<Vehicle>();
	
	public VehicleDataTable() {
		
	}

	@PostConstruct
	public void init() { // init
		this.companyName = "4UHire"; 
		Vehicle v1 = new Vehicle("11WH1234", "VW", "Polo", 65.00, "100cc",
				"Black Volkswagen Polo. Small sized car suitable for city trips", false);
		Vehicle v2 = new Vehicle("10OY3214", "BMW", "318i", 95.00, "2918cc",
				"BMW Touring class. Leather interior.", false);
		Vehicle v3 = new Vehicle("11OY123", "Audi", "A4", 100, "2000cc", "Lovely Car", true);
		Vehicle v4 = new Vehicle("12SO5674", "Porche", "911", 1000, "3000", "Its a Porche", false);
		Vehicle v5 = new Vehicle("03LM1465", "Audi", "A6", 125, "2500cc", "Saloon", false);

		vehiclesCollection.add(v1); 
		vehiclesCollection.add(v2);
		vehiclesCollection.add(v3);
		vehiclesCollection.add(v4);
		vehiclesCollection.add(v5);
	}
 
	public String isSelectedVehicle(String vehicleReg) {
		this.vehicleReg = vehicleReg;

		for (Vehicle v : vehiclesCollection) {
			if (v.getVehicleReg().equals(this.vehicleReg)) {
				this.vehicleMake = v.getVehicleMake();
				this.vehicleModel = v.getVehicleModel();
				this.pricePerDay = v.getPricePerDay();
				this.engineSize = v.getEngineSize();
				this.details = v.getDetails();
				this.disabledAccess = v.isDisabledAccess();
			}
		}
		return "ViewSingleVehicle";
	}

	public boolean isVehicleFound() {
		return vehicleFound;
	}

	public void setVehicleFound(boolean vehicleFound) {
		this.vehicleFound = vehicleFound;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVehicleReg() {
		return VehicleDataTable.vehicleReg;
	}

	public void setVehicleReg(String vehicleReg) {
		VehicleDataTable.vehicleReg = vehicleReg;
	}

	public static String vehiclesReg() {
		return vehicleReg;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}
	
	public static String vehiclesMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}
	
	public static String vehiclesModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}
	
	public static double vehiclePricePerDay(){
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getEngineSize() {
		return engineSize;
	}

	public void setEngineSize(String engineSize) {
		this.engineSize = engineSize;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isDisabledAccess() {
		return disabledAccess;
	}

	public void setDisabledAccess(boolean disabledAccess) {
		this.disabledAccess = disabledAccess;
	}

	public ArrayList<Vehicle> getVehiclesCollection() {
		return vehiclesCollection;
	}

	public void setVehiclesCollection(ArrayList<Vehicle> vehiclesCollection) {
		this.vehiclesCollection = vehiclesCollection;
	}

	public String addVehicle() {
		String vehicleStatus = "";
		final Vehicle v = new Vehicle(vehicleReg, this.vehicleMake, this.vehicleModel, pricePerDay,
				this.engineSize, this.details, this.disabledAccess);
		int index = vehicleAlreadyExists(vehicleReg);
		if (index == -1) {
			vehiclesCollection.add(v);
			vehicleStatus = "UpdateVehicles";
			
			vehicleReg = null;
			this.vehicleMake = null;
			this.vehicleModel = null;
			pricePerDay = 0.00;
			this.engineSize = null;
			this.details = null;
		} else {
			vehicleStatus = null;
		}
		
		return vehicleStatus;

	}
	
	public String editVehicle(Vehicle vehicle) {
		vehicle.setCanEdit(true);
		showSaveButton = true;
		return null;
	}
	
	public String saveAction() {
		for (Vehicle vehicle : vehiclesCollection) {
			vehicle.setCanEdit(false);
		}
		showSaveButton = false;
		return null;
	}
	
	public String deleteVehicle(Vehicle vehicle) {
		vehiclesCollection.remove(vehicle);
		return null;
	}

	public ArrayList<Vehicle> getFoundVehiclesCollection() {
		return foundVehiclesCollection;
	}

	public void setFoundVehiclesCollection(ArrayList<Vehicle> foundVehiclesCollection) {
		this.foundVehiclesCollection = foundVehiclesCollection;
	}

	public boolean isNoVehicleFound() {
		return noVehicleFound;
	}
	
	
	public boolean isShowSaveButton() {
		return showSaveButton;
	}

	public void setShowSaveButton(boolean saveButton) {
		showSaveButton = saveButton;
	}

	public void setNoVehicleFound(boolean noVehicleFound) {
		this.noVehicleFound = noVehicleFound;
	}

	public void searchByMake() {
		noVehicleFound = false;
		vehicleFound = false;
		foundVehiclesCollection.clear();
		int vehicleIndex = -1;
		Vehicle vehicle;
		for (int i = 0; i < vehiclesCollection.size(); i++) {
			if (vehiclesCollection.get(i).getVehicleMake().equalsIgnoreCase(vehicleMake)) {
				vehicleIndex = i;
				vehicleFound = true;
				if (vehicleIndex != -1) {
					vehicle = vehiclesCollection.get(vehicleIndex);
					foundVehiclesCollection.add(vehicle);
				}
			}

		}
		if (foundVehiclesCollection.size() == 0) {
			noVehicleFound = true;
		}

	}

	public int vehicleAlreadyExists(String regNo) {
		int i = -1;
		
		if (vehiclesCollection.size() == 0) {
			i = -1;
			
		} else{
			for (Vehicle v : vehiclesCollection) {
				if (v.getVehicleReg().equalsIgnoreCase(regNo)) {
					i = vehiclesCollection.indexOf(v);
				} 
			}
		}

		return i;

	}

}
