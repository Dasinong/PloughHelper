package com.dasinong.ploughHelper.script;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.dasinong.ploughHelper.dao.IDasinongAppDao;

public class CreateDasinongApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
				"file:./src/main/webapp/WEB-INF/spring/database/OneOffDataSource.xml",
				"file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml");

		IDasinongAppDao appDao = (IDasinongAppDao) applicationContext.getBean("dasinongAppDao");
		appDao.create("安卓版今日农事");
		appDao.create("iOS版今日农事");
		appDao.create("农事天地.com");
	}
}
