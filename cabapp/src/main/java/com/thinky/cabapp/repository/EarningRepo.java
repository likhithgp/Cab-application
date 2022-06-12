package com.thinky.cabapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thinky.cabapp.model.Earning;

@Repository
@Transactional
public interface EarningRepo extends CrudRepository<Earning, Long> {

	@Modifying
	@Query("UPDATE Earning e SET e.cost=?2 WHERE e.driverName = ?1")
	int updateCost(String driverName, Double cost);

	@Query("SELECT e FROM Earning e WHERE e.driverName = ?1")
	List<Earning> findByDriverName(String driverName);
}
