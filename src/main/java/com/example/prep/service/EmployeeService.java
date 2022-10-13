package com.example.prep.service;

import java.util.List;

import com.example.prep.entity.Employee;

public interface EmployeeService {

	public void saveEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(Long id);

	public void deleteEmployeeById(Long id);

	public void updateEmployeeById(Employee employee);

	public boolean isEmployeeId(Long id);


}
