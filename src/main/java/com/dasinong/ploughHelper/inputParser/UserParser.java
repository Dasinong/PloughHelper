package com.dasinong.ploughHelper.inputParser;

import javax.servlet.http.HttpServletRequest;

import com.dasinong.ploughHelper.model.User;

public class UserParser {
	boolean isValid;
	private User user;
	public UserParser(HttpServletRequest request ) throws Exception{
		String userName =  request.getParameter("username");
		String password =  request.getParameter("password");
		String cellPhone =  request.getParameter("cellphone");
        String address = request.getParameter("address");
        String deviceId = request.getParameter("deviceId");
        String channel = request.getParameter("channel");
        Long institutionId = 0L;
        try{
        	institutionId = Long.parseLong(request.getParameter("institutionId"));
        }catch(Exception e){
        	System.out.println("Inproper institutionId. Use 0 as default");
        }

        		
        if (password==null || password.isEmpty()){
        	Exception e = new Exception("密码不能为空");
        	throw e;
        }
        if (cellPhone==null || cellPhone.isEmpty()){
        	Exception e = new Exception("电话号码不能为空");
        	throw e;
        }
        if (userName==null || userName.isEmpty())
        {
        	userName = "";
        }
                
        user = new User();
        user.setUserName(userName);
        user.setAndEncryptPassword(password);
        user.setCellPhone(cellPhone);
        user.setAddress(address);
        user.setDeviceId(deviceId);
        user.setChannel(channel);
		user.setInstitutionId(institutionId);
	}

	public User getUser(){
		return this.user;
	}
}
