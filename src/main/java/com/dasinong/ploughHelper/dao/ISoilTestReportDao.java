package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dasinong.ploughHelper.model.SoilTestReport;

public interface ISoilTestReportDao {

	SessionFactory getSessionFactory();

	void setSessionFactory(SessionFactory sessionFactory);

	void save(SoilTestReport SoilTestReport);

	void update(SoilTestReport SoilTestReport);

	void delete(SoilTestReport SoilTestReport);

	SoilTestReport findById(Long id);

	List<SoilTestReport> findByFieldId(Long fid);

	List<SoilTestReport> findByUserId(Long uid);

}
