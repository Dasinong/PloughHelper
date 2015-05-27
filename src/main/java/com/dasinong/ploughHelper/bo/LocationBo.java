package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.LocationDao;
import com.dasinong.ploughHelper.model.Location;

public class LocationBo {
	LocationDao locationDao;

	public void setLocationDao(LocationDao locationDao){
		this.locationDao = locationDao;
	}
	
	public void save(Location location) {
		locationDao.save(location);
	}


	public void update(Location location) {
		locationDao.update(location);
	}

	public void delete(Location location) {
		locationDao.delete(location);
	}

	public Location findByLocationName(String locationName) {
		return locationDao.findByLocationName(locationName);
	}

}
