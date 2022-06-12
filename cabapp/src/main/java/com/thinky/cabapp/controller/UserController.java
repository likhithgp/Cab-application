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

import com.thinky.cabapp.model.User;
import com.thinky.cabapp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/adduser")
	public ResponseEntity<?> addUser(@RequestBody User userDetails) {

		return new ResponseEntity<>(userService.saveUser(userDetails), HttpStatus.CREATED);

	}

	@PutMapping("/userlocation")
	public ResponseEntity<?> updateUserLocation(@RequestParam(required = true) String userName,
			@RequestParam(required = true) String location) {

		String loc = location.trim().substring(1, location.length() - 1);
		String[] cordinate = loc.split(",");
		Double xValue = Double.parseDouble(cordinate[0].trim());
		Double yValue = Double.parseDouble(cordinate[1].trim());
		return new ResponseEntity<>(userService.updateUserLocation(userName, xValue, yValue), HttpStatus.OK);

	}

	@PutMapping("/userdetails")
	public ResponseEntity<?> updateUserDetails(@RequestParam(required = true) String userName,
			@RequestBody(required = true) User user) {

		return new ResponseEntity<>(userService.updateUserDetails(userName, user), HttpStatus.OK);

	}

}
