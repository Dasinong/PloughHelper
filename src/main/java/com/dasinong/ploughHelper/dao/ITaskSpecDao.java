package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dasinong.ploughHelper.model.TaskSpec;

public interface ITaskSpecDao {

	public abstract SessionFactory getSessionFactory();

	public abstract void setSessionFactory(SessionFactory sessionFactory);

	public abstract void save(TaskSpec taskSpec);

	public abstract void update(TaskSpec taskSpec);

	public abstract void delete(TaskSpec taskSpec);

	public abstract TaskSpec findByTaskSpecName(String taskSpecName);

	public abstract TaskSpec findById(Long id);

	List<TaskSpec> findBySubstage(Long subStageId);

}