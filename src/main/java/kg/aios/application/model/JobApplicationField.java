package kg.aios.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "jba_application_fields", uniqueConstraints = @UniqueConstraint(columnNames = { "company_field_id", "application_id" }))
@SequenceGenerator(name = "job_application_field_seq", sequenceName = "seq_job_application_fields")
public class JobApplicationField {

	@Id
	@GeneratedValue(generator = "job_application_field_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "company_field_id")
	private CompanyField field;

	@Column(name = "value")
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompanyField getField() {
		return field;
	}

	public void setField(CompanyField field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
