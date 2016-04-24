/**
 * 
 */
package com.ruforhire.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ruforhire.model.Employer;

/**
 * @author ashish
 *
 */
@Repository
@Transactional
public class EmployerDaoImpl implements EmployerDao {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployerDaoImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#addCompany(com.ruforhire.model.Company)
	 */
	@Override
	public void addEmployer(Employer e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(e);
		logger.info("Employer saved - " + e);
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#updateCompany(com.ruforhire.model.Company)
	 */
	@Override
	public void updateEmployer(Employer c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
		logger.info("Employer updated - " + c);
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#listCompanies()
	 */
	@Override
	public List<Employer> listEmployers() {
		Session session = this.sessionFactory.getCurrentSession();
        List<Employer> employerList = session.createQuery("from EMPLOYER").list();
        for(Employer p : employerList){
            logger.info("Employer List::"+p);
        }
        
        return employerList;
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#getCompanyById(int)
	 */
	@Override
	public Employer getEmployerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		Employer c = (Employer) session.load(Employer.class, new Integer(id));
        logger.info("Employer loaded successfully, Company details="+c);
        return c;
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#removeCompany(int)
	 */
	@Override
	public void removeEmployer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employer c = (Employer) session.load(Employer.class, new Integer(id));
        if(null != c){
            session.delete(c);
        }
        
        logger.info("Employer deleted successfully, Employer details="+c);
	}

	@Override
	public Employer findEmployerByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employer.class)
									.add(Restrictions.eq("name", name));
		
        List<Employer> employerList = criteria.list();
        if (employerList == null || employerList.isEmpty()) {
        	return null;
        } else {
        	return employerList.get(0);
        }
	}
}
