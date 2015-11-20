package com.dasinong.ploughHelper.script;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ICropDao;
import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.IPetDisSpecBrowseDao;
import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.dao.IWeatherSubscriptionDao;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.WeatherSubscription;
import com.dasinong.ploughHelper.model.WeatherSubscriptionType;

@Transactional
public class CreateWeatherSubscriptionForFields {

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
				"file:./src/main/webapp/WEB-INF/spring/database/ScriptDataSource.xml",
				"file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml");

		IPetDisSpecDao dao = (IPetDisSpecDao) applicationContext.getBean("petDisSpecDao");
		IFieldDao fieldDao = (IFieldDao) applicationContext.getBean("fieldDao");
		List<Field> fields = fieldDao.findAll();
		IWeatherSubscriptionDao weatherSubsDao = (IWeatherSubscriptionDao) applicationContext
				.getBean("weatherSubscriptionDao");

		for (Field field : fields) {
			Location loc = field.getLocation();
			WeatherSubscription subs = new WeatherSubscription();
			subs.setLocationId(loc.getLocationId());
			subs.setMonitorLocationId(field.getMonitorLocationId());
			subs.setLocationName(loc.toString());
			subs.setUserId(field.getUser().getUserId());
			subs.setType(WeatherSubscriptionType.FIELD);
			weatherSubsDao.save(subs);
		}
	}
}
