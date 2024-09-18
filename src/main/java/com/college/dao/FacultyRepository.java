package com.college.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.college.entities.FacultyProfile;


public interface FacultyRepository extends JpaRepository<FacultyProfile, Integer> {

	@Query("select f from FacultyProfile f where f.id= :id")
	public FacultyProfile getFacultyById(@Param("id") int id);

}
