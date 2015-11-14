package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Fertilizer;

public interface IFertilizerDao extends IEntityDao<Fertilizer> {
	
	public abstract List<Fertilizer> findByGeneralName(String generalName);

}