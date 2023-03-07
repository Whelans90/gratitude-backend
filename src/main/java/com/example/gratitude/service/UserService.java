package com.example.gratitude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gratitude.model.User;
import com.example.gratitude.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	// CREATE
	public User createUser(User user) {
		return userRepository.save(user);
	}

	// READ
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	// DELETE
	public void deleteUser(Long user) {
		userRepository.deleteById(user);
	}

	// UPDATE
	public User updateUser(Long userId, User userDetails) {
		User user = userRepository.findById(userId).get();
		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());

		return userRepository.save(user);
	}
	
	public User getUserByUsername (String username) {
		User user = userRepository.findByEmail(username).get();
		
		return user;
	}
}
