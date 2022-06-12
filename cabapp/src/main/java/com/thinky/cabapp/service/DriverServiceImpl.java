package com.thinky.cabapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinky.cabapp.model.Driver;
import com.thinky.cabapp.model.Earning;
import com.thinky.cabapp.repository.DriverRepo;
import com.thinky.cabapp.repository.EarningRepo;
import com.thinky.cabapp.restclient.DriverServiceClient;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepo driverRepo;

	@Autowired
	DriverServiceClient driverClient;

	@Autowired
	EarningRepo earningRepo;

	@Override
	public Driver saveDriver(Driver driverDetails, String location) {
		// TODO Auto-generated method stub

		driverRepo.save(driverDetails);
		driverClient.updateDriverLocation(driverDetails.getDriverName(), location);
		earningRepo.save(new Earning(driverDetails.getDriverName(), 0.00));
		return driverDetails;
	}

	@Override
	public String updateDriverLocation(String driverName, Double xAxis, Double yAxis) {
		// TODO Auto-generated method stub
		driverRepo.updateDriverLocation(driverName, xAxis, yAxis);
		return "Location updated Sucessfully";
	}

	@Override
	public String updateDriverStatus(String driverName, Boolean avaliable) {
		// TODO Auto-generated method stub
		driverRepo.updateDriverStatus(driverName, avaliable);
		return "status updated Sucessfully";
	}

}
