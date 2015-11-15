package com.dasinong.ploughHelper.dao;

import java.util.Map;

import com.dasinong.ploughHelper.model.QualityItemValue;

public interface IQualityItemValueDao extends IEntityDao<QualityItemValue> {

	public abstract Map<Long, String> findByVarietyId(Long varietyId);

}