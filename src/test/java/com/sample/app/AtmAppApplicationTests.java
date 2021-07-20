package com.sample.app;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.sample.app.controller.HomeController;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AtmAppApplication.class)
class AtmAppApplicationTests {

	@Autowired
	HomeController homeController;

	@Test
	void contextLoads() {
	}

	@Test
	public void testApplicationLload() {
		assertNotNull(homeController, "homeController is not loaded");
	}

	@Test
	public void testgetAtms() {
		assertNotEquals(homeController.getAtms().getBody(),"");
	}
	
	@Test
	public void testfindAtmsByCity() {
		//assertNotEquals(homeController.getAtmsByCity("Hulsberg").getBody(),"");
	}

}
