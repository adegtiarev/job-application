package kg.aios.application.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kg.aios.application.dto.JobApplicationDTO;
import kg.aios.application.dto.JobApplicationFieldDTO;
import kg.aios.application.model.Company;
import kg.aios.application.model.CompanyField;
import kg.aios.application.model.JobApplication;
import kg.aios.application.model.JobApplicationField;
import kg.aios.application.model.Position;
import kg.aios.application.service.ApplicationService;
import kg.aios.application.service.CompanyService;
import kg.aios.application.service.FeatureService;
import kg.aios.application.util.ParameterRequiredException;

@RestController
@RequestMapping(value = "/api/application/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private FeatureService featureService;

	private ModelMapper mapper = new ModelMapper();

	@PostMapping("/add")
	public Long addNewApplication(@PathVariable("companyId") Long companyId,
			@Valid @RequestBody JobApplicationDTO application) {
		logger.debug("Add application called for company: {}");

		Company company = companyService.findById(companyId);

		JobApplication newApplication = mapper.map(application, JobApplication.class);
		newApplication.setFields(null);
		newApplication.setCompany(company);

		List<JobApplicationField> applicationFields = new ArrayList<>();

		for (CompanyField field : company.getFields()) {

			JobApplicationFieldDTO fieldDTO = null;
			if (application.getFields() != null) {
				for (JobApplicationFieldDTO fldDTO : application.getFields()) {
					if (field.getId().equals(fldDTO.getFieldId())) {
						fieldDTO = fldDTO;
						break;
					}
				}
			}

			if (field.getRequired() && (fieldDTO == null || fieldDTO.getValue() == null)) {
				throw new ParameterRequiredException("Required parameter or it's value is missing", field.getName());
			}

			if (fieldDTO != null) {
				JobApplicationField applicationField = new JobApplicationField();
				applicationField.setField(field);
				applicationField.setValue(fieldDTO.getValue().toString());

				applicationFields.add(applicationField);
			}
		}
		newApplication.setFields(applicationFields);

		newApplication = applicationService.addApplication(newApplication);

		try {
			featureService.applyFeatures(newApplication);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}

		return newApplication.getId();
	}

	private static final String POSITION_ID = "positionId";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";

	@GetMapping("/filter")
	public List<JobApplicationDTO> filterApplications(@PathVariable("companyId") Long companyId,
			@RequestParam Map<String, String> allParams) {

		JobApplication application = new JobApplication();

		Company company = companyService.findById(companyId);

		application.setCompany(company);

		if (allParams.containsKey(POSITION_ID)) {
			application.setPosition(new Position());
			application.getPosition().setId(Long.valueOf(allParams.get(POSITION_ID)));
		}
		if (allParams.containsKey(FIRST_NAME)) {
			application.setFirstName(allParams.get(FIRST_NAME));
		}
		if (allParams.containsKey(LAST_NAME)) {
			application.setLastName(allParams.get(LAST_NAME));
		}
		if (allParams.containsKey(EMAIL)) {
			application.setEmail(allParams.get(EMAIL));
		}

		Map<Long, String> filterFields = new HashMap<>();
		for (CompanyField companyField : company.getFields()) {
			if (allParams.containsKey(companyField.getName())) {
				filterFields.put(companyField.getId(), allParams.get(companyField.getName()));
			}
		}

		List<JobApplication> applications = applicationService.filterApplications(application, filterFields);

		return mapper.map(applications, new TypeToken<List<JobApplicationDTO>>() {
		}.getType());
	}

	@GetMapping("/search")
	public List<JobApplicationDTO> searchApplications(@PathVariable("companyId") Long companyId,
			@RequestParam("text") String text) {
		List<JobApplication> applications = applicationService.searchApplications(companyId, text);

		return mapper.map(applications, new TypeToken<List<JobApplicationDTO>>() {
		}.getType());
	}
}
