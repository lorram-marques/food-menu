package com.lorram.menu.dto;

import java.util.ArrayList;
import java.util.List;

import com.lorram.menu.entities.Meal;
import com.lorram.menu.entities.Review;

public class MealDTO {

	private Long id;
	private String name;
	private String description;
	
	private List<Review> reviews = new ArrayList<>();
	
	public MealDTO() {
	}

	public MealDTO(Long id, String name, String description, List<Review> reviews) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.reviews = reviews;
	}
	
	public MealDTO(Meal meal) {
		id = meal.getId();
		name = meal.getName();
		description = meal.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Review> getReviews() {
		return reviews;
	}
}
