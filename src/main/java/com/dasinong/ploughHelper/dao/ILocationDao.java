package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Location;

public interface ILocationDao {

	public abstract void save(Location location);

	public abstract void update(Location location);

	public abstract void delete(Location location);

	public abstract Location findByLocationName(String locationName);

	public abstract Location findById(Long id);

	public abstract List getIdList(String province, String city,
			String country, String district);

	public abstract List getAll();

}