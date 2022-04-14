package com.demo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Entity.Like_post;
import com.demo.Repositry.LikeRepositry;
import com.demo.Repositry.PostRepositry;

@Service
@Transactional
public class LikePost {

	@Autowired
	LikeRepositry likeRepositry;

	@Autowired
	PostRepositry postRepositry;

	public void saveOrUpdate(Like_post likes) {
		Optional<Like_post> likepost = likeRepositry.findById(likes.getId());

		if (!likepost.isPresent()) {
			likeRepositry.save(likes);

//			Post p = likes.getPost();
//			p.getLikesLike_posts();
////
			System.out.println("---------------------");

		} else {
			System.out.println("--------");

			likeRepositry.deleteById(likes.getId());
		}
		System.out.println(likepost.toString() + "=====");

	}

	public int findLikeCounts(int id) {
		postRepositry.findById(id);
		return id;
	}
}
