package com.dasinong.ploughHelper.dao;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.Task;

public class TaskDao extends HibernateDaoSupport implements ITaskDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskDao#save(com.dasinong.ploughHelper.model.Task)
	 */
	@Override
	public void save(Task task) {
		getHibernateTemplate().save(task);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskDao#update(com.dasinong.ploughHelper.model.Task)
	 */
	@Override
	public void update(Task task) {
		getHibernateTemplate().update(task);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskDao#delete(com.dasinong.ploughHelper.model.Task)
	 */
	@Override
	public void delete(Task task) {
		getHibernateTemplate().delete(task);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskDao#findByTaskName(java.lang.String)
	 */
	@Override
	public Task findByTaskName(String taskName) {
		List list = getHibernateTemplate().find(
				"from Task where taskName=?",taskName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Task) list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskDao#findById(java.lang.Long)
	 */
	@Override
	public Task findById(Long id) {
		return (Task) this.getHibernateTemplate().get(Task.class,id);
	}

}
