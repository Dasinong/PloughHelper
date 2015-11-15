package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.User;

public class LocationDao extends EntityHibernateDao<Location>implements ILocationDao {

	@Override
	public Location findByLocationName(String locationName) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find("from Location where locationName=?", locationName);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return (Location) list.get(0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getIdList(String province, String city, String country, String district) {
		return getHibernateTemplate().find("from Location where province=? and city=? and country=? and district=?",
				province, city, country, district);
	}

	@Override
	public List<Location> findLocationsInRange(double lat, double lon, double range) {
		return this.getHibernateTemplate().find("from Location where latitude < " + (lat + range) + " and latitude > "
				+ (lat - range) + " and longtitude < " + (lon + range) + " and longtitude > " + (lon - range));
	}

}
