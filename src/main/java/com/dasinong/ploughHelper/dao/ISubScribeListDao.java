package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dasinong.ploughHelper.model.SubScribeList;

public interface ISubScribeListDao {

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#getSessionFactory()
	 */
	public abstract SessionFactory getSessionFactory();

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#setSessionFactory(org.hibernate.SessionFactory)
	 */
	public abstract void setSessionFactory(SessionFactory sessionFactory);

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#save(com.dasinong.ploughHelper.model.SubScribeList)
	 */
	public abstract void save(SubScribeList SubScribeList);

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#update(com.dasinong.ploughHelper.model.SubScribeList)
	 */
	public abstract void update(SubScribeList SubScribeList);

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#delete(com.dasinong.ploughHelper.model.SubScribeList)
	 */
	public abstract void delete(SubScribeList SubScribeList);

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#findById(java.lang.Long)
	 */
	public abstract SubScribeList findById(Long id);

	public abstract List<SubScribeList> findByOwnerId(Long oid);

}