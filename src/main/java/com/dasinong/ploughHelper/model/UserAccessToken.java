package com.dasinong.ploughHelper.model;

import java.sql.Timestamp;

/**
 * 
 * @author xiahonggao
 *
 * UserAccessToken is the most commonly used type of token. This kind of 
 * access  token is needed any time dasinong app calls an API to 
 * manipulate user's data on their behalf
 * 
 * UserAccessToken is generated using user's Id, app's Id, creation time 
 * and pre-defined SALT. The rule to generate a user access token is:
 * 
 *   AES.encrypt(userId+","+appId+","+timestamp, SALT)
 * 
 * User access token expires in 60 days and is renewed when app calls any
 * API on user's behalf.
 *
 * User access token is stored in mysql database with the following fields:
 * - userId
 * - appId
 * - createdAt
 * - expiredAt
 * - token
 */
public class UserAccessToken {

	private Long userId;
	private Long appId;
	private Timestamp createdAt;
	private Timestamp expiredAt;
	private String token;
	
}
