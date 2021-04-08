package kg.aios.application.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "kg.aios.application.config", "kg.aios.application.security", "kg.aios.application.service", "kg.aios.application.api"})
public class RestAppConfig {

	public static void main(String[] args) {
		SpringApplication.run(RestAppConfig.class, args);
	}

}
