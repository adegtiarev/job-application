package kg.aios.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "jba_position")
@SequenceGenerator(name = "job_position_seq", sequenceName = "seq_job_position", initialValue = 5)
public class Position {
	@Id
	@GeneratedValue(generator = "job_position_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "description", length = 100)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
