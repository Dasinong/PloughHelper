package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.User;

public class LocationDao extends HibernateDaoSupport implements ILocationDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#save(com.dasinong.ploughHelper.model.Location)
	 */
	@Override
	public void save(Location location) {
		getHibernateTemplate().save(location);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#update(com.dasinong.ploughHelper.model.Location)
	 */
	@Override
	public void update(Location location) {
		getHibernateTemplate().update(location);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#delete(com.dasinong.ploughHelper.model.Location)
	 */
	@Override
	public void delete(Location location) {
		getHibernateTemplate().delete(location);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#findByLocationName(java.lang.String)
	 */
	@Override
	public Location findByLocationName(String locationName) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find(
				"from Location where locationName=?",locationName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Location) list.get(0);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#findById(java.lang.Long)
	 */
	@Override
	public Location findById(Long id) {
		return (Location) this.getHibernateTemplate().get(Location.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#getIdList(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getIdList(String province,String city, String country,String district) {
		return getHibernateTemplate().find(
				"from Location where province=? and city=? and country=? and district=?",province,city,country,district);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ILocationDao#getAll()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getAll() {
		return getHibernateTemplate().find("from Location");
	}

}
