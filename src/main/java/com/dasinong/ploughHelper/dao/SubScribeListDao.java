package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import com.dasinong.ploughHelper.model.SubScribeList;

public class SubScribeListDao implements ISubScribeListDao {
   private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#getSessionFactory()
	 */
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#setSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#save(com.dasinong.ploughHelper.model.SubScribeList)
	 */
	@Override
	public void save(SubScribeList SubScribeList) {
		this.getSessionFactory().getCurrentSession().save(SubScribeList);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#update(com.dasinong.ploughHelper.model.SubScribeList)
	 */
	@Override
	public void update(SubScribeList SubScribeList) {
		this.getSessionFactory().getCurrentSession().update(SubScribeList);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#delete(com.dasinong.ploughHelper.model.SubScribeList)
	 */
	@Override
	public void delete(SubScribeList SubScribeList) {
		this.getSessionFactory().getCurrentSession().delete(SubScribeList);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#findById(java.lang.Long)
	 */
	@Override
	public SubScribeList findById(Long id) {
		SubScribeList ssb= (SubScribeList) this.getSessionFactory().getCurrentSession().get(SubScribeList.class,id);
		ssb.getCropId();
		return ssb;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubScribeListDao#findByOwnerId(java.lang.Long)
	 */
	@Override
	public List<SubScribeList> findByOwnerId(Long oid) {
		List list = this.getSessionFactory().getCurrentSession().createQuery(
				"from SubScribeList where ownerId=:ownerId").setLong("ownerId",oid).list();
		return list;
	}

}
