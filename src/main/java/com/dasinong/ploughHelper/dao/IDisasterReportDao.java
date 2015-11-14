package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.DisasterReport;

public interface IDisasterReportDao extends IEntityDao<DisasterReport> {

	public abstract DisasterReport findByDisasterName(String disasterName);

}