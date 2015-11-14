package com.dasinong.ploughHelper.dao;

import java.sql.Timestamp;

import com.dasinong.ploughHelper.exceptions.GenerateUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.MultipleUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.UserAccessTokenExpiredException;
import com.dasinong.ploughHelper.exceptions.UserAccessTokenNotFoundException;
import com.dasinong.ploughHelper.model.UserAccessToken;

public interface IUserAccessTokenDao extends IEntityDao<UserAccessToken> {
	
	abstract public void deleteByUserIdAndAppId(Long userId, Long appId);
	
	abstract public UserAccessToken findByToken(String token);
	
	abstract public UserAccessToken findLiveByToken(String token) throws UserAccessTokenExpiredException;
	
	abstract public UserAccessToken findByUserIdAndAppId(Long userId, Long appId);
	
	abstract public UserAccessToken findLiveByUserIdAndAppId(Long userId, Long appId) throws UserAccessTokenExpiredException;

}
