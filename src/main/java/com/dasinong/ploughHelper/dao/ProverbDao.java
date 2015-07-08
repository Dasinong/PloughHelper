package com.dasinong.ploughHelper.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Proverb;

public class ProverbDao extends HibernateDaoSupport implements IProverbDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IProverbDao#save(com.dasinong.ploughHelper.model.Proverb)
	 */
	@Override
	public void save(Proverb proverb) {
		getHibernateTemplate().save(proverb);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IProverbDao#update(com.dasinong.ploughHelper.model.Proverb)
	 */
	@Override
	public void update(Proverb proverb) {
		getHibernateTemplate().update(proverb);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IProverbDao#delete(com.dasinong.ploughHelper.model.Proverb)
	 */
	@Override
	public void delete(Proverb proverb) {
		getHibernateTemplate().delete(proverb);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IProverbDao#findById(java.lang.Long)
	 */
	@Override
	public Proverb findById(Long id) {
		return (Proverb) this.getHibernateTemplate().get(Proverb.class,id);
	}
	
}
