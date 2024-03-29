package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Variety;

public interface IVarietyDao extends IEntityDao<Variety> {

	public abstract Variety findByVarietyName(String varietyName);

	public abstract List<Variety> findByCropRegion(long cropId, String suitableArea);

	List<Variety> findGenericVariety(long cropId);

	List<Variety> findVarietysByName(String varietyName);

	List<Variety> findByCrop(long cropId);

}