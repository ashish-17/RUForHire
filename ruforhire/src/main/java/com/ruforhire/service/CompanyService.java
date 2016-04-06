/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import com.ruforhire.model.Company;

/**
 * @author ashish
 *
 */
public interface CompanyService {
	public void addCompany(Company c);
	public void updateCompany(Company c);
	public List<Company> listCompanies();
	public Company getCompanyById(int id);
	public void removeCompany(int id);
}