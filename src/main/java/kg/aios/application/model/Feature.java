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

@Entity
@Table(name = "jba_features")
@SequenceGenerator(name = "jba_features_seq", sequenceName = "seq_jba_features", initialValue = 4)
public class Feature {

	@Id
	@GeneratedValue(generator = "jba_companies_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@Column(name = "bean_name")
	private String beanName;

	@Column(name = "feature_json", length = 1000)
	private String featureJson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getFeatureJson() {
		return featureJson;
	}

	public void setFeatureJson(String featureJson) {
		this.featureJson = featureJson;
	}

}
