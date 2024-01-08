package com.lorram.menu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.menu.entities.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
