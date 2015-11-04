package com.dasinong.ploughHelper.model;

/**
 * 
 * @author xiahonggao
 *
 * This kind of access token is needed to request API on app's behalf.
 * It is generated using a pre-agreed secret between the app and Dasinong 
 * and is then used during API calls.
 *
 * App access token is generated using app's Id and secret which are both
 * shared between app and Dasinong. The rule to generate an app access
 * token is:
 *
 *   appId + "|" + AES.encrypt(appId, appSecret)
 * 
 * App access token never expires and thus doesn't need to be persisted
 * in mysql database. (Only app id and secret is persisted in database)
 */
public class AppAccessToken {

	private Long appId;
	private String appSecret;
	private String token;

}
