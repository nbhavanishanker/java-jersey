package com.mindtree.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.mindtree.dao.EmployeeDao;
import com.mindtree.exception.EmployeeNotFound;
import com.mindtree.model.Employee;

@Path("employee")
public class EmployeeResource {

	@Context
	EmployeeDao employeeDao;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam(value = "id") String id) {
		try {
			return employeeDao.getEmployee(id);
		} catch (EmployeeNotFound e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		return employeeDao.getAllEmployees();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addEmployee(Employee employee) {
		try {
			employeeDao.addEmployee(employee);
			return "Success";
		} catch (Exception e) {
		}
		return "Failure";
	}

	@PATCH
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteEmployee(@PathParam(value = "id") String id) {
		try {
			employeeDao.deleteEmployee(id);
			return "Successfully Deleted";
		} catch (Exception e) {

		}
		return "Failed to delete";
	}

}
