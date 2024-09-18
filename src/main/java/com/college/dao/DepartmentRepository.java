package com.college.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entities.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
