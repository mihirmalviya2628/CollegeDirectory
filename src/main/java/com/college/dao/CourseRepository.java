package com.college.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.college.entities.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query("select c from Course c where c.facultyProfile.id= :id")
	public Course getCourseFromFacultyById(@Param("id") int id);

}
