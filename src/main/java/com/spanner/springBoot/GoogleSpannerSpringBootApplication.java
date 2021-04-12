package com.spanner.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"model"})
@EnableJpaRepositories(basePackages = {"model"})
public class GoogleSpannerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleSpannerSpringBootApplication.class, args);
	}

}
