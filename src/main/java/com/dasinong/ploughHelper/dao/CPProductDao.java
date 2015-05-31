package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.CPProduct;

public class CPProductDao extends HibernateDaoSupport{
	public void save(CPProduct cPProduct) {
		getHibernateTemplate().save(cPProduct);
	}


	public void update(CPProduct cPProduct) {
		getHibernateTemplate().update(cPProduct);

	}

	public void delete(CPProduct cPProduct) {
		getHibernateTemplate().delete(cPProduct);
	}

	public CPProduct findByCPProductName(String cPProductName) {
		List list = getHibernateTemplate().find(
				"from CPProduct where cPProductName=?",cPProductName);
		return (CPProduct) list.get(0);
	}

}
