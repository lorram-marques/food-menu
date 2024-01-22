package com.lorram.menu.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lorram.menu.dto.MealDTO;
import com.lorram.menu.dto.ReviewDTO;
import com.lorram.menu.services.MealService;
import com.lorram.menu.services.ReviewService;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {
	
	@Autowired
	private MealService service;
	
	@Autowired
	private ReviewService reviewService;
	
	// Meal controller
	
	@GetMapping
	public ResponseEntity<Page<MealDTO>> findAll(Pageable pageable) {
		Page<MealDTO> page = service.findAll(pageable);
		return ResponseEntity.ok().body(page);
		}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MealDTO> findById(@PathVariable Long id) {
		MealDTO meal = service.findById(id);
		return ResponseEntity.ok().body(meal);
	}
	
	@PostMapping
	public ResponseEntity<MealDTO> insert(@RequestBody MealDTO dto) {
		MealDTO meal = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(meal.getId()).toUri();
		return ResponseEntity.created(uri).body(meal);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MealDTO> update(@PathVariable Long id, @RequestBody MealDTO dto) {
		MealDTO meal = service.update(id, dto);
		return ResponseEntity.ok().body(meal);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> findReviews(@PathVariable Long id) {
		List<ReviewDTO> reviews = service.findReviews(id);
		return ResponseEntity.ok().body(reviews);
	}
	
	// Review controller
	
	@GetMapping(value = "/reviews") // <--- admin only 
	public ResponseEntity<Page<ReviewDTO>> findAllReviews(Pageable pageable) {
		Page<ReviewDTO> page = reviewService.findAll(pageable);
		return ResponseEntity.ok().body(page);
		}
	
	@GetMapping(value = "/reviewid/{id}")
	public ResponseEntity<ReviewDTO> findReviewById(@PathVariable Long id) {
		ReviewDTO review = reviewService.findById(id);
		return ResponseEntity.ok().body(review);
	} 
	
	@PostMapping(value = "/{id}/reviews")
	public ResponseEntity<ReviewDTO> insertReview(@PathVariable Long id, @RequestBody ReviewDTO dto) {
		ReviewDTO review = reviewService.insert(id, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(review.getId()).toUri();
		return ResponseEntity.created(uri).body(review);
	}
	
	@DeleteMapping(value = "/reviewid/{id}")
	public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
		reviewService.delete(id);
		return ResponseEntity.noContent().build();
	}
}