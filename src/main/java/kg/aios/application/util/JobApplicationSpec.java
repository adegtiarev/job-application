package kg.aios.application.util;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import kg.aios.application.model.Company;
import kg.aios.application.model.JobApplication;
import kg.aios.application.model.JobApplicationField;
import kg.aios.application.model.Position;

public class JobApplicationSpec {
	public static Specification<JobApplication> byCompanyId(Long companyId) {
		return (root, query, criteriaBuilder) -> {
			Join<JobApplication, Company> joinCompany = root.join("company");
			return criteriaBuilder.equal(joinCompany.get("id"), companyId);
		};
	}

	public static Specification<JobApplication> byPositionId(Long positionId) {
		return (root, query, criteriaBuilder) -> {
			Join<JobApplication, Position> joinPosition = root.join("position");
			return criteriaBuilder.equal(joinPosition.get("id"), positionId);
		};
	}

	public static Specification<JobApplication> byFirstName(String firstName) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("firstName"), firstName);
		};
	}

	public static Specification<JobApplication> byLastName(String lastName) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("lastName"), lastName);
		};
	}

	public static Specification<JobApplication> byEmail(String email) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("email"), email);
		};
	}

	public static Specification<JobApplication> likeText(String text) {
		return (root, query, criteriaBuilder) -> {
			Join<JobApplication, Position> joinPosition = root.join("position");
			
			String likeText = "%" + text + "%";
			
			Join<JobApplication, JobApplicationField> joinFields = root.join("fields");
			
			return criteriaBuilder.or(criteriaBuilder.like(joinPosition.get("description"), likeText),
					criteriaBuilder.like(root.get("firstName"), likeText),
					criteriaBuilder.like(root.get("lastName"), likeText),
					criteriaBuilder.like(root.get("email"), likeText),
					criteriaBuilder.like(joinFields.get("value"), likeText));
		};
	}
}
