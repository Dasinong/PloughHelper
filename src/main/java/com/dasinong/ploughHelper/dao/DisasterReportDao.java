/**
 * 
 */
package com.dasinong.ploughHelper.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.DisasterReport;

/**
 * @author Dell
 *
 */
public class DisasterReportDao extends HibernateDaoSupport implements
		IDisasterReportDao {

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IDisasterReport#save(com.dasinong.ploughHelper.model.DisasterReport)
	 */
	@Override
	public void save(DisasterReport disasterReport) {
		getHibernateTemplate().save(disasterReport);		
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IDisasterReport#update(com.dasinong.ploughHelper.model.DisasterReport)
	 */
	@Override
	public void update(DisasterReport disasterReport) {
		getHibernateTemplate().update(disasterReport);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IDisasterReport#delete(com.dasinong.ploughHelper.model.DisasterReport)
	 */
	@Override
	public void delete(DisasterReport disasterReport) {
		getHibernateTemplate().delete(disasterReport);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IDisasterReport#findByDisasterName(java.lang.String)
	 */
	@Override
	public DisasterReport findByDisasterName(String disasterName) {
		// TODO Auto-generated method stub
		return null;
	}

}
