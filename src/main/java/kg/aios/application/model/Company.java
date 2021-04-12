package kg.aios.application.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "jba_companies")
@SequenceGenerator(name = "jba_companies_seq", sequenceName = "seq_jba_companies", initialValue = 3)
public class Company {

	@Id
	@GeneratedValue(generator = "jba_companies_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "name", length = 120)
	private String name;

	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<CompanyField> fields;

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

	public List<CompanyField> getFields() {
		return fields;
	}

	public void setFields(List<CompanyField> fields) {
		this.fields = fields;
	}

}
