package com.lorram.menu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.menu.dto.ReviewDTO;
import com.lorram.menu.entities.Meal;
import com.lorram.menu.entities.Review;
import com.lorram.menu.repositories.MealRepository;
import com.lorram.menu.repositories.ReviewRepository;
import com.lorram.menu.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService{

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MealRepository mealRepository;
	
	public Page<ReviewDTO> findAll(Pageable pageable) {
		Page<Review> list = repository.findAll(pageable);
		return list.map(x -> new ReviewDTO(x));
	}
	
	public ReviewDTO findById(Long id) {
		Optional<Review> entity = repository.findById(id);
		Review review = entity.orElseThrow(() -> new ResourceNotFoundException(id));
		ReviewDTO dto = new ReviewDTO(review);
		return dto;
	}
	
	public ReviewDTO insert(Long id, ReviewDTO dto) {
		Optional<Meal> meal = mealRepository.findById(id);
		dto.setMeal(meal.get());
		Review review = new Review();
		fromDto(dto, review);
		repository.save(review);
		return new ReviewDTO(review);
	}
	
	public ReviewDTO update(Long id, ReviewDTO dto) {
		Review review = repository.getOne(id);
		fromDto(dto, review);
		review = repository.save(review);
		return new ReviewDTO(review);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void fromDto(ReviewDTO reviewDto, Review review) {
		review.setTitle(reviewDto.getTitle());
		review.setBody(reviewDto.getBody());
		review.setMeal(reviewDto.getMeal());
	}
}
