package org.sivakamiveerapathiran.onlinenursery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoFirstApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoFirstApplication.class, args);
		System.out.println("Spring Boot Started:");
	}

}

