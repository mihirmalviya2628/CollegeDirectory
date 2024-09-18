package com.college.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Course")
public class Course {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String description;
	
	@ManyToOne
	@NotNull
	private Department department;
	
	@ManyToOne	
	@NotNull
	private FacultyProfile facultyProfile;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
	private List<Enrollment> enrollments;
	
	
	/**
	 * @return the enrollments
	 */
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	/**
	 * @param enrollments the enrollments to set
	 */
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * @return the facultyProfile
	 */
	public FacultyProfile getFacultyProfile() {
		return facultyProfile;
	}
	/**
	 * @param facultyProfile the facultyProfile to set
	 */
	public void setFacultyProfile(FacultyProfile facultyProfile) {
		this.facultyProfile = facultyProfile;
	}
//	@Override
//	public String toString() {
//		return "Course [id=" + id + ", title=" + title + ", description=" + description + ", department=" + department
//				+ ", facultyProfile=" + facultyProfile + ", enrollments=" + enrollments + "]";
//	}
	
}
