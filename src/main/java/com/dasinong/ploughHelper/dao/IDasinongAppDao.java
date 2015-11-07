package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.DasinongApp;

public interface IDasinongAppDao {

	abstract DasinongApp findByAppId(Long appId);
	
	abstract DasinongApp create(String appName);
}
