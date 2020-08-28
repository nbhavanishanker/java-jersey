package com.mindtree.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ManagedAsync;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
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
	@ManagedAsync
	public void getEmployee(@PathParam(value = "id") final String id, @Suspended final AsyncResponse asyncResponse)
			throws EmployeeNotFound {
		ListenableFuture<Employee> future = employeeDao.getEmployeeAsync(id);
		Futures.addCallback(future, new FutureCallback<Object>() {

			@Override
			public void onSuccess(Object result) {
				asyncResponse.resume(result);
			}

			@Override
			public void onFailure(Throwable t) {
				if (t instanceof EmployeeNotFound)
					asyncResponse.resume(Response.status(Status.NOT_FOUND).entity(t.getLocalizedMessage()).build());
				if (t instanceof NumberFormatException)
					asyncResponse
							.resume(Response.status(Status.BAD_REQUEST).entity("Bad input for Employee ID").build());
				if (t instanceof Exception)
					asyncResponse.resume(
							Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build());
				asyncResponse.resume(t);
			}
		});
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getEmployees(@Suspended final AsyncResponse asyncResponse) {
		ListenableFuture<List<Employee>> future = employeeDao.getAllEmployeesAsync();
		Futures.addCallback(future, new FutureCallback<List<Employee>>() {

			@Override
			public void onSuccess(List<Employee> result) {
				asyncResponse.resume(result);
			}

			@Override
			public void onFailure(Throwable t) {
				asyncResponse.resume(t);
			}
		});
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void addEmployee(@Valid Employee employee, @Suspended final AsyncResponse asyncResponse) {
		ListenableFuture<Boolean> future = employeeDao.addEmployeeAsync(employee);
		Futures.addCallback(future, new FutureCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				asyncResponse.resume(Response.status(Status.CREATED).entity(result).build());
			}

			@Override
			public void onFailure(Throwable t) {
				if (t instanceof BadRequestException)
					asyncResponse.resume(Response.status(Status.BAD_REQUEST).entity(t.getLocalizedMessage()).build());
				asyncResponse.resume(Response.status(Status.BAD_GATEWAY).build());
			}
		});
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void deleteEmployee(@PathParam(value = "id") String id, @Suspended final AsyncResponse asyncResponse)
			throws NumberFormatException, EmployeeNotFound {
		ListenableFuture<Boolean> future = employeeDao.deleteEmployeeAsync(id);
		Futures.addCallback(future, new FutureCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				asyncResponse.resume(Response.status(Status.NO_CONTENT).entity(result).build());
			}

			@Override
			public void onFailure(Throwable t) {
				if (t instanceof NumberFormatException)
					asyncResponse.resume(Response.status(Status.BAD_REQUEST).entity(t.getLocalizedMessage()).build());
				if (t instanceof EmployeeNotFound)
					asyncResponse.resume(Response.status(Status.NOT_FOUND).entity(t.getLocalizedMessage()).build());
				asyncResponse.resume(t);
			}
		});
	}

}
