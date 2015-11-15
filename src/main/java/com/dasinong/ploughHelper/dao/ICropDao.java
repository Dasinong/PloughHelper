package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Crop;

public interface ICropDao extends IEntityDao<Crop> {

	public abstract Crop findByCropName(String cropName);

	List<Crop> findByType(String type);

}