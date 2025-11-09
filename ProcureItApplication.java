package co.edu.elbosque.procureit;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProcureItApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProcureItApplication.class, args);
	}
}
