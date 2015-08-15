package com.dasinong.ploughHelper.dao;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.TaskRegion;

public class TaskRegionDao extends HibernateDaoSupport implements ITaskRegionDao{
	
	@Override
	public List<TaskRegion> findByTaskRegion(String region) {
		List<TaskRegion> list = getHibernateTemplate().find(
				"from TaskRegion where region=?",region);
		if (list==null||list.isEmpty()){
			return null;
		}
		return list;
	}
	
}
