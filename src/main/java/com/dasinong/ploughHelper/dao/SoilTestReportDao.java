package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.SoilTestReport;

@Transactional
public class SoilTestReportDao implements ISoilTestReportDao{
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#getSessionFactory()
	 */
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#setSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#save(com.dasinong.ploughHelper.model.SoilTestReport)
	 */
	@Override
	public void save(SoilTestReport SoilTestReport) {
		this.getSessionFactory().getCurrentSession().save(SoilTestReport);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#update(com.dasinong.ploughHelper.model.SoilTestReport)
	 */
	@Override
	public void update(SoilTestReport SoilTestReport) {
		this.getSessionFactory().getCurrentSession().update(SoilTestReport);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#delete(com.dasinong.ploughHelper.model.SoilTestReport)
	 */
	@Override
	public void delete(SoilTestReport SoilTestReport) {
		this.getSessionFactory().getCurrentSession().delete(SoilTestReport);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#findById(java.lang.Long)
	 */
	@Override
	@Transactional
	public SoilTestReport findById(Long id) {
		SoilTestReport spec= (SoilTestReport) this.getSessionFactory().getCurrentSession().get(SoilTestReport.class,id);
		spec.getAn();
		return spec;
	}

}
