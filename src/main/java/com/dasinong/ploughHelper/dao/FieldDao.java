package com.dasinong.ploughHelper.dao;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.Field;

public class FieldDao extends HibernateDaoSupport{

	public void save(Field field) {
		getHibernateTemplate().save(field);
	}


	public void update(Field field) {
		getHibernateTemplate().update(field);

	}

	public void delete(Field field) {
		getHibernateTemplate().delete(field);
	}

	public Field findByFieldName(String fieldName) {
		List list = getHibernateTemplate().find(
				"from Field where fieldName=?",fieldName);
		return (Field) list.get(0);
	}

}
