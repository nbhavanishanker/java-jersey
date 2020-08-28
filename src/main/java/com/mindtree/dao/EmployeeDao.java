package com.mindtree.dao;

import java.util.List;

import com.google.common.util.concurrent.ListenableFuture;
import com.mindtree.exception.EmployeeNotFound;
import com.mindtree.model.Employee;

public interface EmployeeDao {

	Boolean addEmployee(Employee employee) throws Exception;

	ListenableFuture<Boolean> addEmployeeAsync(final Employee employee);

	Object getEmployee(Integer id) throws EmployeeNotFound;

	ListenableFuture<Employee> getEmployeeAsync(String id) throws EmployeeNotFound;

	List<Employee> getAllEmployees();

	ListenableFuture<List<Employee>> getAllEmployeesAsync();

	Boolean deleteEmployee(Integer id) throws EmployeeNotFound;

	ListenableFuture<Boolean> deleteEmployeeAsync(String id);

}
