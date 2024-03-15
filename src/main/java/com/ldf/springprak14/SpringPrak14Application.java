package com.ldf.springprak14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ldf.springprak14.Entity")
public class SpringPrak14Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringPrak14Application.class, args);
	}

}
