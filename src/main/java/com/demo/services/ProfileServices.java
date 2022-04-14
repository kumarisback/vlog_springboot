package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Entity.Profile;
import com.demo.Repositry.ProfileRepositry;

@Service
public class ProfileServices {
	@Autowired
	ProfileRepositry profileRepository;

	// getting all books record by using the method findaAll() of CrudRepository
	public List<Profile> getAllProfile() {
		List<Profile> profiles = new ArrayList<Profile>();
		profileRepository.findAll().forEach(profile -> profiles.add(profile));
		return profiles;
	}

	// getting a specific record by using the method findById() of CrudRepository
	public Profile getProfilesById(Long id) {
		return profileRepository.findById(id).get();
	}

	// saving a specific record by using the method save() of CrudRepository
	public void saveOrUpdate(Profile profiles) {
		profileRepository.save(profiles);
	}

	// deleting a specific record by using the method deleteById() of CrudRepository
	public void delete(Long id) {
		profileRepository.deleteById(id);
	}

	// updating a record
	public void update(Profile profiles, int id) {
		profileRepository.save(profiles);
	}

	public Profile findbyUser(String user) {
		return profileRepository.findByUser(user);
	}

	public Profile findByEmail(String user) {

		Profile res = profileRepository.findByEmail(user);
//		System.out.println(res.toString());

		return res;

		// TODO Auto-generated method stub

	}

}
