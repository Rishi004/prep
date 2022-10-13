package com.example.prep.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.prep.dto.EmployeeDto;
import com.example.prep.entity.Employee;
import com.example.prep.service.EmployeeService;
import com.example.prep.util.Constants;
import com.example.prep.util.EndPointURI;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(value = EndPointURI.EMPLOYEE)
	public ResponseEntity<Object> addEmployee (@Valid @RequestBody EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		employeeService.saveEmployee(employee);
		return new ResponseEntity<>(Constants.ADD_EMPLOYEE_SUCCESS, HttpStatus.OK);		
	}
	
	@GetMapping(value = EndPointURI.EMPLOYEE)
	public ResponseEntity<Object> getAllEmployee() {
		return new ResponseEntity<Object>(employeeService.getAllEmployees(), HttpStatus.OK);
	}
	
	@GetMapping(value = EndPointURI.EMPLOYEE_BY_ID)
	public ResponseEntity<Object> getEmployeeById(@PathVariable Long id){
		return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}
	
	@DeleteMapping(value = EndPointURI.EMPLOYEE_BY_ID)
	public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<>(Constants.DELETE_EMPLOYEE_SUCCESS, HttpStatus.OK);
	}
	
	@PutMapping(value = EndPointURI.EMPLOYEE_BY_ID)
	public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		if (!employeeService.isEmployeeId(employeeDto.getId())) {
			return new ResponseEntity<>(Constants.EMPLOYEE, HttpStatus.BAD_REQUEST);
		}
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		employeeService.updateEmployeeById(employee);
		return new ResponseEntity<Object>(Constants.UPDATE_EMPLOYEE_SUCCESS, HttpStatus.OK);
		
	}
}
