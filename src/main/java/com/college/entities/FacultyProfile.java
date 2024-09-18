package com.college.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="FacultyProfile")

public class FacultyProfile {

	private String photoUrl;
	private int office_hours;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_profile_seq")
	@SequenceGenerator(name = "admin_profile_seq", sequenceName = "admin_profile_sequence", allocationSize = 0)
    
	private int id;

	
	@OneToOne(cascade = CascadeType.ALL)	
	 @MapsId // This tells Hibernate to use the same id as the User entity
	 @JoinColumn(name = "id") // This ensures the foreign key column is used as the ID
	private User user;
	
	@ManyToOne
	@NotNull
	private Department department;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "facultyProfile")
	private List<Course> courses;
	
	

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
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	/**
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
	 * @return the office_hours
	 */
	public int getOffice_hours() {
		return office_hours;
	}

	/**
	 * @param office_hours the office_hours to set
	 */
	public void setOffice_hours(int office_hours) {
		this.office_hours = office_hours;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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

//	@Override
//	public String toString() {
//		return "FacultyProfile [photoUrl=" + photoUrl + ", office_hours=" + office_hours + ", id=" + id + ", user="
//				+ user + ", department=" + department + ", courses=" + courses + "]";
//	}
	
	
}
