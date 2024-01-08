package com.lorram.menu.dto;

import java.io.Serializable;

import com.lorram.menu.entities.Review;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String body;
	
	public ReviewDTO() {
	}

	public ReviewDTO(Long id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	public ReviewDTO(Review review) {
		id = review.getId();
		title = review.getTitle();
		body = review.getBody();
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
}
