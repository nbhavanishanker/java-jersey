package com.mindtree.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.mindtree.dao.EmployeeDao;

public class App extends ResourceConfig {

	public App(final EmployeeDao employeeDao) {
		packages("com.mindtree").register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(employeeDao).to(EmployeeDao.class);
			}
		});
	}

}
