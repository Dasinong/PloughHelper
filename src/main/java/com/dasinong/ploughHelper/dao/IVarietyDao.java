package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Variety;

public interface IVarietyDao {

	public abstract void save(Variety variety);

	public abstract void update(Variety variety);

	public abstract void delete(Variety variety);

	public abstract Variety findByVarietyName(String varietyName);

	public abstract Variety findById(Long id);

	public abstract List<Variety> findByCropRegion(long cropId,
			String suitableArea);

	List<Variety> findGenericVariety(long cropId);

	List<Variety> findVarietysByName(String varietyName);

	List<Variety> findByCrop(long cropId);

}