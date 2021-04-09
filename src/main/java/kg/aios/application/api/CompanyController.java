package kg.aios.application.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kg.aios.application.dto.CompanyDTO;
import kg.aios.application.dto.CompanyFieldDTO;
import kg.aios.application.model.Company;
import kg.aios.application.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	private ModelMapper mapper = new ModelMapper();

	@GetMapping("/list")
	public List<CompanyDTO> getCompanies() {
		List<Company> companies = companyService.findAllCompanies();
		
		List<CompanyDTO> dto = mapper.map(companies, new TypeToken<List<CompanyDTO>>(){}.getType());
		
		return dto;
	}
	
	@GetMapping("/{id}/fields")
	public List<CompanyFieldDTO> getCompanyFields(@PathVariable Long id) {
		Company company = companyService.findById(id);
		
		return mapper.map(company.getFields(), new TypeToken<List<CompanyFieldDTO>>(){}.getType());
	}
}
