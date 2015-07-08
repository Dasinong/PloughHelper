package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.dasinong.ploughHelper.model.TaskSpec;

public class TaskSpecDao implements ITaskSpecDao{
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskSpecDao#getSessionFactory()
	 */
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskSpecDao#setSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskSpecDao#save(com.dasinong.ploughHelper.model.TaskSpec)
	 */
	@Override
	public void save(TaskSpec taskSpec) {
		this.getSessionFactory().getCurrentSession().save(taskSpec);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskSpecDao#update(com.dasinong.ploughHelper.model.TaskSpec)
	 */
	@Override
	public void update(TaskSpec taskSpec) {
		this.getSessionFactory().getCurrentSession().update(taskSpec);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskSpecDao#delete(com.dasinong.ploughHelper.model.TaskSpec)
	 */
	@Override
	public void delete(TaskSpec taskSpec) {
		this.getSessionFactory().getCurrentSession().delete(taskSpec);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskSpecDao#findByTaskSpecName(java.lang.String)
	 */
	@Override
	public TaskSpec findByTaskSpecName(String taskSpecName) {
		List list = this.getSessionFactory().getCurrentSession().createQuery(
				"from TaskSpec where taskSpecName=:specName").setString("specName", taskSpecName).list();
		if (list==null||list.isEmpty()){
			return null;
		}
		return (TaskSpec) list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ITaskSpecDao#findById(java.lang.Long)
	 */
	@Override
	@Transactional
	public TaskSpec findById(Long id) {
		TaskSpec spec= (TaskSpec) this.getSessionFactory().getCurrentSession().get(TaskSpec.class,id);
		spec.getSubStage().getDurationHigh();
		return spec;
	}
	
	@Override
	public List<TaskSpec> findBySubstage(Long subStageId){
		List list = this.getSessionFactory().getCurrentSession().createQuery(
				"from TaskSpec where subStageId=:subStageId").setLong("subStageId", subStageId).list();
		if (list==null||list.isEmpty()){
			return null;
		}
		return list;
	}

}
