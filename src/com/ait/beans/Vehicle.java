package com.ait.beans;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Vehicle { 
	private String vehicleReg;
	private String vehicleMake;
	private String vehicleModel;
	private double pricePerDay;
	private String engineSize;
	private String details;
	private boolean disabledAccess;
	private boolean canEdit;
	

	public Vehicle() {
		this.vehicleReg = "default";
		this.vehicleMake = "default";
		this.vehicleModel = "default";
		this.pricePerDay = 0.0;
		this.engineSize = "default";
		this.details = "default";
		this.disabledAccess = false;
		this.canEdit=false;
	}
	
	public Vehicle(String vehicleReg, String vehicleMake, String vehicleModel, double pricePerDay, String engineSize,
			String details, boolean disabledAccess) {
		this.vehicleReg = vehicleReg;
		this.vehicleMake = vehicleMake;
		this.vehicleModel = vehicleModel;
		this.pricePerDay = pricePerDay;
		this.engineSize = engineSize;
		this.details = details;
		this.disabledAccess = disabledAccess;
	}
	
	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public String getVehicleReg() {
		return vehicleReg;
	}

	public void setVehicleReg(String vehicleReg) {
		this.vehicleReg = vehicleReg;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public double getPricePerDay() {
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
	
	
	
	
}
