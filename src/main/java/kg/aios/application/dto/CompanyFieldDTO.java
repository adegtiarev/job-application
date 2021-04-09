package kg.aios.application.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class CompanyFieldDTO {
	private Long id;
	@JsonManagedReference
	private CompanyDTO company;
	private String name;
	private String type;
	private Boolean required;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

}
