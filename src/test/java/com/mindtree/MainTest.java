package com.mindtree;

import static org.junit.Assert.assertNotNull;

import java.awt.AWTException;
import java.io.IOException;

import org.junit.Test;

public class MainTest {

	@Test
	public void testStartServer() {
		assertNotNull(Main.startServer());
	}

	@Test
	public void testMain() throws IOException, AWTException {
		Main main = new Main();
		assertNotNull(main);
		Main.main(new String[] {});
	}
}
