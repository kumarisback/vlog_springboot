package com.demo.Repositry;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.Entity.Profile;

//use for deleting the record from table
@Transactional
public interface ProfileRepositry extends JpaRepository<Profile, Integer> {

	Optional<Profile> findById(Long id);

	void deleteById(Long id);

//	void findByEmail(String user);
	@Query("SELECT p FROM Profile p WHERE p.email = :user")
	Profile findByEmail(@Param("user") String user);

//	@Query("SELECT p FROM Profile p WHERE p.name = :user")
	Profile findByUser(String user);

}
