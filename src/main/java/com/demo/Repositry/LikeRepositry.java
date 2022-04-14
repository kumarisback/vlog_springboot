package com.demo.Repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Entity.Like_post;

public interface LikeRepositry extends JpaRepository<Like_post, Integer> {

	Optional<Like_post> findById(int id);

}
