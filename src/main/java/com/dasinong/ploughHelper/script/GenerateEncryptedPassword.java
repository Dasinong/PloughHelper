package com.dasinong.ploughHelper.script;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.dasinong.ploughHelper.dao.ICropDao;
import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.SHA256;

public class GenerateEncryptedPassword {

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
				"file:./src/main/webapp/WEB-INF/spring/database/OneOffDataSource.xml",
				"file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml");

		IUserDao userDao = (IUserDao) applicationContext.getBean("userDao");
		List<User> users = userDao.getAllUsersWithPassword();
		System.out.println("total " + users.size() + " users");
		for (User user : users) {
			user.setAndEncryptPassword(user.getPassword());
			userDao.update(user);
			System.out.println("encrypt password for user " + user.getUserId());
		}
	}
}
