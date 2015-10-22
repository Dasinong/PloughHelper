package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.WeatherSubscription;

public class WeatherSubscriptionDao  extends HibernateDaoSupport 
	implements IWeatherSubscriptionDao {
	
	@Override
	public WeatherSubscription findById(Long id) {
		return getHibernateTemplate().get(WeatherSubscription.class, id);
	}
	
	@Override
	public List<WeatherSubscription> findByUserId(Long userId) {
		return getHibernateTemplate().find("from WeatherSubscription where userId = ?", userId);
	}

	@Override
	public void delete(WeatherSubscription weatherSubs) {
		this.getHibernateTemplate().delete(weatherSubs);
	}

	@Override
	public void save(WeatherSubscription weatherSubs) {
		this.getHibernateTemplate().save(weatherSubs);
	}
	
	
}
