package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.NatDis;

public class NatDisDao extends HibernateDaoSupport implements INatDisDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisDao#save(com.dasinong.ploughHelper.model.NatDis)
	 */
	@Override
	public void save(NatDis natDis) {
		getHibernateTemplate().save(natDis);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisDao#update(com.dasinong.ploughHelper.model.NatDis)
	 */
	@Override
	public void update(NatDis natDis) {
		getHibernateTemplate().update(natDis);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisDao#delete(com.dasinong.ploughHelper.model.NatDis)
	 */
	@Override
	public void delete(NatDis natDis) {
		getHibernateTemplate().delete(natDis);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisDao#findByNatDisName(java.lang.String)
	 */
	@Override
	public NatDis findByNatDisName(String natDisName) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find(
				"from NatDis where natDisName=?",natDisName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (NatDis) list.get(0);
	}

}
