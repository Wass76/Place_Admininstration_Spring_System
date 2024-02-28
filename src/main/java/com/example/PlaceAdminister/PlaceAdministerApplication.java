package com.example.PlaceAdminister;

import com.pusher.rest.Pusher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.File;
import java.util.Collections;

@SpringBootApplication
public class PlaceAdministerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaceAdministerApplication.class, args);

	}
}
