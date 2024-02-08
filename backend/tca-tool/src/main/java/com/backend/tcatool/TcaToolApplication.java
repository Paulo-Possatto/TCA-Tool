package com.backend.tcatool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.backend.tcatool.entity"})
public class TcaToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcaToolApplication.class, args);
	}

}
