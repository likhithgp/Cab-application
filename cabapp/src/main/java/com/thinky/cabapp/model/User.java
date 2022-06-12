package com.thinky.cabapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Data
@Table(name = "userdetails")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true)
	String userName;
	@Column
	String gender;
	@Column
	@JsonInclude(value = Include.NON_NULL)
	String emailId;
	@Column
	@JsonInclude(value = Include.NON_NULL)
	String phoneNumber;
	@Column
	String age;
	@Column
	@JsonInclude(value = Include.NON_NULL)
	Double xCoordinate;
	@Column
	@JsonInclude(value = Include.NON_NULL)
	Double yCoordinate;
}
