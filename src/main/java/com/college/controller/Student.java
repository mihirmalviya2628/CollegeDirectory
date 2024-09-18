package com.college.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.college.dao.StudentRepository;
import com.college.dao.UserRepository;
import com.college.entities.StudentProfile;
import com.college.entities.User;



@Controller
@RequestMapping("/student")
public class Student {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
//	to add common data
	@ModelAttribute
	public void addCommanData( Principal principal, Model model) {
		User user= userRepository.getUserByEmail(principal.getName());
		model.addAttribute("user", user);
	}
	@GetMapping("/home")
	public String home() {
		return "/student/userHome";
	}
	
	
	@GetMapping("/FacultyAdvisor")
	public String Advisor() {
		return "/student/advisors";
	}
	
	@GetMapping("/MyProfile")
	public String profile(Principal principal,Model model) {
		
		User user =userRepository.getUserByEmail(principal.getName());
		StudentProfile student = studentRepository.getStudentById(user.getId());
		model.addAttribute("student", student);
		model.addAttribute("user", user);
		return "/student/MyProfile";
	}
	
}
