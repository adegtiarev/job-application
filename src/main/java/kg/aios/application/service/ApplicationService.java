package kg.aios.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import kg.aios.application.dao.ApplicationDAO;
import kg.aios.application.model.JobApplication;
import kg.aios.application.util.JobApplicationSpec;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationDAO applicationDao;

	@Transactional
	public List<JobApplication> findAllApplications() {
		return applicationDao.findAll();
	}

	@Transactional
	public List<JobApplication> findApplicationsForCompany(Long companyId) {
		return applicationDao.findByCompanyId(companyId);
	}

	@Transactional
	public List<JobApplication> searchApplications(JobApplication application) {
		Specification<JobApplication> spec = JobApplicationSpec.byCompanyId(application.getCompany().getId());
		if (application.getPosition() != null && application.getPosition().getId() != null) {
			spec = spec.and(JobApplicationSpec.byPositionId(application.getPosition().getId()));
		}
		if (application.getFirstName() != null) {
			spec = spec.and(JobApplicationSpec.byFirstName(application.getFirstName()));
		}
		if (application.getLastName() != null) {
			spec = spec.and(JobApplicationSpec.byLastName(application.getLastName()));
		}
		if (application.getEmail() != null) {
			spec = spec.and(JobApplicationSpec.byEmail(application.getEmail()));
		}

		return applicationDao.findAll(spec);
	}

	@Transactional
	public JobApplication addApplication(JobApplication application) {
		JobApplication addedApplication = applicationDao.save(application);

		return addedApplication;
	}

}
