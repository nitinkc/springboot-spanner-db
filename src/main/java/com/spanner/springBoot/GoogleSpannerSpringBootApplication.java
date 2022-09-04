package com.spanner.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.gcp.data.spanner.repository.config.EnableSpannerRepositories;

@EnableSpannerRepositories(basePackages = {"com.spanner.springBoot.model","com.spanner.springBoot.model.refdata"})
@SpringBootApplication(scanBasePackages = {"com.spanner.springBoot.model",
		"com.spanner.springBoot.model.refdata",
		"com.spanner.springBoot.controller",
		"com.spanner.springBoot.service",
		"com.spanner.springBoot.dao",
		"com.spanner.springBoot.config"
})
@EnableCaching
public class GoogleSpannerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleSpannerSpringBootApplication.class, args);
	}

}
