package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.VarietyBrowse;

public interface IVarietyBrowseDao {

	void save(VarietyBrowse varietyBrowse);

	VarietyBrowse findByVarietyBrowseName(String varietyBrowseName);

	VarietyBrowse findById(Long id);

	List<VarietyBrowse> findByCropId(Long id);

}
