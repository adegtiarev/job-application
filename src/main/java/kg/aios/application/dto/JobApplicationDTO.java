package kg.aios.application.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import kg.aios.application.model.Position;

public class JobApplicationDTO {
	private Long id;
	@NotNull(message = "Position is required")
	private Position position;
	@NotEmpty(message = "FistName field is required")
	private String firstName;
	@NotEmpty(message = "LastName field is required")
	private String lastName;
	@NotNull(message = "Email field is required")
	@Email(message = "Enter valid email")
	private String email;
	private String cvUrl;

	private Set<JobApplicationFieldDTO> fields;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCvUrl() {
		return cvUrl;
	}

	public void setCvUrl(String cvUrl) {
		this.cvUrl = cvUrl;
	}

	public Set<JobApplicationFieldDTO> getFields() {
		return fields;
	}

	public void setFields(Set<JobApplicationFieldDTO> fields) {
		this.fields = fields;
	}

}
