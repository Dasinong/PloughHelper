package com.dasinong.ploughHelper.dao;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.QualityItem;

public class QualityItemDao extends HibernateDaoSupport{

	public void save(QualityItem qualityItem) {
		getHibernateTemplate().save(qualityItem);
	}


	public void update(QualityItem qualityItem) {
		getHibernateTemplate().update(qualityItem);

	}

	public void delete(QualityItem qualityItem) {
		getHibernateTemplate().delete(qualityItem);
	}

	public QualityItem findByQualityItemName(String qualityItemName) {
		List list = getHibernateTemplate().find(
				"from QualityItem where qualityItemName=?",qualityItemName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (QualityItem) list.get(0);
	}

}
