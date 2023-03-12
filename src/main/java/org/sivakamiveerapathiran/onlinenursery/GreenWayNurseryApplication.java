package org.sivakamiveerapathiran.onlinenursery;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class is the entry point for the application.
 * ****
 ***************************/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GreenWayNurseryApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GreenWayNurseryApplication.class, args);
		System.out.println("Spring Boot Started:");
	}

}

