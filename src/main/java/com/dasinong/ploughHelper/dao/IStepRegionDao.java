package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.StepRegion;

public interface IStepRegionDao extends IEntityDao<StepRegion> {

	List<StepRegion> findByStepRegion(String region);

}
