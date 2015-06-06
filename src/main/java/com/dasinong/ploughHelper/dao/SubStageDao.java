package com.dasinong.ploughHelper.dao;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.Variety;

public class SubStageDao extends HibernateDaoSupport{

	public void save(SubStage subStage) {
		getHibernateTemplate().save(subStage);
	}


	public void update(SubStage subStage) {
		getHibernateTemplate().update(subStage);

	}

	public void delete(SubStage subStage) {
		getHibernateTemplate().delete(subStage);
	}

	public SubStage findBySubStageName(String subStageName) {
		List list = getHibernateTemplate().find(
				"from SubStage where subStageName=?",subStageName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (SubStage) list.get(0);
	}

	
	public SubStage findById(Long id) {
		return (SubStage) this.getHibernateTemplate().get(SubStage.class,id);
	}
}
