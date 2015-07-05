package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Step;

public interface IStepDao {

	public abstract void save(Step step);

	public abstract void update(Step step);

	public abstract void delete(Step step);

	public abstract Step findByStepName(String stepName);

	List<Step> findByTaskSpecId(Long taskSpecId);

}