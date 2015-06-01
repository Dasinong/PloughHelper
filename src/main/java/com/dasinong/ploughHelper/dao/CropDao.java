package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Crop;

public class CropDao extends HibernateDaoSupport{
	public void save(Crop crop) {
		getHibernateTemplate().save(crop);
	}


	public void update(Crop crop) {
		getHibernateTemplate().update(crop);

	}

	public void delete(Crop crop) {
		getHibernateTemplate().delete(crop);
	}

	public Crop findByCropName(String cropName) {
		List list = getHibernateTemplate().find(
				"from Crop where cropName=?",cropName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Crop) list.get(0);
	}

}
