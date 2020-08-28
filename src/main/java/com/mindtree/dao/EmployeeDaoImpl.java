package com.mindtree.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.mindtree.exception.EmployeeNotFound;
import com.mindtree.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	Map<Integer, Employee> employeesList = new ConcurrentHashMap<Integer, Employee>();

	ListeningExecutorService service;

	public EmployeeDaoImpl() {
		service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
	}

	@Override
	public Employee getEmployee(Integer id) throws EmployeeNotFound {
		if (employeesList.containsKey(id))
			return employeesList.get(id);
		else
			throw new EmployeeNotFound("Employee not found");
	}

	public ListenableFuture<Employee> getEmployeeAsync(final String id) {
		ListenableFuture<Employee> future = service.submit(new Callable<Employee>() {

			@Override
			public Employee call() throws NumberFormatException, EmployeeNotFound {
				return getEmployee(Integer.parseInt(id));
			}
		});
		return future;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		for (Entry<Integer, Employee> entry : this.employeesList.entrySet()) {
			employees.add(entry.getValue());
		}

		return employees;
	}

	public ListenableFuture<List<Employee>> getAllEmployeesAsync() {
		ListenableFuture<List<Employee>> future = service.submit(new Callable<List<Employee>>() {

			@Override
			public List<Employee> call() throws Exception {
				return getAllEmployees();
			}
		});

		return future;
	}

	@Override
	public Boolean addEmployee(@Valid Employee employee) {
		if (employeesList.containsKey(employee.getId())) {
			throw new BadRequestException("Invalid Input");
		} else {
			employeesList.put(employee.getId(), employee);
			return true;
		}
	}

	public ListenableFuture<Boolean> addEmployeeAsync(@Valid final Employee employee) {
		ListenableFuture<Boolean> response = service.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() {
				return addEmployee(employee);
			}
		});

		return response;
	}

	@Override
	public Boolean deleteEmployee(Integer id) throws EmployeeNotFound {
		if (employeesList.containsKey(id)) {
			employeesList.remove(id);
			return new Boolean(true);
		} else
			throw new EmployeeNotFound("Employee Not Found");
	}

	public ListenableFuture<Boolean> deleteEmployeeAsync(final String id) {
		ListenableFuture<Boolean> future = service.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() throws NumberFormatException, EmployeeNotFound {
				return deleteEmployee(Integer.parseInt(id));
			}
		});
		return future;
	}

}
