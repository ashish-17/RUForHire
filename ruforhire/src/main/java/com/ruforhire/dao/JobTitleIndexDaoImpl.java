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

import com.ruforhire.model.JobTitleIndex;

/**
 * @author ashish
 *
 */
@Repository
@Transactional
public class JobTitleIndexDaoImpl implements JobTitleIndexDao {

	private static final Logger logger = LoggerFactory.getLogger(JobTitleIndexDaoImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.JobTitleIndexDao#addJobTitle(com.ruforhire.model.JobTitleIndex)
	 */
	@Override
	public void addJobTitle(JobTitleIndex jobTitle) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(jobTitle);
		logger.info("Job title saved - " + jobTitle);	
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.JobTitleIndexDao#updateJobTitle(com.ruforhire.model.JobTitleIndex)
	 */
	@Override
	public void updateJobTitle(JobTitleIndex jobTitle) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(jobTitle);
		logger.info("Job title updated - " + jobTitle);	
	}

	/* (non-Javadoc)
	 * @see com.ruforhire.dao.JobTitleIndexDao#listPopulerJobTitles()
	 */
	@Override
	public List<JobTitleIndex> listPopulerJobTitles() {
		Session session = this.sessionFactory.getCurrentSession();
        List<JobTitleIndex> jobTitleList = session.createQuery("FROM JobTitleIndex WHERE isMostPopular is true").list();
        for(JobTitleIndex jobTitle : jobTitleList){
            logger.info("Job Title List::"+jobTitle);
        }
        
        return jobTitleList;
	}

	@Override
	public long getCount() {
		Session session = this.sessionFactory.getCurrentSession();
		long count = (long)session.createQuery("SELECT COUNT(j) FROM JobTitleIndex j").uniqueResult();
		return count;
	}

}
