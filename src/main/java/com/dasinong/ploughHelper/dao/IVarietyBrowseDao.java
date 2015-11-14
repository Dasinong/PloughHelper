package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.VarietyBrowse;

public interface IVarietyBrowseDao extends IEntityDao<VarietyBrowse> {

	VarietyBrowse findByVarietyBrowseName(String varietyBrowseName);

	List<VarietyBrowse> findByCropId(Long id);

}
