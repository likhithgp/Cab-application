package com.thinky.cabapp.model;

import lombok.Data;

@Data
public class DriverLocationRequestDTO {

	String driverName;
	String location;
	public DriverLocationRequestDTO(String driverName, String location) {
		super();
		this.driverName = driverName;
		this.location = location;
	}
	
}
