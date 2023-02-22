package com.example.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

import java.time.*;
import java.util.List;

@Service
public class TaskService {
	@Autowired
        TaskRepository taskRepository;
	
	public Task createTask(Task task) {
		if(task.getDateCreated() == null) {
			task.setDateCreated(LocalDateTime.now());
		}
		return taskRepository.save(task);
	}
	
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}
	
	public void deleteTask(Long taskId) {
		taskRepository.deleteById(taskId);
	}
	
	public Task updateTask(Long taskId, Task taskDetails) {
		//TODO add try catch, and data validation
		Task task = taskRepository.findById(taskId).get();
		task.setName(taskDetails.getDescription());
		task.setDescription(taskDetails.getDescription());
		task.setDateCompleted(taskDetails.getDateCompleted());
		task.setOwnerID(taskDetails.getOwnerID());
		
		task.setDateCreated(taskDetails.getDateCreated());
		
		return taskRepository.save(task);
	}
		
		
}