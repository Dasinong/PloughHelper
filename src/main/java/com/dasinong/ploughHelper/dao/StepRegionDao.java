package com.dasinong.ploughHelper.dao;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.StepRegion;

public class StepRegionDao extends HibernateDaoSupport implements IStepRegionDao{
	
	@Override
	public List<StepRegion> findByStepRegion(String region) {
		List<StepRegion> list = getHibernateTemplate().find(
				"from StepRegion where region=?",region);
		if (list==null||list.isEmpty()){
			return null;
		}
		return list;
	}
	
}