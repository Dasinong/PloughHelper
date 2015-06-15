package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.SubStage;

public interface ISubStageDao {

	public abstract void save(SubStage subStage);

	public abstract void update(SubStage subStage);

	public abstract void delete(SubStage subStage);

	public abstract SubStage findBySubStageName(String subStageName);

	public abstract SubStage findById(Long id);

}