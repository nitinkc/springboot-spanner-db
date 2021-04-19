package com.spanner.springBoot;

import com.google.cloud.spring.data.spanner.repository.config.EnableSpannerRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"model"})
@EnableJpaRepositories(basePackages = {"model"})//Testing if both JPA and Spanner Repo works
@EnableSpannerRepositories(basePackages = {"model"})
public class GoogleSpannerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleSpannerSpringBootApplication.class, args);
	}

}
