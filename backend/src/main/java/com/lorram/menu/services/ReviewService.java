package com.lorram.menu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lorram.menu.dto.ReviewDTO;
import com.lorram.menu.dto.UserDTO;
import com.lorram.menu.entities.Meal;
import com.lorram.menu.entities.Review;
import com.lorram.menu.entities.User;
import com.lorram.menu.repositories.MealRepository;
import com.lorram.menu.repositories.ReviewRepository;
import com.lorram.menu.repositories.UserRepository;
import com.lorram.menu.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService{

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
		dto.setUser(getCurrentUserDTO());
		Review review = new Review();
		fromDto(dto, review);
		repository.save(review);
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
	private UserDTO getCurrentUserDTO() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDTO user = new UserDTO(userRepository.findByEmail(username));
		return user;
		}
	
	private User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(username);
		}
	
	private void fromDto(ReviewDTO reviewDto, Review review) {
		review.setTitle(reviewDto.getTitle());
		review.setBody(reviewDto.getBody());
		review.setMeal(reviewDto.getMeal());
		review.setUser(getCurrentUser());
	}
	
}
