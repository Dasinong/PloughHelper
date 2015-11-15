package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.CPProductBrowse;

public interface ICPProductBrowseDao extends IEntityDao<CPProductBrowse> {

	public abstract List<CPProductBrowse> findByModel(String model);

}