package com.schizhande.autorepaircentremanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.schizhande")
@EntityScan(basePackages = "com.schizhande")
public class AutoRepairCentreManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoRepairCentreManagementSystemApplication.class, args);
	}

}
