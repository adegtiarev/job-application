package kg.aios.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kg.aios.application.model.Feature;

@Repository
public interface FeatureDAO extends JpaRepository<Feature, Long> {
	List<Feature> findByCompanyId(Long companyId);
}
