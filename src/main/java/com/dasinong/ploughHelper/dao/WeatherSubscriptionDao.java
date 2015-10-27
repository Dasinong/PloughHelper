package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.exceptions.WeatherAlreadySubscribedException;
import com.dasinong.ploughHelper.model.WeatherSubscription;

public class WeatherSubscriptionDao  extends HibernateDaoSupport 
	implements IWeatherSubscriptionDao {
	
	@Override
	public WeatherSubscription findById(Long id) {
		return getHibernateTemplate().get(WeatherSubscription.class, id);
	}
	
	@Override
	public WeatherSubscription findByLocationIdAndUserId(Long userId, Long locationId) {
		List<WeatherSubscription> subs = getHibernateTemplate().find(
				"from WeatherSubscription where userId = ? and locationId = ? order by ordering DESC", userId, locationId);
		
		if (subs == null || subs.size() == 0) {
			return null;
		} else {
			return subs.get(0);
		}
	}
	
	@Override
	public List<WeatherSubscription> findByUserId(Long userId) {
		return getHibernateTemplate().find(
				"from WeatherSubscription where userId = ? order by ordering DESC", userId);
	}

	@Override
	public void delete(WeatherSubscription weatherSubs) {
		this.getHibernateTemplate().delete(weatherSubs);
	}

	@Override
	public void save(WeatherSubscription weatherSubs) throws Exception {
		Long userId = weatherSubs.getUserId();
		Long locationId = weatherSubs.getLocationId();
		if (this.findByLocationIdAndUserId(userId, locationId) != null) {
			throw new WeatherAlreadySubscribedException(locationId);
		}
		
		Long maxOrdering = this.getMaxOrdering(weatherSubs.getUserId());
		weatherSubs.setOrdering(maxOrdering + 1);
		this.getHibernateTemplate().save(weatherSubs);
	}
	
	@Override
	public void saveSafe(WeatherSubscription weatherSubs) {
		try {
			this.save(weatherSubs);
		} catch (Exception ex) {
			// suppress any exception
		}
	}
	
	@Override
	public void updateOrdering(Long[] ids) {
		for (int i=0; i < ids.length; i++) {
			this.getHibernateTemplate().bulkUpdate(
				"update WeatherSubscription set ordering = ? where weatherSubscriptionId = ?", 
				(long)(i+1), ids[ids.length - i - 1]);
		}			
	}
	
	// There might be race condition. But for now, it's fine because
	// 1. user is very unlikely to hit this API from multiple devices at the same time
	// 2. even if that happens, the result is still acceptable
	private Long getMaxOrdering(Long userId) {
		List<Long> ret = this.getHibernateTemplate().find(
			"select max(ordering) from WeatherSubscription where userId = ?", userId);

		if (ret == null || ret.size() == 0 || ret.get(0) == null) {
			return 0L;
		}
		
		return ret.get(0).longValue();
	}
}