package com.springBootCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBootCRUD.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
