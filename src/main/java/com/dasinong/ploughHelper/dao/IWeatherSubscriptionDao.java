package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.exceptions.WeatherAlreadySubscribedException;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.WeatherSubscription;

public interface IWeatherSubscriptionDao extends IEntityDao<WeatherSubscription> {

	public abstract List<WeatherSubscription> findByUserId(Long userId);

	public abstract void updateOrdering(Long[] id);

	WeatherSubscription findByLocationIdAndUserId(Long userId, Long locationId);
}
