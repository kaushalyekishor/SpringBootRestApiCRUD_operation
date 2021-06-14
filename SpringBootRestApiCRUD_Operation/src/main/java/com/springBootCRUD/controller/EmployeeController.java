package com.springBootCRUD.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBootCRUD.dao.EmployeeDAO;
import com.springBootCRUD.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {
 
	@Autowired
	EmployeeDAO employeeDAO;
	
	/*to save an employee*/
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}
	
	/*get All Empoyee*/
	@GetMapping("employees")
	public List<Employee> getAllEmployee(){
		return employeeDAO.findAll();
	}
	
	/*get Employee by Id*/
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empid){
		
		Employee emp = employeeDAO.findOne(empid);
		
		if(emp == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	/*update an empoyee by empid*/
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empid, @Valid @RequestBody Employee empDetails){
		
		Employee emp = employeeDAO.findOne(empid);
		
		if(emp == null) {
			return ResponseEntity.notFound().build();
		}
		
		emp.setName(empDetails.getName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setExperties(empDetails.getExperties());
		
		Employee updaEmployee = employeeDAO.save(emp);
		
		return ResponseEntity.ok().body(updaEmployee);
	}
	
	/*Delete an Employee*/
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empid){
		
		Employee emp = employeeDAO.findOne(empid);
		
		if(emp == null) {
			return ResponseEntity.notFound().build();
		}
		
		employeeDAO.delete(emp);
		
		return ResponseEntity.ok().build();
		
	}
}
