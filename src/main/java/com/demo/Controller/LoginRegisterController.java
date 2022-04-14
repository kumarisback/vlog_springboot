package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.Entity.Profile;
import com.demo.services.ProfileServices;
//import com.demo.Repositry.ProfileRepositry;
import com.demo.services.UserService;

@Controller
public class LoginRegisterController {

	@Autowired
	ProfileServices profileserivces;

	@Autowired
	UserService userService;

//	@PostMapping("/auth")
//	public String handleLogin(@ModelAttribute Login login) {
//		System.out.println(login.toString());
//		Login userLogin = login;
//		UserDetails flag = userService.loadUserByUsername(userLogin.getUser());
//		System.out.println(flag.toString());
//		return "redirect:/users";
//	}

	@GetMapping("/login")
	public String greetingForm() {

		return "LoginRegisteration/login";
	}

//	@GetMapping("/error")
//	public String error() {
//		return "error";
//	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("register", new Profile());
		return "LoginRegisteration/registeration";
	}

	@PostMapping("/register")
	public String handleRegister(@ModelAttribute Profile profile, Model model) {
		model.addAttribute("register", profile);
		Profile p = new Profile();
		p = profile;
//		p.setPassword(new BCryptPasswordEncoder(10).encode(p.getPassword()));
		if (p.getRole() == null)
			p.setRole("NORMAL");
//	    System.out.println(p.toString()); 
		profileserivces.saveOrUpdate(p);
		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}

}
