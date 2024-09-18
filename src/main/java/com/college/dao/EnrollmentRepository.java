package com.college.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.college.entities.Enrollment;
import com.college.entities.User;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

	@Query("select e.student.id from Enrollment e where e.student.id= :id")
	public List<Integer> getStudentIdByCourseId(@Param("id") int id);
}


