package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.NatDis;

public class NatDisDao extends HibernateDaoSupport{
	public void save(NatDis natDis) {
		getHibernateTemplate().save(natDis);
	}


	public void update(NatDis natDis) {
		getHibernateTemplate().update(natDis);

	}

	public void delete(NatDis natDis) {
		getHibernateTemplate().delete(natDis);
	}

	public NatDis findByNatDisName(String natDisName) {
		List list = getHibernateTemplate().find(
				"from NatDis where natDisName=?",natDisName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (NatDis) list.get(0);
	}

}
