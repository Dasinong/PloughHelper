package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Location;

public class LocationDao extends HibernateDaoSupport{
	public void save(Location location) {
		getHibernateTemplate().save(location);
	}


	public void update(Location location) {
		getHibernateTemplate().update(location);

	}

	public void delete(Location location) {
		getHibernateTemplate().delete(location);
	}

	public Location findByLocationName(String locationName) {
		List list = getHibernateTemplate().find(
				"from Location where locationName=?",locationName);
		return (Location) list.get(0);
	}

}
