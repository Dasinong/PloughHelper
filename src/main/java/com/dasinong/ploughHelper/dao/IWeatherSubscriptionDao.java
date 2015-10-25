package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.WeatherSubscription;

public interface IWeatherSubscriptionDao {

	public abstract void delete(WeatherSubscription weatherSubs);
	
	public abstract void save(WeatherSubscription weatherSubs) throws Exception;
	
	public abstract WeatherSubscription findById(Long id);
	
	public abstract List<WeatherSubscription> findByUserId(Long userId);
	
	public abstract void updateOrdering(Long[] id);

	WeatherSubscription findByLocationIdAndUserId(Long userId, Long locationId);
}
