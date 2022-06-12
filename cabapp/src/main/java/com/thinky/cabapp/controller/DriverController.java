package com.thinky.cabapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinky.cabapp.model.Driver;
import com.thinky.cabapp.model.DriverLocationRequestDTO;
import com.thinky.cabapp.service.DriverService;

@RestController

@RequestMapping("/api")
public class DriverController {

	private DriverService driverService;

	@Autowired
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}

	@PostMapping("/addDriver")
	public ResponseEntity<?> addDriver(@RequestBody Driver driverDetails, @RequestParam String location) {

		return new ResponseEntity<>(driverService.saveDriver(driverDetails, location), HttpStatus.CREATED);

	}

	@PutMapping("/driverLocation")
	public ResponseEntity<?> updateUserLocation(@RequestBody DriverLocationRequestDTO driverLocationRequestDTO) {

		String loc = driverLocationRequestDTO.getLocation().trim().substring(1,
				driverLocationRequestDTO.getLocation().length() - 1);

		String[] cordinate = loc.split(",");
		Double xValue = Double.parseDouble(cordinate[0].trim());
		Double yValue = Double.parseDouble(cordinate[1].trim());
		return new ResponseEntity<>(
				driverService.updateDriverLocation(driverLocationRequestDTO.getDriverName(), xValue, yValue),
				HttpStatus.OK);

	}

	@PutMapping("/driverStatus")
	public ResponseEntity<?> updateDriverStatus(@RequestParam(required = true) String driverName,

			@RequestParam(required = true) Boolean avaliable) {

		return new ResponseEntity<>(driverService.updateDriverStatus(driverName, avaliable), HttpStatus.OK);

	}

}
