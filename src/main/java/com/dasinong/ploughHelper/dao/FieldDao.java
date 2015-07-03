package com.dasinong.ploughHelper.dao;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Field;

public class FieldDao extends HibernateDaoSupport implements IFieldDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFieldDao#save(com.dasinong.ploughHelper.model.Field)
	 */
	@Override
	public void save(Field field) {
		getHibernateTemplate().save(field);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFieldDao#update(com.dasinong.ploughHelper.model.Field)
	 */
	@Override
	public void update(Field field) {
		getHibernateTemplate().update(field);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFieldDao#delete(com.dasinong.ploughHelper.model.Field)
	 */
	@Override
	public void delete(Field field) {
		getHibernateTemplate().delete(field);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFieldDao#findByFieldName(java.lang.String)
	 */
	@Override
	public Field findByFieldName(String fieldName) {
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().find(
				"from Field where fieldName=?",fieldName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Field) list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFieldDao#findById(java.lang.Long)
	 */
	@Override
	public Field findById(Long id) {
		return (Field) this.getHibernateTemplate().get(Field.class,id);
	}

}
