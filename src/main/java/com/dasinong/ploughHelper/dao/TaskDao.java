package com.dasinong.ploughHelper.dao;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.Task;

public class TaskDao extends HibernateDaoSupport{

	public void save(Task task) {
		getHibernateTemplate().save(task);
	}


	public void update(Task task) {
		getHibernateTemplate().update(task);

	}

	public void delete(Task task) {
		getHibernateTemplate().delete(task);
	}

	public Task findByTaskName(String taskName) {
		List list = getHibernateTemplate().find(
				"from Task where taskName=?",taskName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Task) list.get(0);
	}

}
