package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.Crop;

public interface ICropDao {

	public abstract void save(Crop crop);

	public abstract void update(Crop crop);

	public abstract void delete(Crop crop);

	public abstract Crop findByCropName(String cropName);

}