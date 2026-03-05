package com.manuel.gestion_tareas_spring_mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GestionTareasSpringMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionTareasSpringMongodbApplication.class, args);
	}

}
