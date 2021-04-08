package kg.aios.application.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api" })
public class DefaultController {

	@RequestMapping(value = { "/", "" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String initialPage() {

		return "It works!";
	}
	
	

}
