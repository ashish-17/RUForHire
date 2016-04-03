/**
 * 
 */
package com.ruforhire.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ruforhire.model.Company;

/**
 * @author ashish
 *
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#addCompany(com.ruforhire.model.Company)
	 */
	@Override
	public void addCompany(Company c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);
		logger.info("Company saved - " + c);
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#updateCompany(com.ruforhire.model.Company)
	 */
	@Override
	public void updateCompany(Company c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
		logger.info("Company updated - " + c);
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#listCompanies()
	 */
	@Override
	public List<Company> listCompanies() {
		Session session = this.sessionFactory.getCurrentSession();
        List<Company> companyList = session.createQuery("from COMPANY").list();
        for(Company p : companyList){
            logger.info("Company List::"+p);
        }
        
        return companyList;
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#getCompanyById(int)
	 */
	@Override
	public Company getCompanyById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		Company c = (Company) session.load(Company.class, new Integer(id));
        logger.info("Company loaded successfully, Company details="+c);
        return c;
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.CompanyDao#removeCompany(int)
	 */
	@Override
	public void removeCompany(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Company c = (Company) session.load(Company.class, new Integer(id));
        if(null != c){
            session.delete(c);
        }
        
        logger.info("Company deleted successfully, Company details="+c);
	}
}
