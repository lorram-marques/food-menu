package com.lorram.menu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page; // TODO !!
import org.springframework.data.domain.Pageable; // TODO !!
import org.springframework.stereotype.Service;

import com.lorram.menu.dto.UserDTO;
import com.lorram.menu.entities.User;
import com.lorram.menu.repositories.UserRepository;
import com.lorram.menu.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDTO(x));
	}
	
	public UserDTO findById(Long id) {
		Optional<User> entity = repository.findById(id);
		User user = entity.orElseThrow(() -> new ResourceNotFoundException(id));
		UserDTO dto = new UserDTO(user);
		return dto;
	}
	
	public UserDTO insert(UserDTO dto) {
		User user = new User();
		fromDto(dto, user);
		repository.save(user);
		return new UserDTO(user);
	}
	
	public UserDTO update(Long id, UserDTO dto) {
		User user = repository.getOne(id);
		fromDto(dto, user);
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void fromDto(UserDTO userDto, User user) {
		user.setName(userDto.getName());
	}
}
