/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruforhire.dao.CompanyDao;
import com.ruforhire.model.Company;

/**
 * @author ashish
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {
	private CompanyDao companyDao;

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruforhire.service.CompanyService#addCompany(com.ruforhire.model.
	 * Company)
	 */
	@Override
	@Transactional
	public void addCompany(Company c) {
		companyDao.addCompany(c);
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
	public void updateCompany(Company c) {
		companyDao.updateCompany(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruforhire.service.CompanyService#listCompanies()
	 */
	@Override
	@Transactional
	public List<Company> listCompanies() {
		return companyDao.listCompanies();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruforhire.service.CompanyService#getCompanyById(int)
	 */
	@Override
	@Transactional
	public Company getCompanyById(int id) {
		return companyDao.getCompanyById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruforhire.service.CompanyService#removeCompany(int)
	 */
	@Override
	@Transactional
	public void removeCompany(int id) {
		companyDao.removeCompany(id);
	}
}
