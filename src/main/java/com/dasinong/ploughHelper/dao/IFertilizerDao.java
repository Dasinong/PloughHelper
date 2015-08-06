package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Fertilizer;

public interface IFertilizerDao {

	public abstract void save(Fertilizer fertilizer);

	public abstract void update(Fertilizer fertilizer);

	public abstract void delete(Fertilizer fertilizer);

	public abstract List<Fertilizer> findByGeneralName(String generalName);

	public abstract Fertilizer findById(Long id);

}