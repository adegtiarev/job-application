package kg.aios.application.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import kg.aios.application.dao.ApplicationDAO;
import kg.aios.application.model.JobApplication;
import kg.aios.application.model.JobApplicationField;
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
	public List<JobApplication> filterApplications(JobApplication application, Map<Long, String> filterFields) {
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

		List<JobApplication> applications = applicationDao.findAll(spec);

		Iterator<JobApplication> appIterator = applications.iterator();
		while (appIterator.hasNext()) {
			JobApplication app = appIterator.next();

			boolean remove = false;
			for (JobApplicationField field : app.getFields()) {
				if (filterFields.containsKey(field.getField().getId())
						&& !field.getValue().equals(filterFields.get(field.getField().getId()))) {
					remove = true;
					break;
				}
			}

			if (remove) {
				appIterator.remove();
			}
		}

		return applications;
	}
	
	@Transactional
	public List<JobApplication> searchApplications(Long companyId, String searchText) {
		Specification<JobApplication> spec = JobApplicationSpec.byCompanyId(companyId);
		if (searchText != null && searchText.length() > 3) {
			spec = spec.and(JobApplicationSpec.likeText(searchText));
		}
		
		List<JobApplication> applications = applicationDao.findAll(spec);
		
		return applications;
	}

	@Transactional
	public JobApplication addApplication(JobApplication application) {
		JobApplication addedApplication = applicationDao.save(application);

		return addedApplication;
	}

}
