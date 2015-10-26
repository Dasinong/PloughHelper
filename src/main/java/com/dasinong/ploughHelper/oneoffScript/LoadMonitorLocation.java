package com.dasinong.ploughHelper.oneoffScript;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.dasinong.ploughHelper.dao.IMonitorLocationDao;
import com.dasinong.ploughHelper.model.MonitorLocation;

public class LoadMonitorLocation {
	public static void main(String[] args) throws IOException{
		//This is the same with @ContextConfiguration
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
						"file:./src/main/webapp/WEB-INF/spring/database/OneOffDataSource.xml",
						"file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml");  
		//This is the same with autowired.
		IMonitorLocationDao monitorLocationDao = (IMonitorLocationDao) applicationContext.getBean("monitorLocationDao");
		
		
		String fullpath = "/Users/jiangsean/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt";
		MonitorLocation m;
		File f = new File(fullpath);
		FileInputStream fr = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
		String line;
		while ((line=br.readLine())!=null) {
			try{
				line = line.trim();
				String units[] = line.split(" ");
				if (units.length==6){
					m = new MonitorLocation(units[0],units[1],units[2],units[3],units[4],units[5]);
					monitorLocationDao.save(m);
				}
			}catch (Exception e){
				System.out.println("Error happend while inserting monitor location " + line);
			}
		}
		br.close();
		fr.close();
	}
}
