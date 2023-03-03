package com.example.gratitude.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.gratitude.model.User;
import com.example.gratitude.service.UserService;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/Users", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/users")
	public List<User> readUsers() {
		return userService.getUsers();
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
	public User updateUser(@PathVariable(value = "userId") Long id, @RequestBody User userDetails) {
		return userService.updateUser(id, userDetails);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable(value = "userId") Long id) {
		userService.deleteUser(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
