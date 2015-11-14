package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Location;

public interface ILocationDao extends IEntityDao<Location> {

	public abstract Location findByLocationName(String locationName);

	public abstract List getIdList(String province, String city,
			String country, String district);
	
	public abstract List<Location> findLocationsInRange(double lat, double lon, double range);

}