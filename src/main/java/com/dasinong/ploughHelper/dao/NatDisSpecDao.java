package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.NatDisSpec;
import com.dasinong.ploughHelper.model.Task;

public class NatDisSpecDao extends HibernateDaoSupport{
	public void save(NatDisSpec natDisSpec) {
		getHibernateTemplate().save(natDisSpec);
	}


	public void update(NatDisSpec natDisSpec) {
		getHibernateTemplate().update(natDisSpec);

	}

	public void delete(NatDisSpec natDisSpec) {
		getHibernateTemplate().delete(natDisSpec);
	}

	public NatDisSpec findByNatDisSpecName(String natDisSpecName) {
		List list = getHibernateTemplate().find(
				"from NatDisSpec where natDisSpecName=?",natDisSpecName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (NatDisSpec) list.get(0);
	}

	public NatDisSpec findById(Long id) {
		return (NatDisSpec) this.getHibernateTemplate().get(NatDisSpec.class,id);
	}
}
