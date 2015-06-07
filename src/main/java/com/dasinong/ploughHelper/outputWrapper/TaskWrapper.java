package com.dasinong.ploughHelper.outputWrapper;

import java.io.Serializable;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.TaskSpecDao;
import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.TaskSpec;

//This class wraps what supposed to be seen by the view
public class TaskWrapper implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long taskId;
	private Long taskSpecId;
	private Long fieldId;
	private String taskSpecName;
	private boolean taskStatus;
	
	public TaskWrapper(Task task){
	   this.setTaskId(task.getTaskId());
	   this.setTaskSpecId(task.getTaskSpecId());
	   this.setFieldId(task.getFieldId());
	   this.setTaskStatus(task.getTaskStatus());
	   TaskSpecDao taskSpecDao = (TaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
	   TaskSpec taskspec = taskSpecDao.findById(task.getTaskSpecId());
	   this.setTaskSpecName(taskspec.getTaskSpecName());
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getTaskSpecId() {
		return taskSpecId;
	}

	public void setTaskSpecId(Long taskSpecId) {
		this.taskSpecId = taskSpecId;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getTaskSpecName() {
		return taskSpecName;
	}

	public void setTaskSpecName(String taskSpecName) {
		this.taskSpecName = taskSpecName;
	}

	public boolean isTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}
	
}
