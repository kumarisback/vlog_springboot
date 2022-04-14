package com.demo.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "uniqueall", columnNames = { "post_id", "profile_id" }))
//@Embeddable
public class Like_post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Post post;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Profile profile;

//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return Objects.hash(getPost(), getProfile());
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Like_post other = (Like_post) obj;
//
//		return Objects.equals(getPost(), other.getPost()) && Objects.equals(getProfile(), other.getProfile());
//	}

	@Override
	public String toString() {
		return "Like_post [id=" + id + ", post=" + post + ", profile=" + profile + "]";
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public int getPostId() {
		return post.getId();
	}

	public Like_post() {
//		super();
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Like_post(Post post, Profile profile) {
//		super();
		this.post = post;
		this.profile = profile;
	}

}
