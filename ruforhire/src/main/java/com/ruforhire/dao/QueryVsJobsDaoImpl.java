/**
 * 
 */
package com.ruforhire.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ruforhire.model.Employer;
import com.ruforhire.model.JobDescription;
import com.ruforhire.model.QueryVsJobs;

/**
 * @author ashish
 *
 */
@Repository
@Transactional
public class QueryVsJobsDaoImpl implements QueryVsJobsDao {

	private static final Logger logger = LoggerFactory.getLogger(QueryVsJobsDaoImpl.class);
	 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@Override
	public void addQuery(QueryVsJobs q) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(q);
		logger.info("query data saved - " + q);		
	}

	@Override
	public List<JobDescription> getJobsForQuery(String query) {
		Session session = this.sessionFactory.getCurrentSession();
        List<JobDescription> jobs = session.createQuery("SELECT q.jobDescription FROM QueryVsJobs q WHERE q.query=" + query).list();
        for(JobDescription job : jobs){
            logger.info("Job description List::"+job);
        }
        
        return jobs;
	}

	@Override
	public long getCount() {
		Session session = this.sessionFactory.getCurrentSession();
		long count = (long)session.createQuery("SELECT COUNT(q) FROM QueryVsJobs q").uniqueResult();
		return count;
	}

	@Override
	public long getJobCountForQuery(String query) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(QueryVsJobs.class)
									.add(Restrictions.eq("query", query))
									.setProjection(Projections.rowCount());
		
		long count = (long) criteria.uniqueResult();
		return count;
	}

	@Override
	public List<QueryVsJobs> listJobsVsQuery() {
		Session session = this.sessionFactory.getCurrentSession();
        List<QueryVsJobs> queryList = session.createQuery("from QueryVsJobs").list();
        for(QueryVsJobs query : queryList){
            logger.info("Query List::"+query);
        }
        
        return queryList;
	}
}
