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
public class DriverServiceClient {

	// @Value("${cabservice.drive.location}")
	private String driverLocationUpdateURL = "http://localhost:8080/api/driverLocation";
	private String driverStatusUpdateURL = "http://localhost:8080/api/driverStatus";

	@Autowired
	RestTemplate restTemplate;

	public String updateDriverLocation(String driverName, String location) {

		ResponseEntity<String> response = null;
		try {
			log.debug("driver status update is called");
			HttpHeaders headers = new HttpHeaders();
			DriverLocationRequestDTO request = new DriverLocationRequestDTO(driverName, location);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<DriverLocationRequestDTO> reqEntity = new HttpEntity<>(request, headers);
			response = restTemplate.exchange(driverLocationUpdateURL, HttpMethod.PUT, reqEntity, String.class);
			log.info("completed the driver Location update with response {}", response);
		} catch (Exception e) {
			log.error("Error obtained during driver location update :: service url {} - exception message is {}",
					driverLocationUpdateURL, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		return response != null ? response.getBody() : null;
	}

	public String updateDriverStatus(String driverName, Boolean avaliable) {

		ResponseEntity<String> response = null;
		// driverStatusUpdateURL=driverStatusUpdateURL+"?driverName="+driverName+"&avaliable="+avaliable;
		try {
			log.debug("driver location update is called");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<DriverLocationRequestDTO> reqEntity = new HttpEntity<>(null, headers);
			response = restTemplate.exchange(
					driverStatusUpdateURL + "?driverName=" + driverName + "&avaliable=" + avaliable, HttpMethod.PUT,
					reqEntity, String.class);
			log.info("completed the driver status with response {}", response);

		} catch (Exception e) {
			log.error("Error obtained during driver status update :: service url {} - exception message is {}",
					driverStatusUpdateURL + "?driverName=" + driverName + "&avaliable=" + avaliable, e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		return response != null ? response.getBody() : null;
	}
}
