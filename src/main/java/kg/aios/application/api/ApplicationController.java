package kg.aios.application.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	
	public void addNewApplication() {
		
	}
}
