package kg.aios.application.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kg.aios.application.config.RestAppConfig;
import kg.aios.application.model.Position;
import kg.aios.application.service.PositionService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { RestAppConfig.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DefaultControllerTest {

	private MockMvc mvc;

	@MockBean
	private PositionService positionService;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testInitialPage() throws Exception {
		mvc.perform(get("/api").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").value("It works!"));
	}

	@Test
	public void testPositions() throws Exception {

		List<Position> positions = new ArrayList<>();
		Position position = new Position();
		position.setId(1L);
		position.setDescription("Java developer");
	
		positions.add(position);

		given(positionService.findAllPositions()).willReturn(positions);

		mvc.perform(get("/api/positions").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(1));
	}
}
