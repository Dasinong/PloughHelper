package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.TaskSpec;
import com.dasinong.ploughHelper.model.Variety;


public class VarietyDao extends HibernateDaoSupport implements IVarietyDao {
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyDao#save(com.dasinong.ploughHelper.model.Variety)
	 */
	@Override
	public void save(Variety variety) {
		getHibernateTemplate().save(variety);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyDao#update(com.dasinong.ploughHelper.model.Variety)
	 */
	@Override
	public void update(Variety variety) {
		getHibernateTemplate().update(variety);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyDao#delete(com.dasinong.ploughHelper.model.Variety)
	 */
	@Override
	public void delete(Variety variety) {
		getHibernateTemplate().delete(variety);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyDao#findByVarietyName(java.lang.String)
	 */
	@Override
	public Variety findByVarietyName(String varietyName) {
		List list = getHibernateTemplate().find(
				"from Variety where varietyName=?",varietyName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Variety) list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyDao#findById(java.lang.Long)
	 */
	@Override
	public Variety findById(Long id) {
		return (Variety) this.getHibernateTemplate().get(Variety.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyDao#findByCropRegion(long, java.lang.String)
	 */
	@Override
	public List<Variety> findByCropRegion(long cropId, String suitableArea){
		List list = getHibernateTemplate().find(
				"from Variety where cropId=? and suitableArea like '%"+suitableArea + "%'",cropId);
		return list;
	}
}
