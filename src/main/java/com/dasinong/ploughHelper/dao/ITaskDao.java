package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.Task;

public interface ITaskDao extends IEntityDao<Task> {

	public abstract Task findByTaskName(String taskName);

}