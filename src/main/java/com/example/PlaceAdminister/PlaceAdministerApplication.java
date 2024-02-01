package com.example.PlaceAdminister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.File;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PlaceAdministerApplication {

	public static void main(String[] args) {
		try {
			File file=new File("src/main/resources/Rooms.json");
			file.delete();
			file.createNewFile();
		}catch (Exception e){

		}
		SpringApplication.run(PlaceAdministerApplication.class, args);
	}

}
