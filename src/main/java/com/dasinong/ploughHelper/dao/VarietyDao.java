package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.TaskSpec;
import com.dasinong.ploughHelper.model.Variety;


public class VarietyDao extends HibernateDaoSupport {
	public void save(Variety variety) {
		getHibernateTemplate().save(variety);
	}

	public void update(Variety variety) {
		getHibernateTemplate().update(variety);
	}

	public void delete(Variety variety) {
		getHibernateTemplate().delete(variety);
	}

	public Variety findByVarietyName(String varietyName) {
		List list = getHibernateTemplate().find(
				"from Variety where varietyName=?",varietyName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Variety) list.get(0);
	}
	
	public Variety findById(Long id) {
		return (Variety) this.getHibernateTemplate().get(Variety.class,id);
	}
	
	public List<Variety> findByCropRegion(long cropId, String suitableArea){
		List list = getHibernateTemplate().find(
				"from Variety where cropId=? and suitableArea like '%"+suitableArea + "%'",cropId);
		return list;
	}
}
