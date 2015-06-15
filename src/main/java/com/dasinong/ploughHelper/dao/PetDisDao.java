package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.PetDis;

public class PetDisDao extends HibernateDaoSupport implements IPetDisDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisDao#save(com.dasinong.ploughHelper.model.PetDis)
	 */
	@Override
	public void save(PetDis petDis) {
		getHibernateTemplate().save(petDis);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisDao#update(com.dasinong.ploughHelper.model.PetDis)
	 */
	@Override
	public void update(PetDis petDis) {
		getHibernateTemplate().update(petDis);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisDao#delete(com.dasinong.ploughHelper.model.PetDis)
	 */
	@Override
	public void delete(PetDis petDis) {
		getHibernateTemplate().delete(petDis);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisDao#findByPetDisName(java.lang.String)
	 */
	@Override
	public PetDis findByPetDisName(String petDisName) {
		List list = getHibernateTemplate().find(
				"from PetDis where petDisName=?",petDisName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (PetDis) list.get(0);
	}

}
