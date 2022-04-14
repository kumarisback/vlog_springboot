package com.demo.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//mark class as an Entity   
@Entity
//defining class name as Table name  
@Table
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String user;

	@Column
	private String password;

	@Column
	private String role;

	@Column
	private String email;

	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Like_post> likesLike_posts = new HashSet<Like_post>();

	public Set<Like_post> getLikesLike_posts() {
		return likesLike_posts;
	}

	public void setLikesLike_posts(Set<Like_post> likesLike_posts) {
		this.likesLike_posts = likesLike_posts;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Profile() {

	}
//	public Profile(long id, String user, String password) {
//		super();
//		this.id = id;
//		this.user = user;
//		this.password = password;
//	}

//	public Profile( String user, String password) {
//		super();
//		this.user = user;
//		this.password = password;
//	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", user=" + user + ", password=" + password + "]";
	}

	public String getUser() {
		return user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public int getProfileid() {
		// TODO Auto-generated method stub
		return 0;
	}

}
