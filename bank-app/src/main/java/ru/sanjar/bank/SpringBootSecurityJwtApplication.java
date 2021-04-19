package ru.sanjar.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
		String to = "usd";
		String from = "rub";
		System.out.println(to.toUpperCase() + "_" + from.toUpperCase());
	}


}
