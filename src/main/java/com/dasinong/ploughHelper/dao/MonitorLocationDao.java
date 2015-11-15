package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.MonitorLocation;

public class MonitorLocationDao extends EntityHibernateDao<MonitorLocation>implements IMonitorLocationDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dasinong.ploughHelper.dao.ILocationDao#findByLocationName(java.lang.
	 * String)
	 */
	@Override
	public MonitorLocation findByCode(int code) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find("from MonitorLocation where code=?", code);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return (MonitorLocation) list.get(0);
	}

}
