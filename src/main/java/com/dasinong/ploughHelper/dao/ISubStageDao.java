package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.SubStage;

public interface ISubStageDao extends IEntityDao<SubStage> {

	public abstract SubStage findBySubStageName(String subStageName);

}