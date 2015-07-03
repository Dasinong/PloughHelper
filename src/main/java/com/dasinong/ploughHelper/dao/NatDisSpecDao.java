package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.NatDisSpec;
import com.dasinong.ploughHelper.model.Task;

public class NatDisSpecDao extends HibernateDaoSupport implements INatDisSpecDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisSpecDao#save(com.dasinong.ploughHelper.model.NatDisSpec)
	 */
	@Override
	public void save(NatDisSpec natDisSpec) {
		getHibernateTemplate().save(natDisSpec);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisSpecDao#update(com.dasinong.ploughHelper.model.NatDisSpec)
	 */
	@Override
	public void update(NatDisSpec natDisSpec) {
		getHibernateTemplate().update(natDisSpec);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisSpecDao#delete(com.dasinong.ploughHelper.model.NatDisSpec)
	 */
	@Override
	public void delete(NatDisSpec natDisSpec) {
		getHibernateTemplate().delete(natDisSpec);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisSpecDao#findByNatDisSpecName(java.lang.String)
	 */
	@Override
	public NatDisSpec findByNatDisSpecName(String natDisSpecName) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find(
				"from NatDisSpec where natDisSpecName=?",natDisSpecName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (NatDisSpec) list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.INatDisSpecDao#findById(java.lang.Long)
	 */
	@Override
	public NatDisSpec findById(Long id) {
		return (NatDisSpec) this.getHibernateTemplate().get(NatDisSpec.class,id);
	}
}
