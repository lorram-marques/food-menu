package com.lorram.menu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.menu.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
}
