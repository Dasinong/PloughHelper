package com.dasinong.ploughHelper.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.VarietyBrowse;


public class VarietyBrowseDao extends HibernateDaoSupport implements IVarietyBrowseDao {
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyBrowseDao#save(com.dasinong.ploughHelper.model.VarietyBrowse)
	 */
	@Override
	public void save(VarietyBrowse varietyBrowse) {
		getHibernateTemplate().save(varietyBrowse);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyBrowseDao#findByVarietyBrowseName(java.lang.String)
	 */
	@Override
	public VarietyBrowse findByVarietyBrowseName(String varietyBrowseName) {
		List list = getHibernateTemplate().find(
				"from VarietyBrowse where varietyBrowseName=?",varietyBrowseName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (VarietyBrowse) list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IVarietyBrowseDao#findById(java.lang.Long)
	 */
	@Override
	public VarietyBrowse findById(Long id) {
		return (VarietyBrowse) this.getHibernateTemplate().get(VarietyBrowse.class,id);
	}
	
	@Override
	public List<VarietyBrowse> findByCropId(Long cropId){
		List list = getHibernateTemplate().find("from VarietyBrowse where cropId=?",cropId);
		if (list==null){
			return new ArrayList<VarietyBrowse>();
		}
		return list;
	}
}
