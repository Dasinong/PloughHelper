package com.dasinong.ploughHelper.inputParser;

import javax.servlet.http.HttpServletRequest;

import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

public class UserParser {
	boolean isValid;
	private User user;

	public UserParser(HttpServletRequest request) throws Exception {
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		String userName = requestX.getStringOptional("username", "");
		String password = requestX.getNonEmptyString("password");
		String cellPhone = requestX.getNonEmptyString("cellphone");
		String address = requestX.getStringOptional("address", null);
		String deviceId = requestX.getStringOptional("deviceId", null);
		String channel = requestX.getStringOptional("channel", null);
		Long institutionId = requestX.getLongOptional("institutionId", 0L);

		user = new User();
		user.setUserName(userName);
		user.setAndEncryptPassword(password);
		user.setCellPhone(cellPhone);
		user.setAddress(address);
		user.setDeviceId(deviceId);
		user.setChannel(channel);
		user.setInstitutionId(institutionId);
	}

	public User getUser() {
		return this.user;
	}
}
