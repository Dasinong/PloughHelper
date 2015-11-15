package com.dasinong.ploughHelper.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.CPProduct;
import com.dasinong.ploughHelper.model.Fertilizer;

public class FertilizerDao extends EntityHibernateDao<Fertilizer>implements IFertilizerDao {

	@Override
	public List<Fertilizer> findByGeneralName(String generalName) {
		List list = getHibernateTemplate().find("from Fertilizer where generalName=?", generalName);
		if (list == null) {
			return new ArrayList<Fertilizer>();
		}
		return list;
	}

}
