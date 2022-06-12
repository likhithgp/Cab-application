package com.thinky.cabapp.service;

import com.thinky.cabapp.model.Driver;

public interface DriverService {

	public Driver saveDriver(Driver driverDetails, String location);

	public String updateDriverLocation(String driverName, Double xAxis, Double yAxis);

	public String updateDriverStatus(String driverName, Boolean avaliable);

}
