package com.college.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.college.entities.StudentProfile;


public interface StudentRepository extends JpaRepository<StudentProfile, Integer> {

	@Query("select s from StudentProfile s where s.id= :id")
	public StudentProfile getStudentById(@Param("id") int id);
	
	@Query("select s from StudentProfile s where s.department.id= :id")
	public List<StudentProfile> getStudentByDepartmentId(@Param("id") int id);
}
