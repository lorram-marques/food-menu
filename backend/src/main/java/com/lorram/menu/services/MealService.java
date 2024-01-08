package com.lorram.menu.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.menu.dto.MealDTO;
import com.lorram.menu.dto.ReviewDTO;
import com.lorram.menu.entities.Meal;
import com.lorram.menu.repositories.MealRepository;
import com.lorram.menu.services.exceptions.ResourceNotFoundException;

@Service
public class MealService{

	@Autowired
	private MealRepository repository;
	
	public Page<MealDTO> findAll(Pageable pageable) {
		Page<Meal> list = repository.findAll(pageable);
		return list.map(x -> new MealDTO(x));
	}
	
	public MealDTO findById(Long id) {
		Optional<Meal> entity = repository.findById(id);
		Meal Meal = entity.orElseThrow(() -> new ResourceNotFoundException(id));
		MealDTO dto = new MealDTO(Meal);
		return dto;
	}
	
	public MealDTO insert(MealDTO dto) {
		Meal Meal = new Meal();
		fromDto(dto, Meal);
		repository.save(Meal);
		return new MealDTO(Meal);
	}
	
	public MealDTO update(Long id, MealDTO dto) {
		Meal Meal = repository.getOne(id);
		fromDto(dto, Meal);
		Meal = repository.save(Meal);
		return new MealDTO(Meal);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public List<ReviewDTO> findReviews(Long id) {
		Optional<Meal> entity = repository.findById(id);
		Meal meal = entity.orElseThrow(() -> new ResourceNotFoundException(id));
		List<ReviewDTO> list = meal.getReviews().stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
		return list;
	}
	
	private void fromDto(MealDTO MealDto, Meal Meal) {
		Meal.setName(MealDto.getName());
	}
}
