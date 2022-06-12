package com.thinky.cabapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinky.cabapp.model.User;
import com.thinky.cabapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User saveUser(User userDetails) {

		userRepo.save(userDetails);
		return userDetails;
	}

	@Override
	public String updateUserLocation(String userName, Double xAxis, Double yAxis) {

		userRepo.updateUserLocation(userName, xAxis, yAxis);
		return "user location updated sucessfully";
	}

	@Override
	public String updateUserDetails(String userName, User userUpdateObj) {
		// TODO Auto-generated method stub
		User user = userRepo.findbyUserName(userName).get(0);// userRepo.findbyUserName(userName).get(0);
		user.setAge(userUpdateObj.getAge());
		if (userUpdateObj.getEmailId() != null) {
			user.setEmailId(userUpdateObj.getEmailId());
		}
		user.setGender(userUpdateObj.getGender());
		if (userUpdateObj.getPhoneNumber() != null) {
			user.setPhoneNumber(userUpdateObj.getPhoneNumber());
		}
		user.setUserName(user.getUserName());
		if (userUpdateObj.getXCoordinate() != null) {
			user.setXCoordinate(userUpdateObj.getXCoordinate());
		}
		if (userUpdateObj.getYCoordinate() != null) {
			user.setYCoordinate(userUpdateObj.getYCoordinate());
		}
		userRepo.save(user);
		return "user Details updated sucessfully";
	}
}
