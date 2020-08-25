package com.mindtree.dao;

import java.util.List;

import com.mindtree.exception.EmployeeNotFound;
import com.mindtree.model.Employee;

public interface EmployeeDao {

	boolean addEmployee(Employee employee);

	Employee getEmployee(String id) throws EmployeeNotFound;

	List<Employee> getAllEmployees();

	boolean deleteEmployee(String id) throws EmployeeNotFound;
}
