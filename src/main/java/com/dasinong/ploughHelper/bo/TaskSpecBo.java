package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.TaskSpecDao;
import com.dasinong.ploughHelper.model.TaskSpec;

public class TaskSpecBo {
	TaskSpecDao taskSpecDao;

	public void setTaskSpecDao(TaskSpecDao taskSpecDao){
		this.taskSpecDao = taskSpecDao;
	}
	
	public void save(TaskSpec taskSpec) {
		taskSpecDao.save(taskSpec);
	}


	public void update(TaskSpec taskSpec) {
		taskSpecDao.update(taskSpec);
	}

	public void delete(TaskSpec taskSpec) {
		taskSpecDao.delete(taskSpec);
	}

	public TaskSpec findByTaskSpecName(String taskSpecName) {
		return taskSpecDao.findByTaskSpecName(taskSpecName);
	}

}
