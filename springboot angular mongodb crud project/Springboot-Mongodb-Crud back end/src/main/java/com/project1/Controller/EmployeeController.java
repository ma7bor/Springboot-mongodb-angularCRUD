package com.project1.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project1.Exception.ResourceNotFoundException;
import com.project1.Repo.EmployeeRepo;
import com.project1.Service.SequenceService;
import com.project1.model.Employee;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepo employeeRepo ;
	
	@Autowired
	private SequenceService sequenceService  ;
	
	// this method is used to  retreive all the employees list
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		return employeeRepo.findAll();		
	}
	
	
	// this  handler method is used to retrieve employee by id  
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	
	// this method is to add employee in the  database
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		employee.setId(sequenceService.generateSequence(Employee.SEQUENCE_NAME));
		return employeeRepo.save(employee);
	}

	
	
	
	//this handler method is used to update employee information by id
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// this one is for delete an employee from the DB
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
