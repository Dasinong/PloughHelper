package com.dasinong.ploughHelper.dao;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.DasinongApp;

public class DasinongAppDao extends HibernateDaoSupport implements IDasinongAppDao {

	@Override
	public DasinongApp findByAppId(Long appId) {
		return this.getHibernateTemplate().get(DasinongApp.class, appId);
	}

	@Override
	public DasinongApp create(String appName) {
		DasinongApp app = new DasinongApp();
		app.setAppName(appName);
		
		// App secret is used as encrypt key of AES algorithm.
		// Thus, it has to be 16 bytes.
	    SecureRandom rand = new SecureRandom();
	    System.out.println(rand.generateSeed(16));
	    String appSecret = Base64.getEncoder().encodeToString(rand.generateSeed(16));
	    app.setAppSecret(appSecret);
	    
	    this.getHibernateTemplate().save(app);
	    
	    return app;
	}
	

}
