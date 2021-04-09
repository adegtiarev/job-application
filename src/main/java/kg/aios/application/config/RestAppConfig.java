package kg.aios.application.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("kg.aios.application.dao")
@EntityScan("kg.aios.application.model")
@SpringBootApplication
@ComponentScan({ "kg.aios.application.service", "kg.aios.application.api" })
public class RestAppConfig {

	public static void main(String[] args) {
		SpringApplication.run(RestAppConfig.class, args);
	}

}
