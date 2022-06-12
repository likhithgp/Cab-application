package com.thinky.cabapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinky.cabapp.model.Booking;
import com.thinky.cabapp.model.Driver;
import com.thinky.cabapp.model.Earning;
import com.thinky.cabapp.repository.BookingRepo;
import com.thinky.cabapp.repository.DriverRepo;
import com.thinky.cabapp.repository.EarningRepo;
import com.thinky.cabapp.repository.UserRepo;
import com.thinky.cabapp.restclient.BookingServiceClient;
import com.thinky.cabapp.restclient.DriverServiceClient;
import com.thinky.cabapp.restclient.UserServiceClient;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	DriverRepo driverRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	BookingRepo bookingRepo;

	@Autowired
	BookingServiceClient bookingServiceClient;

	@Autowired
	DriverServiceClient driverServiceClient;

	@Autowired
	UserServiceClient userServiceClient;

	@Autowired
	EarningRepo earningRepo;

	/*
	 * @Autowired public BookingServiceImpl(DriverRepo driverRepo, UserRepo
	 * userRepo, BookingRepo bookingRepo, BookingServiceClient bookingServiceClient,
	 * DriverServiceClient driverServiceClient, UserServiceClient userServiceClient,
	 * EarningRepo earningRepo) { super(); this.driverRepo = driverRepo;
	 * this.userRepo = userRepo; this.bookingRepo = bookingRepo;
	 * this.bookingServiceClient = bookingServiceClient; this.driverServiceClient =
	 * driverServiceClient; this.userServiceClient = userServiceClient;
	 * this.earningRepo = earningRepo; }
	 */
	String pickPoint = null;
	String destination = null;
	String driverName = null;

	@Override
	public List<String> findRide(String pickPoint, String destination) {
		// TODO Auto-generated method stub
		this.pickPoint = pickPoint;
		this.destination = destination;

		List<Double> coordinate = coOrdinate(pickPoint);
		List<Driver> listOfAvaliable = driverRepo.listOfAvaliableNearyByDriver();
		// and power(?1 - xCoordinate) + power(?2 - yCoordinate) <= ?3

		List<Driver> listOfNearByDriverObj = listOfAvaliable.stream()
				.filter(driver -> (Math.pow(coordinate.get(0) - driver.getXCoordinate(), 2)
						+ Math.pow(coordinate.get(1) - driver.getYCoordinate(), 2)) <= Math.pow(5.00, 2))
				.collect(Collectors.toList());

		List<String> listOfNearBy = new ArrayList<String>();
		listOfNearByDriverObj.forEach(driver -> {
			listOfNearBy.add(driver.getDriverName());
		});

		return listOfNearBy;
	}

	@Override
	public String chooseRide(String driverName, String userName) {
		// TODO Auto-generated method stub

		List<Driver> driverObj = driverRepo.findByDriverName(driverName);
		if (driverObj != null && driverObj.size()>0) {
			this.driverName = driverName;

			Booking booking = new Booking();
			booking.setDestination(destination);
			booking.setPickPoint(pickPoint);
			booking.setUserName(userName);
			booking.setDriverName(driverName);

			bookingRepo.save(booking);
			driverServiceClient.updateDriverStatus(driverName, false);
			return "Started Raid";
		} else {
			return "Did not selected Valid raider";
		}
	}

	@Override
	public String calculateBill(String userName) {
		// TODO Auto-generated method stub
		List<Double> initalPoint = coOrdinate(pickPoint);
		List<Double> destinationPoint = coOrdinate(destination);

		Double cost = Math.sqrt(Math.pow(initalPoint.get(0) - destinationPoint.get(0), 2)
				+ (Math.pow(initalPoint.get(1) - destinationPoint.get(1), 2)));
		cost = Math.round(cost * 100.0) / 100.0;
		bookingRepo.updateCost(userName, cost);

		driverServiceClient.updateDriverLocation(driverName, destination);
		userServiceClient.updateUserLocation(userName, destination);
		Earning earn = earningRepo.findByDriverName(driverName).get(0);
		earn.setCost(earn.getCost() + cost);
		earningRepo.save(earn);
		driverServiceClient.updateDriverStatus(driverName, true);
		return "ride Ended bill amount $" + cost;
	}

	@Override
	public List<Earning> totalDriverEarning() {
		// TODO Auto-generated method stub

		return (List<Earning>) earningRepo.findAll();
	}

	private List<Double> coOrdinate(String point) {

		String location = point.trim().substring(1, point.length() - 1);
		String[] cordinate = location.split(",");
		List<Double> list = new ArrayList<Double>();
		list.add(Double.parseDouble(cordinate[0].trim()));
		list.add(Double.parseDouble(cordinate[1].trim()));

		return list;
	}

}
