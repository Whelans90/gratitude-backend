package com.example.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
