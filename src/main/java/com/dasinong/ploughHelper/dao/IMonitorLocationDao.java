package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.MonitorLocation;

public interface IMonitorLocationDao {

	public abstract void save(MonitorLocation monitorLocation);

	public abstract void update(MonitorLocation monitorLocation);

	public abstract void delete(MonitorLocation monitorLocation);

	public abstract MonitorLocation findByCode(int code);

	List getAll();
}
