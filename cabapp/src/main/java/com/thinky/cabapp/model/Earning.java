package com.thinky.cabapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name = "earning")
public class Earning {

	public Earning(Long id, String driverName, Double cost) {
		super();
		this.id = id;
		this.driverName = driverName;
		this.cost = cost;
	}

	public Earning() {
		super();
	}

	public Earning(String driverName, Double cost) {
		super();
		this.driverName = driverName;
		this.cost = cost;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column
	String driverName;
	@Column
	@JsonInclude(value = Include.NON_NULL)
	@Value("0.00")
	Double cost;
}
