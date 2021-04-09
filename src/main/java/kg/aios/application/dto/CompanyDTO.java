package kg.aios.application.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class CompanyDTO {
	private Long id;
	private String name;
	@JsonBackReference
	private List<CompanyFieldDTO> fields;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CompanyFieldDTO> getFields() {
		return fields;
	}

	public void setFields(List<CompanyFieldDTO> fields) {
		this.fields = fields;
	}

}
