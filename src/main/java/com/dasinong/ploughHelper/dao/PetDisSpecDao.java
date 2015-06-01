package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.PetDisSpec;

public class PetDisSpecDao extends HibernateDaoSupport{
	public void save(PetDisSpec petDisSpec) {
		getHibernateTemplate().save(petDisSpec);
	}


	public void update(PetDisSpec petDisSpec) {
		getHibernateTemplate().update(petDisSpec);

	}

	public void delete(PetDisSpec petDisSpec) {
		getHibernateTemplate().delete(petDisSpec);
	}

	public PetDisSpec findByPetDisName(String petDisSpecName) {
		List list = getHibernateTemplate().find(
				"from PetDisSpec where petDisSpecName=?",petDisSpecName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (PetDisSpec) list.get(0);
	}

}
