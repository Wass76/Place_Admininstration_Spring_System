package com.example.PlaceAdminister;

//import com.pusher.rest.Pusher;
import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Request.PlaceRequest;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Service.AuthenticationService;
import com.example.PlaceAdminister.Service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.util.Collections;

import static com.example.PlaceAdminister.Model_Entitiy.Role.ADMIN;
import static com.example.PlaceAdminister.Model_Entitiy.Role.MANAGER;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@RequiredArgsConstructor
public class PlaceAdministerApplication {

	private final PlaceRepository placeRepository;

	private final PlaceService placeService;
	public static void main(String[] args) {
		SpringApplication.run(PlaceAdministerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService authenticationService
	){
		return args -> {

			var hub = PlaceRequest.builder()
					.name("Hub")
					.locations("Center")
					.build();
			PlaceDTO placeDTO = new PlaceDTO(hub);
			PlaceEntity place =  placeService.store(placeDTO);

			System.out.println(place.getId());


			var admin = RegisterRequest.builder()
					.firstName("Admin")
					.lastName("Admin")
					.email("admin@gmail.com")
					.password("password")
					.role(ADMIN)
					.place_id(place.getId())
					.build();
			System.out.println("admin token: " + authenticationService.register(admin).getToken());

			var manager = RegisterRequest.builder()
					.firstName("Manager")
					.lastName("Manager")
					.email("manager@gmail.com")
					.password("password")
					.role(MANAGER)
					.place_id(place.getId())
					.build();
			System.out.println("manager token: " + authenticationService.register(manager).getToken());


		};
	}
}
