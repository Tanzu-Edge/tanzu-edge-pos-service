package com.tanzu.posdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PosDataServiceApplication {

	public static void main(String[] args) {
		String timed="NOW";
		
		SpringApplication.run(PosDataServiceApplication.class, args);
	}

}
