package com.college.entities;

import java.util.List;

import jakarta.annotation.Generated;
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
@Table(name = "StudentProfile")

public class StudentProfile {

	private String photoUrl;
	private String year;

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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
	private List<Enrollment> enrollments;

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
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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
//		return "StudentProfile [photoUrl=" + photoUrl + ", year=" + year + ", id=" + id + ", user=" + user
//				+ ", department=" + department + ", enrollments=" + enrollments + "]";
//	}

}
