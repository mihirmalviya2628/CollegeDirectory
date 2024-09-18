package com.college.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Enrollment")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


	@ManyToOne(cascade = CascadeType.ALL)	
	@NotNull
    @JoinColumn(name = "student_id")
    private StudentProfile student;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "course_id")
    private Course course;
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
	 * @return the studentProfile
	 */
	public StudentProfile getStudent() {
		return student;
	}
	/**
	 * @param studentProfile the studentProfile to set
	 */
	public void setStudent(StudentProfile student) {
		this.student = student;
	}
	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
//	@Override
//	public String toString() {
//		return "Enrollment [id=" + id + ", student=" + student + ", course=" + course + "]";
//	}
	
}
