package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.Task;

public class PetDisSpecDao extends HibernateDaoSupport implements IPetDisSpecDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisSpecDao#save(com.dasinong.ploughHelper.model.PetDisSpec)
	 */
	@Override
	public void save(PetDisSpec petDisSpec) {
		getHibernateTemplate().save(petDisSpec);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisSpecDao#update(com.dasinong.ploughHelper.model.PetDisSpec)
	 */
	@Override
	public void update(PetDisSpec petDisSpec) {
		getHibernateTemplate().update(petDisSpec);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisSpecDao#delete(com.dasinong.ploughHelper.model.PetDisSpec)
	 */
	@Override
	public void delete(PetDisSpec petDisSpec) {
		getHibernateTemplate().delete(petDisSpec);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisSpecDao#findByPetDisSpecName(java.lang.String)
	 */
	@Override
	public PetDisSpec findByPetDisSpecName(String petDisSpecName) {
		List list = getHibernateTemplate().find(
				"from PetDisSpec where petDisSpecName=?",petDisSpecName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (PetDisSpec) list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisSpecDao#findById(java.lang.Long)
	 */
	@Override
	public PetDisSpec findById(Long id) {
		return (PetDisSpec) this.getHibernateTemplate().get(PetDisSpec.class,id);
	}

	@Override
	public PetDisSpec findByNameAndCrop(String petDisSpecName, String cropName) {
		List list = getHibernateTemplate().find(
				"from PetDisSpec where petDisSpecName=? and cropName=?",petDisSpecName, cropName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (PetDisSpec) list.get(0);
	}
}