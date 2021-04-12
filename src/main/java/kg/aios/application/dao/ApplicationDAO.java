package kg.aios.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kg.aios.application.model.JobApplication;

@Repository
public interface ApplicationDAO extends JpaRepository<JobApplication, Long>, JpaSpecificationExecutor<JobApplication>{
	
	List<JobApplication> findByCompanyId(Long companyId);
	
}
