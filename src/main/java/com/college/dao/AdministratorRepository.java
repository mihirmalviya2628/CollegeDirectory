package com.college.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.college.entities.AdministratorProfile;
import com.college.entities.User;


public interface AdministratorRepository extends JpaRepository<AdministratorProfile, Integer> {

	@Query("select u from User u where u.email= :email")
	public User getUserByEmail(@Param("email") String email);

    
}


