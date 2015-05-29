package com.dasinong.ploughHelper.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.QualityItemValue;

public class QualityItemValueDao extends HibernateDaoSupport{

	public void save(QualityItemValue qualityItemValue) {
		getHibernateTemplate().save(qualityItemValue);
	}


	public void update(QualityItemValue qualityItemValue) {
		getHibernateTemplate().update(qualityItemValue);

	}

	public void delete(QualityItemValue qualityItemValue) {
		getHibernateTemplate().delete(qualityItemValue);
	}

	public Map<Long,String> findByVarietyId(Long varietyId) {
		Map<Long,String> result = new HashMap<Long,String>();
		List list = getHibernateTemplate().find(
				"from QualityItemValue where varietyId=?",varietyId);
		for (Object i : list){
			QualityItemValue qiv = (QualityItemValue) i;
			result.put(qiv.getQualityItemId(),qiv.getItemValue());
		}
		return result;
	}

}
