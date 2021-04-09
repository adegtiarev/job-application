package kg.aios.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kg.aios.application.model.Company;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {

}
