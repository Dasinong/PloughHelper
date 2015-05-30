package com.dasinong.ploughHelper.inputParser;

import javax.servlet.http.HttpServletRequest;

import com.dasinong.ploughHelper.model.User;

public class UserParser {
	boolean isValid;
	private User user;
	public UserParser(HttpServletRequest request ){
		String userName =  request.getParameter("userName");
		String password =  request.getParameter("password");
		String cellPhone =  request.getParameter("cellPhone");
        String address = request.getParameter("address");
        user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setCellPhone(cellPhone);
        user.setAddress(address);
	}

	public User getUser(){
		return this.user;
	}
}
