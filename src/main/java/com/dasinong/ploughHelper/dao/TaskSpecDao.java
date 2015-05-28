package com.dasinong.ploughHelper.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.TaskSpec;

public class TaskSpecDao extends HibernateDaoSupport{

	public void save(TaskSpec taskSpec) {
		getHibernateTemplate().save(taskSpec);
	}


	public void update(TaskSpec taskSpec) {
		getHibernateTemplate().update(taskSpec);

	}

	public void delete(TaskSpec taskSpec) {
		getHibernateTemplate().delete(taskSpec);
	}

	public TaskSpec findByTaskSpecName(String taskSpecName) {
		List list = getHibernateTemplate().find(
				"from TaskSpec where taskSpecName=?",taskSpecName);
		return (TaskSpec) list.get(0);
	}

}
