package com.demo.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Entity.Post;

public interface PostRepositry extends JpaRepository<Post, Integer> {

}
