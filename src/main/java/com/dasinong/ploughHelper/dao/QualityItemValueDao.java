package com.dasinong.ploughHelper.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.QualityItemValue;

public class QualityItemValueDao extends HibernateDaoSupport implements IQualityItemValueDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IQualityItemValueDao#save(com.dasinong.ploughHelper.model.QualityItemValue)
	 */
	@Override
	public void save(QualityItemValue qualityItemValue) {
		getHibernateTemplate().save(qualityItemValue);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IQualityItemValueDao#update(com.dasinong.ploughHelper.model.QualityItemValue)
	 */
	@Override
	public void update(QualityItemValue qualityItemValue) {
		getHibernateTemplate().update(qualityItemValue);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IQualityItemValueDao#delete(com.dasinong.ploughHelper.model.QualityItemValue)
	 */
	@Override
	public void delete(QualityItemValue qualityItemValue) {
		getHibernateTemplate().delete(qualityItemValue);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IQualityItemValueDao#findByVarietyId(java.lang.Long)
	 */
	@Override
	public Map<Long,String> findByVarietyId(Long varietyId) {
		Map<Long,String> result = new HashMap<Long,String>();
		List list = getHibernateTemplate().find(
				"from QualityItemValue where varietyId=?",varietyId);
		if (list==null||list.isEmpty()){
			return null;
		}
		for (Object i : list){
			QualityItemValue qiv = (QualityItemValue) i;
			result.put(qiv.getQualityItemId(),qiv.getItemValue());
		}
		return result;
	}

}
