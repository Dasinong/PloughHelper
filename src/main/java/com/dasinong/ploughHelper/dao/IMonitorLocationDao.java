package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.MonitorLocation;

public interface IMonitorLocationDao extends IEntityDao<MonitorLocation> {

	public abstract MonitorLocation findByCode(int code);

}
