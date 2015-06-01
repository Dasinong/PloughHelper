package com.dasinong.ploughHelper.dao;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.Step;

public class StepDao extends HibernateDaoSupport{

	public void save(Step step) {
		getHibernateTemplate().save(step);
	}


	public void update(Step step) {
		getHibernateTemplate().update(step);

	}

	public void delete(Step step) {
		getHibernateTemplate().delete(step);
	}

	public Step findByStepName(String stepName) {
		List list = getHibernateTemplate().find(
				"from Step where stepName=?",stepName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Step) list.get(0);
	}

}
