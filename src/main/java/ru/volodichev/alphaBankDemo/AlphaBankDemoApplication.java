package ru.volodichev.alphaBankDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlphaBankDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphaBankDemoApplication.class, args);
	}

}
