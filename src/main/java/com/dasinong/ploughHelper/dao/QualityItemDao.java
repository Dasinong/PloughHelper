package com.dasinong.ploughHelper.dao;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.QualityItem;

public class QualityItemDao extends HibernateDaoSupport implements IQualityItemDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IQualityItemDao#save(com.dasinong.ploughHelper.model.QualityItem)
	 */
	@Override
	public void save(QualityItem qualityItem) {
		getHibernateTemplate().save(qualityItem);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IQualityItemDao#update(com.dasinong.ploughHelper.model.QualityItem)
	 */
	@Override
	public void update(QualityItem qualityItem) {
		getHibernateTemplate().update(qualityItem);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IQualityItemDao#delete(com.dasinong.ploughHelper.model.QualityItem)
	 */
	@Override
	public void delete(QualityItem qualityItem) {
		getHibernateTemplate().delete(qualityItem);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IQualityItemDao#findByQualityItemName(java.lang.String)
	 */
	@Override
	public QualityItem findByQualityItemName(String qualityItemName) {
		List list = getHibernateTemplate().find(
				"from QualityItem where qualityItemName=?",qualityItemName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (QualityItem) list.get(0);
	}

}
