package com.thinky.cabapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thinky.cabapp.model.Driver;

@Repository
@Transactional
public interface DriverRepo extends CrudRepository<Driver, Long> {

	@Modifying
	@Query("UPDATE Driver  SET xCoordinate = ?2, yCoordinate=?3 WHERE driverName = ?1")
	int updateDriverLocation(String driverName, Double xAxis, Double yAxis);

	List<Driver> findByDriverName(String driverName);

	@Modifying
	@Query("UPDATE Driver  SET avaliable=?2  WHERE driverName = ?1")
	int updateDriverStatus(String driverName, Boolean avaliable);

	@Query("select d from Driver d  WHERE d.avaliable = true")
	List<Driver> listOfAvaliableNearyByDriver();
}
