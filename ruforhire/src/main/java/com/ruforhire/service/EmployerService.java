/**
 * 
 */
package com.ruforhire.service;

import java.util.List;

import com.ruforhire.model.Employer;

/**
 * @author ashish
 *
 */
public interface EmployerService {
	public void addEmployer(Employer c);
	public void updateEmployer(Employer c);
	public List<Employer> listEmployers();
	public Employer getEmployerById(int id);
	public Employer findEmployerByName(String name);
	public void removeEmployer(int id);
}
