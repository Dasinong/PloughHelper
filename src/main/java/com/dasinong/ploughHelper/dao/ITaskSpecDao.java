package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dasinong.ploughHelper.model.TaskSpec;

public interface ITaskSpecDao extends IEntityDao<TaskSpec> {
	
	public abstract TaskSpec findByTaskSpecName(String taskSpecName);

	List<TaskSpec> findBySubstage(Long subStageId);

}