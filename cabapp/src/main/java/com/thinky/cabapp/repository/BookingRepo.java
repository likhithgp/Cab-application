package com.thinky.cabapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thinky.cabapp.model.Booking;
import com.thinky.cabapp.model.User;

@Repository
@Transactional
public interface BookingRepo extends CrudRepository<Booking, Long> {

	@Query("SELECT u FROM Booking u WHERE u.userName = ?1")
	public List<User> findbyUserName(String userName);

	@Modifying
	@Query("UPDATE Booking u SET u.cost=?2 WHERE u.userName = ?1")
	int updateCost(String userName, Double cost);


}
