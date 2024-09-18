package com.college.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_profile_seq")
	@SequenceGenerator(name = "admin_profile_seq", sequenceName = "admin_profile_sequence", allocationSize = 1)
	private int id;
	
	@NotNull
	private String name;

	@Column(unique = true)
	private String username;
	
	@Size(min = 6,message = "Password should be at least 6 character")
	private String password;
	
	private String role;
	
	@Email(regexp =  "[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+", message = "Invalid Email")
	@Column(unique = true)
	@NotNull
	private String email;
	
	private String phone;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")	
	private StudentProfile studentProfile;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")	
	private FacultyProfile facultyProfile;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")	
	private AdministratorProfile administratorProfile;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the studentProfile
	 */
	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	/**
	 * @param studentProfile the studentProfile to set
	 */
	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
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

	/**
	 * @return the administratorProfile
	 */
	public AdministratorProfile getAdministratorProfile() {
		return administratorProfile;
	}

	/**
	 * @param administratorProfile the administratorProfile to set
	 */
	public void setAdministratorProfile(AdministratorProfile administratorProfile) {
		this.administratorProfile = administratorProfile;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", role="
//				+ role + ", email=" + email + ", phone=" + phone + ", studentProfile=" + studentProfile
//				+ ", facultyProfile=" + facultyProfile + ", administratorProfile=" + administratorProfile + "]";
//	}
	
	
}
