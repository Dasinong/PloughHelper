package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.Task;

public interface ITaskDao {

	public abstract void save(Task task);

	public abstract void update(Task task);

	public abstract void delete(Task task);

	public abstract Task findByTaskName(String taskName);

	public abstract Task findById(Long id);

}