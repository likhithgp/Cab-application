package com.thinky.cabapp.service;

import com.thinky.cabapp.model.User;

public interface UserService {

	public User saveUser(User userDetails);

	public String updateUserLocation(String userName, Double xAxis, Double yAxis);

	public String updateUserDetails(String userName, User userUpadteObj);

}
