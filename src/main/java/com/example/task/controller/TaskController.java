package com.example.task.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.model.Task;
import com.example.task.service.TaskService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class TaskController {
	@Autowired
    TaskService taskService;

	@PostMapping("/tasks")
	public Task createTask(@Valid@RequestBody Task task) {
		return taskService.createTask(task);
	}
	
	@GetMapping("/tasks")
	public List<Task> readTasks() {
		return taskService.getTasks();
	}
	
	@PutMapping("/tasks/{taskId}")
	public Task updateTask(@PathVariable(value="taskId") Long id, @Valid@RequestBody Task taskDetails) {
		return taskService.updateTask(id, taskDetails);
	}
	
	
	@DeleteMapping("/tasks/{taskId}")
	public void deleteTask(@PathVariable(value="taskId") Long id) {
		taskService.deleteTask(id);
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
