package com.mindtree;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.mindtree.config.App;
import com.mindtree.dao.EmployeeDao;
import com.mindtree.dao.EmployeeDaoImpl;

public class MyResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		final EmployeeDao employeeDao = new EmployeeDaoImpl();
		return new App(employeeDao);
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testGetIt() {
		String responseMsg = target("myresource").request().get(String.class);
		assertEquals("Got it!", responseMsg);
	}
}
