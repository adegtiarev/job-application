package kg.aios.application.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import kg.aios.application.config.RestAppConfig;
import kg.aios.application.dto.JobApplicationDTO;
import kg.aios.application.dto.JobApplicationFieldDTO;
import kg.aios.application.model.Company;
import kg.aios.application.model.CompanyField;
import kg.aios.application.model.JobApplication;
import kg.aios.application.model.Position;
import kg.aios.application.service.ApplicationService;
import kg.aios.application.service.CompanyService;
import kg.aios.application.service.FeatureService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { RestAppConfig.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationControllerTest {

	private MockMvc mvc;

	@MockBean
	private ApplicationService applicationService;

	@MockBean
	private CompanyService companyService;

	@MockBean
	private FeatureService featureService;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();

		Company company = new Company();
		company.setId(1L);
		company.setName("Company 1");

		List<CompanyField> fields = new ArrayList<>();
		CompanyField field = new CompanyField();
		field.setId(1L);
		field.setName("Phone");
		field.setRequired(true);

		fields.add(field);

		company.setFields(fields);

		BDDMockito.given(companyService.findById(1L)).willReturn(company);

	}

	@Test
	public void testAddApplication() throws Exception {
		JobApplicationDTO application = new JobApplicationDTO();
		application.setPosition(new Position());
		application.getPosition().setId(1L);
		application.setFirstName("Customer");
		application.setLastName("Ivanov");
		application.setEmail("customer@gmail.com");
		application.setCvUrl("https://cdn.cvplaces.com/sdfsdfsdfsdf");

		Set<JobApplicationFieldDTO> fields = new HashSet<>();
		JobApplicationFieldDTO field = new JobApplicationFieldDTO();
		field.setFieldId(1L);
		field.setValue("996777665544");
		fields.add(field);

		application.setFields(fields);

		String contentBody = new ObjectMapper().writeValueAsString(application);

		JobApplication returnApp = new JobApplication();
		returnApp.setId(1L);

		BDDMockito.given(applicationService.addApplication(BDDMockito.any())).willReturn(returnApp);

		mvc.perform(post("/api/application/1/add").contentType(MediaType.APPLICATION_JSON).content(contentBody))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").value(1L));
	}

	@Test
	public void testAddApplicationParameterRequired() throws Exception {
		JobApplicationDTO application = new JobApplicationDTO();
		application.setPosition(new Position());
		application.getPosition().setId(1L);
		application.setFirstName("Customer");
		application.setLastName("Ivanov");
		application.setEmail("customer@gmail.com");
		application.setCvUrl("https://cdn.cvplaces.com/sdfsdfsdfsdf");

		Set<JobApplicationFieldDTO> fields = new HashSet<>();

		application.setFields(fields);

		String contentBody = new ObjectMapper().writeValueAsString(application);

		mvc.perform(post("/api/application/1/add").contentType(MediaType.APPLICATION_JSON).content(contentBody))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFilterApplications() throws Exception {
		List<JobApplication> applications = new ArrayList<>();
		JobApplication application = new JobApplication();
		application.setId(1L);
		application.setFirstName("George");
		applications.add(application);

		BDDMockito.given(applicationService.filterApplications(BDDMockito.any(), BDDMockito.any()))
				.willReturn(applications);

		mvc.perform(get("/api/application/1/filter").content("firstName=Aibek")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].firstName").value("George"));
	}

	@Test
	public void testSearchApplications() throws Exception {
		List<JobApplication> applications = new ArrayList<>();
		JobApplication application = new JobApplication();
		application.setId(1L);
		application.setFirstName("George");
		application.setLastName("Mendez");
		applications.add(application);

		application = new JobApplication();
		application.setId(2L);
		application.setFirstName("Paul");
		application.setLastName("Georgeson");
		applications.add(application);

		BDDMockito.given(applicationService.searchApplications(1L, "eorge")).willReturn(applications);

		mvc.perform(get("/api/application/1/search?text=eorge")).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].firstName").value("George"))
				.andExpect(jsonPath("$[1].lastName").value("Georgeson"));
	}

}
