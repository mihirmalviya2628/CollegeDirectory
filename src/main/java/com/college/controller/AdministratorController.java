package com.college.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.college.dao.AdministratorRepository;
import com.college.dao.DepartmentRepository;
import com.college.dao.FacultyRepository;
import com.college.dao.StudentRepository;
import com.college.dao.UserRepository;
import com.college.entities.AdministratorProfile;
import com.college.entities.Department;
import com.college.entities.FacultyProfile;
import com.college.entities.StudentProfile;
import com.college.entities.User;
import com.college.helper.Message;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/admin")
public class AdministratorController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@Autowired
	AdministratorRepository administratorRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@ModelAttribute
	public void addCommanData( Principal principal, Model model) {
		User loginUser= userRepository.getUserByEmail(principal.getName());
		model.addAttribute("loginUser", loginUser);
	}

	@GetMapping("/home")
	public String home() {
		return "/administrator/userHome";
	}
	
	@GetMapping("/studentList")
	public String showStudents(Principal principal,Model model) {
//		System.out.println(principal.getName());
		User user =userRepository.getUserByEmail(principal.getName());
		AdministratorProfile admin=user.getAdministratorProfile();
		List<User> students = userRepository.getByDepartmentId(admin.getDepartment().getId());
//		System.out.println(students);
		model.addAttribute("students", students); 
		return "/administrator/studentList";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteContact(@PathVariable("id") Integer id,Model model,Principal principal) {
		

//	User user =userRepository.getUserById(id);
	studentRepository.deleteById(id);
	
//	userRepository.delete(user);
	
	User user2 =userRepository.getUserByEmail(principal.getName());
	
	AdministratorProfile admin=user2.getAdministratorProfile();
	List<User> students = userRepository.getByDepartmentId(admin.getDepartment().getId());
//	System.out.println(students);
	model.addAttribute("students", students); 
	return "/administrator/studentList";
	
	}
	
	
	
	
	@GetMapping("/facultyList")
	public String showFaculty(Principal principal,Model model) {
		
		User user =userRepository.getUserByEmail(principal.getName());
		AdministratorProfile admin=user.getAdministratorProfile();
		List<User> faculties = userRepository.getByFacultyDepartmentId(admin.getDepartment().getId());
		System.out.println(faculties);
		model.addAttribute("faculties", faculties); 
		return "/administrator/facultyList";
	}
	
	@GetMapping("/deleteFaculty/{id}")
	public String deleteFaculty(@PathVariable("id") Integer id,Model model,Principal principal) {
		
	Optional<FacultyProfile> facultyOptional=this.facultyRepository.findById(id);
	FacultyProfile faculty=facultyOptional.get();
	facultyRepository.delete(faculty);
	
//	User user =userRepository.getUserById(faculty.getId());
//	userRepository.delete(user);
	
	User user2 =userRepository.getUserByEmail(principal.getName());
	AdministratorProfile admin=user2.getAdministratorProfile();
	List<User> faculties = userRepository.getByFacultyDepartmentId(admin.getDepartment().getId());
	System.out.println(faculties);
	model.addAttribute("faculties", faculties); 
	return "/administrator/facultyList";
	
	}
	
	@GetMapping("/addStudent")
	public String addStudent(Model model) {
		model.addAttribute("user", new User());
		return "/administrator/AddStudent";
	}
	
	@PostMapping("/add_student")
	public String processAddStudent(@ModelAttribute User user,@RequestParam String year,@RequestParam int departmentId,Model model,Principal principal) {
		//TODO: process POST request
		
		try {
			
			System.out.print("???"+user.getId());
			user.setRole("ROLE_STUDENT");
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User user2= userRepository.save(user);
			StudentProfile student = new StudentProfile();
			Optional<Department> departmentopOptional=departmentRepository.findById(departmentId);
			Department department=departmentopOptional.get();
			
			student.setYear(year);
			student.setDepartment(department);
			student.setUser(user2);
			student.setId(user2.getId());
			
			user2.setStudentProfile(student);
			userRepository.save(user2);


			model.addAttribute("user", new User());
            model.addAttribute("message", new Message("alert-success", "Added!!"));
			return "/administrator/AddStudent";
			
		} catch (Exception e) {
			// TODO: handle exception
            e.printStackTrace();
			model.addAttribute("user", user);
            model.addAttribute("message", new Message("alert-danger", "Failed!!"));
			return "/administrator/AddStudent";
			
		}
	}
	
	
	@GetMapping("/addFaculty")
	public String addFaculty(Model model) {
		model.addAttribute("user", new User());
		return "/administrator/AddFaculty";
	}
	
	@PostMapping("/add_faculty")
	public String processAddFaculty(@ModelAttribute User user,@RequestParam int office_hours,@RequestParam int departmentId,Model model,Principal principal) {
		//TODO: process POST request
		
		try {
			
			
			user.setRole("ROLE_FACULTY");
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User user2= userRepository.save(user);
			System.out.print("???"+user2);

			FacultyProfile faculty = new FacultyProfile();
			Optional<Department> departmentopOptional=departmentRepository.findById(departmentId);
			Department department=departmentopOptional.get();
			
			faculty.setDepartment(department);
			faculty.setId(user2.getId());
			faculty.setOffice_hours(office_hours);
			faculty.setUser(user2);
			
			
			
			user2.setFacultyProfile(faculty);
			userRepository.save(user2);


			model.addAttribute("user", new User());
            model.addAttribute("message", new Message("alert-success", "Added!!"));
			return "/administrator/AddStudent";
			
		} catch (Exception e) {
			// TODO: handle exception
            e.printStackTrace();
			model.addAttribute("user", user);
            model.addAttribute("message", new Message("alert-danger", "Failed!!"));
			return "/administrator/AddFaculty";
			
		}
	}
	
	
	@PostMapping("/updateStudent/{id}")
	public String UpdateS(@PathVariable int id,Model model) {
		Optional<User> useropOptional=userRepository.findById(id);
		User user=useropOptional.get();
		StudentProfile student=studentRepository.getStudentById(id);
		model.addAttribute("student",student);
		model.addAttribute("user",user);
		return "/administrator/updateStudent";
	}
	
	@PostMapping("/updateS_process")
	public String updateS_process(@ModelAttribute User user,@RequestParam int departmentId,@RequestParam String year,Model model,Principal principal) {
		//TODO: process POST request
		try {
			StudentProfile student= studentRepository.getStudentById(user.getId());
			student.setYear(year);
			student.setUser(user);
			student.setDepartment(departmentRepository.findById(departmentId).get());
			user.setStudentProfile(student);
			userRepository.save(user);
			model.addAttribute("message", new Message("alert-success", "Updated!!"));
			User loggedUser = userRepository.getUserByEmail(principal.getName());
			AdministratorProfile admin=loggedUser.getAdministratorProfile();
			List<User> students = userRepository.getByDepartmentId(admin.getDepartment().getId());
//			System.out.println(students);
			model.addAttribute("students", students); 
			return "/administrator/studentList";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", new Message("alert-danger", "Failed!!"));
			User loggedUser = userRepository.getUserByEmail(principal.getName());
			AdministratorProfile admin=loggedUser.getAdministratorProfile();
			List<User> students = userRepository.getByDepartmentId(admin.getDepartment().getId());
//			System.out.println(students);
			model.addAttribute("students", students); 
			return "/administrator/studentList";
		}
		
		
	}
	
	@PostMapping("/updateFaculty/{id}")
	public String UpdateF(@PathVariable int id,Model model) {
		Optional<User> useropOptional=userRepository.findById(id);
		User user=useropOptional.get();
		FacultyProfile faculty=facultyRepository.getFacultyById(id);
		model.addAttribute("faculty",faculty);
		model.addAttribute("user",user);
		return "/administrator/updateFaculty";
	}
	
	@PostMapping("/updateF_process")
	public String updateF_process(@ModelAttribute User user,@RequestParam int departmentId,@RequestParam int office_hours,Model model) {
		//TODO: process POST request
		try {
			FacultyProfile faculty=facultyRepository.getFacultyById(user.getId());
			faculty.setOffice_hours(office_hours);
			faculty.setUser(user);
			faculty.setDepartment(departmentRepository.findById(departmentId).get());
			user.setFacultyProfile(faculty);
			userRepository.save(user);
			model.addAttribute("message", new Message("alert-success", "Updated!!"));
			model.addAttribute("faculty",faculty);
			model.addAttribute("user",user);
			return "/administrator/facultyList";
		} catch (Exception e) {
			// TODO: handle exception
			FacultyProfile faculty=facultyRepository.getFacultyById(user.getId());
			model.addAttribute("faculty", faculty);
			
			model.addAttribute("message", new Message("alert-danger", "Failed!!"));
			return "/administrator/updateFaculty";
		}
	}
	
}
