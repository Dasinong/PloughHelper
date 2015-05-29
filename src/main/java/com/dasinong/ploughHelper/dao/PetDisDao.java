package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.PetDis;

public class PetDisDao extends HibernateDaoSupport{
	public void save(PetDis petDis) {
		getHibernateTemplate().save(petDis);
	}


	public void update(PetDis petDis) {
		getHibernateTemplate().update(petDis);

	}

	public void delete(PetDis petDis) {
		getHibernateTemplate().delete(petDis);
	}

	public PetDis findByPetDisName(String petDisName) {
		List list = getHibernateTemplate().find(
				"from PetDis where petDisName=?",petDisName);
		return (PetDis) list.get(0);
	}

}
