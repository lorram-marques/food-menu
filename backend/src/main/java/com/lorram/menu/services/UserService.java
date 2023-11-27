package com.lorram.menu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; // TODO !!
import org.springframework.data.domain.Pageable; // TODO !!
import org.springframework.stereotype.Service;

import com.lorram.menu.dto.UserDTO;
import com.lorram.menu.entities.User;
import com.lorram.menu.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDTO(x));
	}
}
