package se.springboot.sharitytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("se.springboot.sharitytest")

public class SharitytestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharitytestApplication.class, args);
	}

}
