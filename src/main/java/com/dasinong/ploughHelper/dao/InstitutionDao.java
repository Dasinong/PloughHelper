package com.dasinong.ploughHelper.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Institution;

public class InstitutionDao extends HibernateDaoSupport implements IInstitutionDao{
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IInstitutionDao#save(com.dasinong.ploughHelper.model.Institution)
	 */
	@Override
	public void save(Institution institution) {
		getHibernateTemplate().save(institution);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IInstitutionDao#update(com.dasinong.ploughHelper.model.Institution)
	 */
	@Override
	public void update(Institution institution) {
		getHibernateTemplate().update(institution);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IInstitutionDao#delete(com.dasinong.ploughHelper.model.Institution)
	 */
	@Override
	public void delete(Institution institution) {
		getHibernateTemplate().delete(institution);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IInstitutionDao#findAll()
	 */
	@Override
	public List<Institution> findAll() {
		List list = getHibernateTemplate().find(
				"from Institution");
		if (list==null){
			return new ArrayList<Institution>();
		}
		return list;
	}
	

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IInstitutionDao#findById(int)
	 */
	@Override
	public Institution findById(int id) {
		return (Institution) this.getHibernateTemplate().get(Institution.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IInstitutionDao#findByName(java.lang.String)
	 */
	@Override
	public Institution findByName(String name) {
		List list = getHibernateTemplate().find(
				"from Institution where name=?",name);
		if (list==null){
			return null;
		}
		return (Institution) list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IInstitutionDao#findByCode(java.lang.String)
	 */
	@Override
	public Institution findByCode(String code) {
		List list = getHibernateTemplate().find(
				"from Institution where code=?",code);
		if (list==null){
			return null;
		}
		return (Institution) list.get(0);
	}
}
