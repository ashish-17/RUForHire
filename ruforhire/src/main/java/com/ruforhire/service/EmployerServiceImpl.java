/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruforhire.dao.EmployerDao;
import com.ruforhire.model.Employer;

/**
 * @author ashish
 *
 */
@Service
public class EmployerServiceImpl implements EmployerService {
	private EmployerDao employerDao;

	public void setEmployerDao(EmployerDao employerDao) {
		this.employerDao = employerDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruforhire.service.CompanyService#addCompany(com.ruforhire.model.
	 * Company)
	 */
	@Override
	@Transactional
	public void addEmployer(Employer c) {
		employerDao.addEmployer(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruforhire.service.CompanyService#updateCompany(com.ruforhire.model.
	 * Company)
	 */
	@Override
	@Transactional
	public void updateEmployer(Employer c) {
		employerDao.updateEmployer(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruforhire.service.CompanyService#listCompanies()
	 */
	@Override
	@Transactional
	public List<Employer> listEmployers() {
		return employerDao.listEmployers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruforhire.service.CompanyService#getCompanyById(int)
	 */
	@Override
	@Transactional
	public Employer getEmployerById(int id) {
		return employerDao.getEmployerById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruforhire.service.CompanyService#removeCompany(int)
	 */
	@Override
	@Transactional
	public void removeEmployer(int id) {
		employerDao.removeEmployer(id);
	}

	@Override
	public Employer findEmployerByName(String name) {
		return employerDao.findEmployerByName(name);
	}
}
