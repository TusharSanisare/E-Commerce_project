package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = "controller")
//@ComponentScan(basePackages = "sevrice")

@EnableJpaRepositories("repository")
@ComponentScan(basePackages = {"controller", "sevrice", "dto"})
@EntityScan(basePackages = {"model"})

public class ECommerceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceProjectApplication.class, args);
	}

}
