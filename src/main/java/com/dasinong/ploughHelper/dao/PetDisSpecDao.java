package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.Task;

public class PetDisSpecDao extends EntityHibernateDao<PetDisSpec> implements IPetDisSpecDao{
	
	@Override
	public PetDisSpec findByPetDisSpecName(String petDisSpecName) {
		List list = getHibernateTemplate().find(
				"from PetDisSpec where petDisSpecName=?",petDisSpecName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (PetDisSpec) list.get(0);
	}

	@Override
	public PetDisSpec findByNameAndCrop(String petDisSpecName, String cropName) {
		List list = getHibernateTemplate().find(
				"from PetDisSpec where petDisSpecName=? and cropName=?",petDisSpecName, cropName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (PetDisSpec) list.get(0);
	}
}