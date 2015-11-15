package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dasinong.ploughHelper.model.SoilTestReport;

public interface ISoilTestReportDao extends IEntityDao<SoilTestReport> {

	List<SoilTestReport> findByFieldId(Long fid);

	List<SoilTestReport> findByUserId(Long uid);

}
