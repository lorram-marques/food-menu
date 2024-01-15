package com.lorram.menu.dto;

import java.io.Serializable;

import com.lorram.menu.entities.Meal;
import com.lorram.menu.entities.Review;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String body;
	private Meal meal;
	
	public ReviewDTO() {
	}

	public ReviewDTO(Long id, String title, String body, Meal meal) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.meal = meal;
	}
	
	public ReviewDTO(Review review) {
		id = review.getId();
		title = review.getTitle();
		body = review.getBody();
		meal = review.getMeal();
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
}
