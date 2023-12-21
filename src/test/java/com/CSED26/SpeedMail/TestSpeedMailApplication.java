package com.CSED26.SpeedMail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSpeedMailApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpeedMailApplication::main).with(TestSpeedMailApplication.class).run(args);
	}

}
