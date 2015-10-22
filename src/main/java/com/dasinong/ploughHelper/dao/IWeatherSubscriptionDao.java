package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.WeatherSubscription;

public interface IWeatherSubscriptionDao {

	public abstract void delete(WeatherSubscription weatherSubs);
	
	public abstract void save(WeatherSubscription weatherSubs);
	
	public abstract WeatherSubscription findById(Long id);
	
	public abstract List<WeatherSubscription> findByUserId(Long userId);
}
