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

@RestController
@RequestMapping(value = "/menu")
public class MealController {
	
	@Autowired
	private MealService service;
	
	@GetMapping
	public ResponseEntity<Page<MealDTO>> findAll(Pageable pageable) {
		Page<MealDTO> page = service.findAll(pageable);
		return ResponseEntity.ok().body(page);
		}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MealDTO> findById(@PathVariable Long id) {
		MealDTO Meal = service.findById(id);
		return ResponseEntity.ok().body(Meal);
	}
	
	@PostMapping
	public ResponseEntity<MealDTO> insert(@RequestBody MealDTO dto) {
		MealDTO Meal = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Meal.getId()).toUri();
		return ResponseEntity.created(uri).body(Meal);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MealDTO> update(@PathVariable Long id, @RequestBody MealDTO dto) {
		MealDTO Meal = service.update(id, dto);
		return ResponseEntity.ok().body(Meal);
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
	
}