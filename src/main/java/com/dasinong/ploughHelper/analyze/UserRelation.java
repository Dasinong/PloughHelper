package com.dasinong.ploughHelper.analyze;

import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.model.User;

//This is dynamic analysis from database directly.
public class UserRelation {
	class UserTree{
		public User user;
		public User parent;
		public UserTree(User user, User parent){
			
		}
	}
	IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
	
	List<User> userList = userDao.findAll();
	
	
}
