package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.MonitorLocation;


public class MonitorLocationDao extends HibernateDaoSupport implements IMonitorLocationDao{
	
	@Override
	public void save(MonitorLocation monitorLocation) {
		getHibernateTemplate().save(monitorLocation);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#update(com.dasinong.ploughHelper.model.Location)
	 */
	@Override
	public void update(MonitorLocation monitorLocation) {
		getHibernateTemplate().update(monitorLocation);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#delete(com.dasinong.ploughHelper.model.Location)
	 */
	@Override
	public void delete(MonitorLocation monitorLocation) {
		getHibernateTemplate().delete(monitorLocation);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#findByLocationName(java.lang.String)
	 */
	@Override
	public MonitorLocation findByCode(int code) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find(
				"from MonitorLocation where code=?",code);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (MonitorLocation) list.get(0);
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getAll() {
		return getHibernateTemplate().find("from MonitorLocation");
	}

}
