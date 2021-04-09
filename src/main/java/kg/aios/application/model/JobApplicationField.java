package kg.aios.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "jba_application_fields")
@SequenceGenerator(name = "jba_application_fields_seq", sequenceName = "seq_jba_application_fields")
public class JobApplicationField {

	@Id
	@GeneratedValue(generator = "jba_application_fields_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "value")
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
