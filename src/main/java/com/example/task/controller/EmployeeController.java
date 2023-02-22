package com.example.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.example.task.model.Employee;
import com.example.task.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
        @Autowired
        EmployeeService empService;
        
        @PostMapping("/employees")
        public Employee createEmployee(@RequestBody Employee emp) {
        	return empService.createEmployee(emp);
        }
        
        @GetMapping("/employees")
        public List<Employee> readEmployees() {
        	return empService.getEmployees();
        }
        
        @PutMapping("/employees/{empId}") 
        public Employee updateEmployee(@PathVariable(value= "empId") Long id, @RequestBody Employee empDetails) {
        	return empService.updateEmployee(id, empDetails);
        }
        
        @DeleteMapping("/employees/{empId}")
        public void deleteEmployee(@PathVariable(value="empId") Long id) {
        	empService.deleteEmployee(id);
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
