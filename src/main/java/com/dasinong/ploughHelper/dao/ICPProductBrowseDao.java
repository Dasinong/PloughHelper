package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.CPProductBrowse;

public interface ICPProductBrowseDao {

	public abstract void save(CPProductBrowse cPProductBrowse);

	public abstract CPProductBrowse findById(Long id);

	public abstract List<CPProductBrowse> findByModel(String model);

}