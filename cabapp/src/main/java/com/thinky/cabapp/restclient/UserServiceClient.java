package com.thinky.cabapp.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.thinky.cabapp.model.DriverLocationRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceClient {

	private String userLocationUpdateURL = "http://localhost:8080/api/userlocation";

	@Autowired
	RestTemplate restTemplate;

	public String updateUserLocation(String userName, String location) {

		ResponseEntity<String> response = null;
		// userLocationUpdateURL=userLocationUpdateURL+"?userName="+userName+"&location="+location;
		try {
			log.debug("user location update is called");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<DriverLocationRequestDTO> reqEntity = new HttpEntity<>(null, headers);
			response = restTemplate.exchange(userLocationUpdateURL + "?userName=" + userName + "&location=" + location,
					HttpMethod.PUT, reqEntity, String.class);
			log.info("completed the user location update with response {}", response);
		} catch (Exception e) {
			log.error("Error obtained during user location update :: service url {} - exception message is {}",
					userLocationUpdateURL, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		return response != null ? response.getBody() : null;
	}
}
