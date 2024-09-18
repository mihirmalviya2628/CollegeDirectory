package com.college.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.college.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.email= :email")
	public User getUserByEmail(@Param("email") String email);

	@Query("select u from User u where u.id= :id")
	public User getUserById(@Param("id") int id);
	

    @Query("SELECT u FROM User u WHERE u.studentProfile.department.id= :id")
    public List<User> getByDepartmentId(@Param("id") int id);

     
    @Query("SELECT u FROM User u WHERE u.facultyProfile.department.id= :id")
    public List<User> getByFacultyDepartmentId(@Param("id") int id);

}


