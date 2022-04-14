package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.Entity.Profile;
import com.demo.Entity.UserPrinciple;
import com.demo.Repositry.ProfileRepositry;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private ProfileRepositry repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Profile p = repo.findByEmail(username);
		if (p == null)
			throw new UsernameNotFoundException("User Not Found");
		return new UserPrinciple(p);
	}

}
