package com.dasinong.ploughHelper.datapool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IMonitorLocationDao;
import com.dasinong.ploughHelper.model.MonitorLocation;

//Used for easy access, read only and does not support dynamic DB change.
public class AllMonitorLocation {
	private IMonitorLocationDao monitorLocationDao;
	
	private static Object SynRoot = new Object();
	@SuppressWarnings("unchecked")
	private AllMonitorLocation(){
		_allLocation = new HashMap<Integer,MonitorLocation>();
		monitorLocationDao = (IMonitorLocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("monitorLocationDao");

		List<MonitorLocation> mls = monitorLocationDao.findAll();
		for (MonitorLocation ml : mls ){
			_allLocation.put(ml.getCode(), ml);
		}
	}
	
	private AllMonitorLocation(IMonitorLocationDao monitorLocationDao){
		_allLocation = new HashMap<Integer,MonitorLocation>();
		if (monitorLocationDao==null){
			monitorLocationDao = (IMonitorLocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("monitorLocationDao");
		}
		List<MonitorLocation> mls = monitorLocationDao.findAll();
		for (MonitorLocation ml : mls ){
			_allLocation.put(ml.getCode(), ml);
		}
	}

	public static AllMonitorLocation getInstance(){
		synchronized (SynRoot){
			if (null == allLocation){
				{
					allLocation = new AllMonitorLocation();
				}
			}
		}
		return allLocation;
	}
	
	public static AllMonitorLocation getInstance(IMonitorLocationDao monitorLocationDao){
		synchronized (SynRoot){
			if (null == allLocation){
				{
					allLocation = new AllMonitorLocation(monitorLocationDao);
				}
			}
		}
		return allLocation;
	}

	private static AllMonitorLocation allLocation;

	
	public HashMap<Integer,MonitorLocation> _allLocation;

	public int getNearest(double lat,double lon){
		MonitorLocation target = null;
		double minDis =100;
		Iterator iter= _allLocation.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<Integer,MonitorLocation> entry = (Map.Entry<Integer,MonitorLocation>) iter.next();
			MonitorLocation ml = entry.getValue();
			if ((ml.getLatitude()-lat)*(ml.getLatitude()-lat) +(ml.getLongitude()-lon)*(ml.getLongitude()-lon)<minDis){
				minDis = (ml.getLatitude()-lat)*(ml.getLatitude()-lat) +(ml.getLongitude()-lon)*(ml.getLongitude()-lon);
				target = ml;
			}
		}
		return target.getCode();
	}

	public MonitorLocation getMonitorLocation(int areaid){
		return _allLocation.get(areaid);
	}

	public static void main(String[] args){
	
		Iterator iter= AllMonitorLocation.getInstance()._allLocation.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
		System.out.print(AllMonitorLocation.getInstance().getNearest(39,116));
	}
}
