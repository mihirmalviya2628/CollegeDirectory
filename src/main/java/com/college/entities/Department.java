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
@Table(name = "Department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String name;
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department")
	private List<StudentProfile> students;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department")
	private List<FacultyProfile> faculties;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department")
	private List<Course> courses;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department")
	private List<AdministratorProfile> administrators;
	
	
	/**
	 * @return the administrators
	 */
	public List<AdministratorProfile> getAdministrators() {
		return administrators;
	}
	/**
	 * @param administrators the administrators to set
	 */
	public void setAdministrators(List<AdministratorProfile> administrators) {
		this.administrators = administrators;
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
	 * @return the student
	 */
	public List<StudentProfile> getStudents() {
		return students;
	}
	/**
	 * @return the faculties
	 */
	public List<FacultyProfile> getFaculties() {
		return faculties;
	}
	/**
	 * @param faculties the faculties to set
	 */
	public void setFaculties(List<FacultyProfile> faculties) {
		this.faculties = faculties;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudents(List<StudentProfile> students) {
		this.students = students;
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
//	@Override
//	public String toString() {
//		return "Department [id=" + id + ", name=" + name + ", description=" + description + ", students=" + students
//				+ ", faculties=" + faculties + ", courses=" + courses + ", administrators=" + administrators + "]";
//	}
	
}
