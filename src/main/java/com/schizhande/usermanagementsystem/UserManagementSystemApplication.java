package com.schizhande.usermanagementsystem;

import com.schizhande.usermanagementsystem.authorities.Permissions;
import com.schizhande.usermanagementsystem.service.UserPermissionService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

import static java.util.stream.Collectors.toSet;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.schizhande")
@EntityScan(basePackages = "com.schizhande")
public class UserManagementSystemApplication implements CommandLineRunner {

    @Autowired
    private UserPermissionService userPermissionService;

    public static void main(String[] args) {
        SpringApplication.run(UserManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    	log.info("---> saving permissions");
        val permissions = Arrays.stream(Permissions.values())
				.map(Enum::name)
				.collect(toSet());
        userPermissionService.createManyPermission(permissions);

    }
}
