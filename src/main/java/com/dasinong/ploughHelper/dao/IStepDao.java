package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Step;

public interface IStepDao extends IEntityDao<Step> {

	public abstract Step findByStepName(String stepName);

	List<Step> findByTaskSpecId(Long taskSpecId);

}