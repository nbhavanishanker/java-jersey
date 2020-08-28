package com.mindtree.controller;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.mindtree.config.App;
import com.mindtree.dao.EmployeeDao;
import com.mindtree.dao.EmployeeDaoImpl;
import com.mindtree.model.Address;
import com.mindtree.model.Employee;

public class EmployeeResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		final EmployeeDao employeeDao = new EmployeeDaoImpl();
		return new App(employeeDao);
	}

	@Test
	public void testGetEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Bhavani Shanker");
		employee.setGender("Male");
		employee.setAddress(new Address(UUID.randomUUID().toString(), "Street Name", "Hyderabad", "500058"));
		Entity<Employee> entity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);
		Response response = target("employee").request().post(entity);
		response = target("employee").path(employee.getId() + "").request().get();
		Employee responseEmp = response.readEntity(Employee.class);
		assertEquals(responseEmp.getId(), employee.getId());
	}

	@Test
	public void testGetEmployee1() {
		Response response = target("employee").path(1 + "").request().get();
		assertEquals(404, response.getStatus());
	}

	@Test
	public void testGetEmployee2() {
		Response response = target("employee").path(1 + "a").request().get();
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testGetEmployees() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Bhavani Shanker");
		employee.setGender("Male");
		Entity<Employee> entity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);
		Response response = target("employee").request().post(entity);
		response = target("employee").request().get();
		assertEquals(200, response.getStatus());
	}

	@Test
	public void testAddEmployee() {
		Employee employee = new Employee();
		employee.setId(12);
		employee.setName("Bhavani Shanker");
		employee.setGender("Male");
		Entity<Employee> entity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);
		Response response = target("employee").request().post(entity);
		assertEquals(201, response.getStatus());
	}

	@Test
	public void testAddEmployee1() {
		Employee employee = new Employee();
		employee.setId(12);
		employee.setName(null);
		employee.setGender("Male");
		Entity<Employee> entity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);
		Response response = target("employee").request().post(entity);
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testAddEmployee2() {
		Employee employee = new Employee();
		employee.setId(12);
		employee.setName("Bhavani Shanker");
		employee.setGender("Male");
		Entity<Employee> entity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);
		target("employee").request().post(entity);
		Response response = target("employee").request().post(entity);
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testDeleteEmployee() {
		Employee employee = new Employee();
		employee.setId(12);
		employee.setName("Bhavani Shanker");
		employee.setGender("Male");
		Entity<Employee> entity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);
		Response response = target("employee").request().post(entity);
		response = target("employee").path(employee.getId() + "").request().delete();
		assertEquals(204, response.getStatus());
		response = target("employee").path(1 + "").request().delete();
		assertEquals(404, response.getStatus());
		response = target("employee").path("6a").request().delete();
		assertEquals(400, response.getStatus());
	}

}
