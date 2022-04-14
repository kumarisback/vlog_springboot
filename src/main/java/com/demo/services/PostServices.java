package com.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Entity.Post;
import com.demo.Repositry.PostRepositry;

@Service
public class PostServices {
	@Autowired
	PostRepositry postRepository;

	public void saveOrUpdate(Post post) {
		postRepository.save(post);
	}

	public Optional<Post> findbyId(int id) {
		return postRepository.findById(id);
	}

	// getting all books record by using the method findaAll() of CrudRepository
	public List<Post> getAllPost() {
		List<Post> posts = new ArrayList<Post>();
		postRepository.findAll().forEach(post -> posts.add(post));
		System.out.println("hi===============");
		return posts;
	}

}
