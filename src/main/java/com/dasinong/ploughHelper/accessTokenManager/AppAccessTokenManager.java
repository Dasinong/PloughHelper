package com.dasinong.ploughHelper.accessTokenManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IDasinongAppDao;
import com.dasinong.ploughHelper.exceptions.GenerateAppAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidAppAccessTokenException;
import com.dasinong.ploughHelper.model.AppAccessToken;
import com.dasinong.ploughHelper.model.DasinongApp;
import com.dasinong.ploughHelper.util.AES;

public class AppAccessTokenManager {
	
	private IDasinongAppDao appDao;

	public AppAccessTokenManager() {
		this.appDao =  (IDasinongAppDao) ContextLoader.getCurrentWebApplicationContext().getBean("dasinongAppDao");
	}
	
	public AppAccessTokenManager(IDasinongAppDao appDao) {
		this.appDao = appDao;
	}
	
	public AppAccessToken generate(Long appId) throws GenerateAppAccessTokenException {
		String appSecret;
	    DasinongApp app;
	    String token;
	    
		try {
			app = this.appDao.findByAppId(appId);
			appSecret = app.getAppSecret();
			String encryptedAppId = AES.encrypt(appId.toString(), appSecret);
			token = appId + "|" + encryptedAppId;
		} catch (Exception ex) {
			// if token can't be generated for whatever reason,
			// just throw it!
			throw new GenerateAppAccessTokenException(appId);
		}
		
		return new AppAccessToken(appId, appSecret, token);
	}
	
	public AppAccessToken parse(String token) throws InvalidAppAccessTokenException {
		Long appId;
		String appSecret;
	    DasinongApp app;
	    Long decryptedAppId;
	    
		try {
			String[] tokens = token.split("\\|");
			appId = Long.valueOf(tokens[0]);
			app = this.appDao.findByAppId(appId);
			appSecret = app.getAppSecret();
			decryptedAppId = Long.valueOf(AES.decrypt(tokens[1], appSecret));
		} catch (Exception ex) {
			// If token can't be parsed correctly for whatever reason,
			// just throw it!
			throw new InvalidAppAccessTokenException(token);
		}
		    
		if (!appId.equals(decryptedAppId)) {
			throw new InvalidAppAccessTokenException(token);
		}
		    
		return new AppAccessToken(appId, appSecret, token);
	}
	
	public static void main(String[] args) throws Exception {
		 ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
	              "file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
	              "file:./src/main/webapp/WEB-INF/spring/database/OneOffDataSource.xml",
	              "file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml"); 
	    
	    IDasinongAppDao appDao = (IDasinongAppDao) applicationContext.getBean("dasinongAppDao");
	    DasinongApp app = appDao.create("测试App");
	    AppAccessTokenManager manager = new AppAccessTokenManager(appDao);
	    AppAccessToken token = manager.generate(app.getAppId());
	    System.out.println(token.getToken());
	    AppAccessToken decryptedToken = manager.parse(token.getToken());
	    System.out.println(decryptedToken.getAppId());
	}
}
