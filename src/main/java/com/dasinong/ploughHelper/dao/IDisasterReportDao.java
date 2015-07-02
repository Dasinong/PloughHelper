package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.DisasterReport;

public interface IDisasterReportDao {

	public abstract void save(DisasterReport disasterReport);

	public abstract void update(DisasterReport disasterReport);

	public abstract void delete(DisasterReport disasterReport);

	public abstract DisasterReport findByDisasterName(String disasterName);

}