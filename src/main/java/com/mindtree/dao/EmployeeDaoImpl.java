package com.mindtree.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.validation.Valid;

import com.mindtree.exception.EmployeeNotFound;
import com.mindtree.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	HashMap<String, Employee> employeesList = new HashMap<String, Employee>();

	@Override
	public boolean addEmployee(@Valid Employee employee) {
		employeesList.put(employee.getId(), employee);
		return false;
	}

	@Override
	public Employee getEmployee(String id) throws EmployeeNotFound {
		if (employeesList.containsKey(id))
			return employeesList.get(id);
		else
			throw new EmployeeNotFound("Employee Not Found");
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		for (Entry<String, Employee> entry : this.employeesList.entrySet()) {
			employees.add(entry.getValue());
		}

		return employees;
	}

	@Override
	public boolean deleteEmployee(String id) throws EmployeeNotFound {
		if (employeesList.containsKey(id)) {
			employeesList.remove(id);
			return true;
		} else
			throw new EmployeeNotFound("Employee Not Found");
	}

}
