package com.dasinong.ploughHelper.dao;

import java.util.Map;

import com.dasinong.ploughHelper.model.QualityItemValue;

public interface IQualityItemValueDao {

	public abstract void save(QualityItemValue qualityItemValue);

	public abstract void update(QualityItemValue qualityItemValue);

	public abstract void delete(QualityItemValue qualityItemValue);

	public abstract Map<Long, String> findByVarietyId(Long varietyId);

}