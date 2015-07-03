package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dasinong.ploughHelper.model.SoilTestReport;

public class SoilTestReportDao implements ISoilTestReportDao{
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#getSessionFactory()
	 */
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#setSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#save(com.dasinong.ploughHelper.model.SoilTestReport)
	 */
	@Override
	public void save(SoilTestReport SoilTestReport) {
		this.getSessionFactory().getCurrentSession().save(SoilTestReport);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#update(com.dasinong.ploughHelper.model.SoilTestReport)
	 */
	@Override
	public void update(SoilTestReport SoilTestReport) {
		this.getSessionFactory().getCurrentSession().update(SoilTestReport);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#delete(com.dasinong.ploughHelper.model.SoilTestReport)
	 */
	@Override
	public void delete(SoilTestReport SoilTestReport) {
		this.getSessionFactory().getCurrentSession().delete(SoilTestReport);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISoilTestReportDao#findById(java.lang.Long)
	 */
	@Override
	public SoilTestReport findById(Long id) {
		SoilTestReport spec= (SoilTestReport) this.getSessionFactory().getCurrentSession().get(SoilTestReport.class,id);
		spec.getAn();
		return spec;
	}
	
	@Override
	public List<SoilTestReport> findByFieldId(Long fid) {
		@SuppressWarnings("unchecked")
		List<SoilTestReport> list = this.getSessionFactory().getCurrentSession().createQuery(
				"from SoilTestReport where fieldId=:fieldId").setLong("fieldId",fid).list();
		return list;
	}
	
	@Override
	public List<SoilTestReport> findByUserId(Long uid) {
		@SuppressWarnings("unchecked")
		List<SoilTestReport> list = this.getSessionFactory().getCurrentSession().createQuery(
				"from SoilTestReport where userId=:userId").setLong("userId",uid).list();
		return list;
	}

}
