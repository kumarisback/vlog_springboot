package com.demo.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Post {

	@Id
	@GeneratedValue
	int id;

	@Column
	private String path;

	@Column
	private String user;

	@Column
	private String content;

	@Column
	private int likes;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Like_post> likesLike_posts = new HashSet<Like_post>();

	public Set<Like_post> getLikesLike_posts() {
		return likesLike_posts;
	}

	public void setLikesLike_posts(Set<Like_post> likesLike_posts) {
		this.likesLike_posts = likesLike_posts;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private Profile profile;

	@Override
	public String toString() {
		return "Post [id=" + id + ", path=" + path + ", user=" + user + ", content=" + content + ", likes=" + likes
				+ ", profile=" + profile + "]";
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLike(int likes) {
		this.likes = likes;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
