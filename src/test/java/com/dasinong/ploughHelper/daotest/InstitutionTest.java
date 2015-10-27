package com.dasinong.ploughHelper.daotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dasinong.ploughHelper.dao.IInstitutionDao;
import com.dasinong.ploughHelper.model.Institution;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
								"file:./src/main/webapp/WEB-INF/spring/database/OneOffDataSource.xml",
								"file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml"
								})
public class InstitutionTest {
	
	@Autowired
	private IInstitutionDao institutionDao;
	
	@Test
	public void findByName() {
		Institution i = institutionDao.findByName("陶氏益农");
		Assert.assertEquals(i.getId(),1);
		Assert.assertEquals(i.getName(), "陶氏益农");
		Assert.assertEquals(i.getCode(), "DOWS");
	}
	
	@Test
	public void findByCode(){
		Institution i = institutionDao.findByCode("DOWS");
		Assert.assertEquals(i.getId(),1);
		Assert.assertEquals(i.getName(), "陶氏益农");
		Assert.assertEquals(i.getCode(), "DOWS");
	}
	
	@Test
	public void findById(){
		Institution i = institutionDao.findById(1);
		Assert.assertEquals(i.getId(),1);
		Assert.assertEquals(i.getName(), "陶氏益农");
		Assert.assertEquals(i.getCode(), "DOWS");
	}
}