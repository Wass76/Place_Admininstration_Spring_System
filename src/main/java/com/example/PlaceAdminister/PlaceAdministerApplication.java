package com.example.PlaceAdminister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PlaceAdministerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceAdministerApplication.class, args);
	}

}
