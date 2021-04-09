package kg.aios.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kg.aios.application.dao.CompanyDAO;
import kg.aios.application.model.Company;

@Service
public class CompanyService {

	@Autowired
	private CompanyDAO companyDao;

	@Transactional
	public List<Company> findAllCompanies() {
		return companyDao.findAll();
	}

	@Transactional
	public Company findById(Long id) {
		return companyDao.findById(id).get();
	}
}
