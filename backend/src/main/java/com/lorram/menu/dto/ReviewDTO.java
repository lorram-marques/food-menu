package com.lorram.menu.dto;

import java.io.Serializable;

import com.lorram.menu.entities.Meal;
import com.lorram.menu.entities.Review;
import com.lorram.menu.entities.User;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String body;
	private Meal meal;
	private UserDTO user;
	
	public ReviewDTO() {
	}

	public ReviewDTO(Long id, String title, String body, Meal meal, User user) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.meal = meal;
		this.user = new UserDTO(user);
	}
	
	public ReviewDTO(Review review) {
		id = review.getId();
		title = review.getTitle();
		body = review.getBody();
		meal = review.getMeal();
		user = new UserDTO(review.getUser());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO userDTO) {
		this.user = userDTO;
	}
}
