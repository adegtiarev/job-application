package kg.aios.application.config;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = { "kg.aios.application.service", "kg.aios.application.api",
		"kg.aios.application.feature" })
public class TestConfig {

}
