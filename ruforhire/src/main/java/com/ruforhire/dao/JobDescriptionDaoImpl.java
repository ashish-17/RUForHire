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
import org.springframework.transaction.annotation.Transactional;

import com.ruforhire.model.JobDescription;

/**
 * @author ashish
 *
 */
@Repository
@Transactional
public class JobDescriptionDaoImpl implements JobDescriptionDao {

	private static final Logger logger = LoggerFactory.getLogger(JobDescriptionDaoImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@Override
	public void addJobDescription(JobDescription jd) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(jd);
		logger.info("Job description saved - " + jd);		
	}

	@Override
	public void updateJobDescription(JobDescription jd) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(jd);
		logger.info("Job description updated - " + jd);		
	}

	@Override
	public List<JobDescription> listJobDescriptions() {
		Session session = this.sessionFactory.getCurrentSession();
        List<JobDescription> jobList = session.createQuery("from JobDescription").list();
        for(JobDescription jd : jobList){
            logger.info("JobDescription List::"+jd);
        }
        
        return jobList;
	}

	@Override
	public JobDescription getJobDescriptionById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		JobDescription jd = (JobDescription) session.load(JobDescription.class, new Integer(id));
        logger.info("Jobs loaded successfully, details="+jd);
        return jd;
	}

	@Override
	public void removeJobDescription(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		JobDescription jd = (JobDescription) session.load(JobDescription.class, new Integer(id));
        if(null != jd){
            session.delete(jd);
        }
        
        logger.info("Job deleted successfully, Job details="+jd);		
	}
}
