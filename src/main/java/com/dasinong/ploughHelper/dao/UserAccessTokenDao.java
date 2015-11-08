package com.dasinong.ploughHelper.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.exceptions.GenerateUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.MultipleUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.UserAccessTokenExpiredException;
import com.dasinong.ploughHelper.exceptions.UserAccessTokenNotFoundException;
import com.dasinong.ploughHelper.model.UserAccessToken;
import com.dasinong.ploughHelper.util.AES;
import com.dasinong.ploughHelper.util.Env;

public class UserAccessTokenDao extends HibernateDaoSupport
	implements IUserAccessTokenDao {

	@Override
	public void save(UserAccessToken token) {
		this.getHibernateTemplate().save(token);
	}

	@Override
	public void update(UserAccessToken token) {
		this.getHibernateTemplate().update(token);
	}
	
	@Override
	public void delete(UserAccessToken token) {
		this.getHibernateTemplate().delete(token);
	}
	
	@Override
	public void deleteByUserIdAndAppId(Long userId, Long appId) {
		Query query = this.getSession().createQuery("delete from UserAccessToken where userId = :userId and appId = :appId");
		query.setParameter("userId", userId);
		query.setParameter("appId", appId);
		query.executeUpdate();
	}
	
	@Override
	public UserAccessToken findByToken(String token) {
		List<UserAccessToken> tokens = this.getHibernateTemplate().find(
				"from UserAccessToken where token = ?", token);
		
		if (tokens == null || tokens.size() == 0) {
			return null;
		}
		
		return tokens.get(0);
	}
	
	@Override
	public UserAccessToken findLiveByToken(String token) throws UserAccessTokenExpiredException {
		UserAccessToken accessToken = this.findByToken(token);
		if (accessToken != null && accessToken.isExpired()) {
			throw new UserAccessTokenExpiredException(token);
		}
		
		return accessToken;
	}

	@Override
	public UserAccessToken findByUserIdAndAppId(Long userId, Long appId) {
		List<UserAccessToken> tokens = this.getHibernateTemplate().find(
				"from UserAccessToken where userId = ? and appId = ?", userId, appId);
		
		if (tokens == null || tokens.size() == 0) {
			return null;
		}
		
		return tokens.get(0);
	}

	@Override
	public UserAccessToken findLiveByUserIdAndAppId(Long userId, Long appId) throws UserAccessTokenExpiredException {
		UserAccessToken accessToken = this.findByUserIdAndAppId(userId, appId);
		if (accessToken != null && accessToken.isExpired()) {
			throw new UserAccessTokenExpiredException(accessToken.getToken());
		}
		
		return accessToken;
	}
}
