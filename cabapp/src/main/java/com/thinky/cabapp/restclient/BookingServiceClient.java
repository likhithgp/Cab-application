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
public class BookingServiceClient {

	private String calculateBillURL = "http://localhost:8080/api/bill";

	@Autowired
	RestTemplate restTemplate;

	public Double calculateBill(String userName) {

		ResponseEntity<Double> response = null;
		calculateBillURL = calculateBillURL + "?userName" + userName;
		try {
			log.debug("Calculate Bill Rest API is called");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<DriverLocationRequestDTO> reqEntity = new HttpEntity<>(null, headers);
			response = restTemplate.exchange(calculateBillURL, HttpMethod.PUT, reqEntity, Double.class);
			log.info("completed the calcualte bill with response {}", response);
		} catch (Exception e) {
			log.error("Error obtained calculating  :: service url {} - exception message is {}", calculateBillURL,
					e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		return response != null ? response.getBody() : null;
	}
}
