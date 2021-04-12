package kg.aios.application.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kg.aios.application.model.Position;
import kg.aios.application.service.PositionService;

@RestController
@RequestMapping({ "/api" })
public class DefaultController {
	
	@Autowired
	private PositionService positionService;

	@RequestMapping(value = { "/", "" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String initialPage() {

		return "It works!";
	}
	
	@GetMapping("/positions")
	public List<Position> getPositions() {
		return positionService.findAllPositions();
	}

}
