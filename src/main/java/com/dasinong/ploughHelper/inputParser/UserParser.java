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
        	userName = cellPhone;
        }
        
        user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setCellPhone(cellPhone);
        user.setAddress(address);
        user.setDeviceId(deviceId);
		if (channel!=null && !"".equals(channel)){
			if(channel.equals("TaoShi")){
				user.setChannel("陶氏");
			}else{
			    user.setChannel(channel);
			}
		}
	}

	public User getUser(){
		return this.user;
	}
}
