package com.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
	// autowire the RegisterService class
//	@Autowired
//	ProfileServices profilesService;
	// creating a get mapping that retrieves all the books detail from the database
//	@GetMapping("/profile")  
//	private List<Profile> getAllProfile()   
//	{  
//	return profilesService.getAllProfile();  
//	}  
	// creating a get mapping that retrieves the detail of a specific book
//	@GetMapping("/profile/{id}")  
//	private Profile getProfiles(@PathVariable("id") int id)   
//	{  
//	return profilesService.getProfilesById(id);  
//	}  
	// creating a delete mapping that deletes a specified book
//	@DeleteMapping("/profile/{id}")  
//	private void deleteBook(@PathVariable("id") int id)   
//	{  
//	profilesService.delete(id);  
//	}  
	// creating post mapping that post the book detail in the database
//	@PostMapping("/profiles")  
//	private int saveBook(@RequestBody Profile profiles)   
//	{  
//	profilesService.saveOrUpdate(profiles);  
//	return profiles.getProfileid();  
//	}  
//	//creating put mapping that updates the book detail   
//	@PutMapping("/profiles")  
//	private Profile update(@RequestBody Profile profiles)   
//	{  
//	profilesService.saveOrUpdate(profiles);  
//	return profiles;  
//	}  
}
