package org.nico.trap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
public class TrapApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TrapApplication.class, args);
	}
}
