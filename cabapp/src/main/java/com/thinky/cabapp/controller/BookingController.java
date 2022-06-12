package com.thinky.cabapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinky.cabapp.service.BookingService;

@RestController

@RequestMapping("/api")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@GetMapping("/findraid")
	public ResponseEntity<?> findRide(@RequestParam(required = true) String userName,

			@RequestParam(required = true) String pickPoint, @RequestParam(required = true) String destination) {

		List<String> driverList = bookingService.findRide(pickPoint, destination);
		if (driverList != null && driverList.size() > 0) {
			return new ResponseEntity<>(driverList, HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>( "No raider found", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/raid")
	public ResponseEntity<?> chooseRide(@RequestParam(required = true) String userName,

			@RequestParam(required = true) String driverName) {

		return new ResponseEntity<>(bookingService.chooseRide(driverName, userName), HttpStatus.CREATED);

	}

	@PutMapping("/bill")
	public ResponseEntity<?> calulateBill(@RequestParam(required = true) String userName) {

		return new ResponseEntity<>(bookingService.calculateBill(userName), HttpStatus.OK);

	}

	@GetMapping("/earning")
	public ResponseEntity<?> driverEarning() {

		return new ResponseEntity<>(bookingService.totalDriverEarning(), HttpStatus.FOUND);

	}
}
