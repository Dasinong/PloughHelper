package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.TaskDao;
import com.dasinong.ploughHelper.model.Task;

public class TaskBo {
	TaskDao taskDao;

	public void setTaskDao(TaskDao taskDao){
		this.taskDao = taskDao;
	}
	
	public void save(Task task) {
		taskDao.save(task);
	}


	public void update(Task task) {
		taskDao.update(task);
	}

	public void delete(Task task) {
		taskDao.delete(task);
	}

	public Task findByTaskName(String taskName) {
		return taskDao.findByTaskName(taskName);
	}

}
