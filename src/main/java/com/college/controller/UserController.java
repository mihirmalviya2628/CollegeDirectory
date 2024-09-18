package com.college.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.dao.AdministratorRepository;
import com.college.dao.DepartmentRepository;
import com.college.dao.UserRepository;
import com.college.entities.AdministratorProfile;
import com.college.entities.Department;
import com.college.entities.User;
import com.college.helper.Message;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdministratorRepository administratorRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/home")
	public String Home() {
		return "home";
	}
	@GetMapping("/signin")
	public String login(Model model) {
		return "signin";
	}
	
	@GetMapping("/signup")
	public String register(Model model) {
		model.addAttribute("user", new User()); 
		return "signup";
	}

	@PostMapping("/do_register")
	public String register(@Valid @ModelAttribute User user,BindingResult result,Model model ,@RequestParam int departmentId ) {

		try {
			
			if(result.hasErrors()){
				System.out.println("ERROR" + result.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			user.setRole("ROLE_ADMIN");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User u=this.userRepository.save(user);
			
			
			AdministratorProfile admin =new AdministratorProfile();
			admin.setDepartment(departmentRepository.findById(1).get());
			admin.setId(u.getId());
			admin.setUser(u);
			System.out.println("1/"+u.getId());
			Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
			System.out.println("2/"+u.getId());
			admin.setDepartment(departmentOptional.get());
			System.out.println("3/"+admin);
			
			AdministratorProfile saveAdmini = administratorRepository.save(admin);
			System.out.println("4/"+admin);
			u.setAdministratorProfile(saveAdmini);
			User u1=userRepository.save(u);
			System.out.println("5/"+u.getId());
			
			model.addAttribute("user", u1);
			System.out.println(u);
			model.addAttribute("message", new Message("alert-success", "Registration Successfull !!!!"));
			return "redirect:/admin/home";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("user", user);
			model.addAttribute("message", new Message("alert-danger", "Registration Failed !!!! Maybe email already exists"));
			return "signup";
		}
		
	}
//	@PostMapping("/login") 
//    public	String redirect(@RequestParam String username) {
//		User user =userRepository.getUserByEmail(username);
//		if(user.getRole()=="ROLE_ADMIN") {
//			System.out.print(user.getRole());
//			return "/administrator/userHome";
//		}
//		if(user.getRole()=="ROLE_STUDENT") {
//			return "redirect:/student/home";
//		}
//		if(user.getRole()=="ROLE_FACULTY") {
//			return "redirect:/faculty/home";
//		}
//		return "redirect:/home";
//	}
}
