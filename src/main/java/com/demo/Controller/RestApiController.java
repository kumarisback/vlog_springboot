package com.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.Entity.Profile;
import com.demo.services.ProfileServices;
//import com.demo.Repositry.ProfileRepositry;
import com.demo.services.UserService;

@Controller
public class RestApiController {

	@Autowired
	ProfileServices profileserivces;

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public String greetUser(Model model) {
		List<Profile> list = new ArrayList<>();
		list.addAll(profileserivces.getAllProfile());
		model.addAttribute("profiles", list);
		return "users";
	}

	@GetMapping("/users/{id}")
	private String getProfiles(@PathVariable("id") Long id, Model model) {
		Profile profile = profileserivces.getProfilesById(id);
		model.addAttribute("register", profile);
		return "registeration";
	}

	@DeleteMapping("/users/{id}")
	private String deleteProfiles(@PathVariable("id") Long id) {
		System.out.println(id);
		profileserivces.delete(id);

		return "redirect:/users";
	}

	@PostMapping("/welcome")
	public String greetingSubmit() {
		return "welcome";
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
}
