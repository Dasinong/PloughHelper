package com.dasinong.ploughHelper.unittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dasinong.ploughHelper.dao.ICropDao;
import com.dasinong.ploughHelper.model.Crop;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
								"file:./src/main/webapp/WEB-INF/spring/database/OneOffDataSource.xml",
								"file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml"
								})
public class TestUploader {
	
	@Autowired
	private ICropDao cropDao;
	
	@Test
	public void getCropById(){
		Crop crop = cropDao.findById(20L);
		System.out.println(crop.getCropName());
	}
	
	public static void main(String[] args){
		//This is the same with @ContextConfiguration
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
				"file:./src/main/webapp/WEB-INF/spring/database/OneOffDataSource.xml",
				"file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml");  
		//This is the same with autowired.
		ICropDao cropDao = (ICropDao) applicationContext.getBean("cropDao");
		Crop crop = cropDao.findById(20L);
		System.out.println(crop.getCropName());
	}
}
