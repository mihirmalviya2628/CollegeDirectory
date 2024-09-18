package com.college.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.college.dao.CourseRepository;
import com.college.dao.EnrollmentRepository;
import com.college.dao.FacultyRepository;
import com.college.dao.StudentRepository;
import com.college.dao.UserRepository;
import com.college.entities.Course;
import com.college.entities.FacultyProfile;
import com.college.entities.User;
import com.college.helper.Message;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/faculty")
public class Faculty {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
//	to add common data
	@ModelAttribute
	public void addCommanData( Principal principal, Model model) {
		User user= userRepository.getUserByEmail(principal.getName());
		model.addAttribute("user", user);
	}
	@GetMapping("/home")
	public String home() {
		return "/faculty/userHome";
	}
	
	
	@GetMapping("/studentList")
	public String List(Model model,Principal principal) {
		
		User user =userRepository.getUserByEmail(principal.getName());
		FacultyProfile faculty=facultyRepository.getFacultyById(user.getId());
		List<Course> courses =faculty.getCourses();
		HashMap<String, List<User>> users=new HashMap<>();
		for(int i=0;i<courses.size();i++){
			
			List<Integer> sList=enrollmentRepository.getStudentIdByCourseId(courses.get(i).getId());
			
			List<User> uList=new ArrayList<>();
			
			for(int j=0;j<sList.size();j++) {
		    uList.add(userRepository.getUserById(sList.get(j)));
			}
			
			users.put(courses.get(i).getTitle(),uList); 
			
		}
		model.addAttribute("users", users);
		return "/faculty/studentList";
	}
	
	
	@GetMapping("/MyProfile")
	public String profile(Principal principal,Model model) {
		
		User user =userRepository.getUserByEmail(principal.getName());
		FacultyProfile faculty=facultyRepository.getFacultyById(user.getId());
		model.addAttribute("faculty", faculty);
		model.addAttribute("user", user);
		return "/faculty/MyProfile";
	}
	
	@PostMapping("/update/{id}")
	public String  UpdateProfile(@PathVariable("id") Integer id,Model model) {
		
		User user=userRepository.getUserById(id);
		FacultyProfile faculty =user.getFacultyProfile();
		model.addAttribute("user", user);
		model.addAttribute("faculty", faculty);
		return "/faculty/updateProfile";
		
	}
	@PostMapping("/update_process")
	public String updateProcess(@ModelAttribute User user,Model model,@RequestParam int office_hours) {
		//TODO: process POST request
		try {
			userRepository.save(user);

			FacultyProfile faculty =user.getFacultyProfile();
			faculty.setOffice_hours(office_hours);
			facultyRepository.save(faculty);
			
			model.addAttribute("faculty", faculty);
			model.addAttribute("user", user);
			model.addAttribute("message", new Message("alert-sucess", "Updated!!"));
			
			return "/faculty/MyProfile";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", new Message("alert-danger", "Failed!!"));
			
			return "/faculty/updateProfile";
		}
	}
	
}
