package com.thinky.cabapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thinky.cabapp.model.User;

@Repository
@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

	@Modifying
	@Query("UPDATE User u SET u.xCoordinate = ?2, u.yCoordinate=?3 WHERE u.userName = ?1")
	int updateUserLocation(String userName, Double xAxis, Double yAxis);

	@Query("SELECT u FROM User u WHERE u.userName = ?1")
	public List<User> findbyUserName(String userName);

}
