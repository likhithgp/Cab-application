package com.thinky.cabapp.service;

import java.util.List;

import com.thinky.cabapp.model.Earning;

public interface BookingService {

	public List<String> findRide(String pickPoint, String Destination);

	public String chooseRide(String driverName, String userName);

	public String calculateBill(String userName);

	public List<Earning> totalDriverEarning();

}
