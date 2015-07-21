package com.dasinong.ploughHelper.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.CPProductBrowse;


public class CPProductBrowseDao extends HibernateDaoSupport implements ICPProductBrowseDao{
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICPProductBrowseDao#save(com.dasinong.ploughHelper.model.CPProductBrowse)
	 */
	@Override
	public void save(CPProductBrowse cPProductBrowse) {
		getHibernateTemplate().save(cPProductBrowse);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICPProductBrowseDao#findById(java.lang.Long)
	 */
	@Override
	public CPProductBrowse findById(Long id) {
		return (CPProductBrowse) this.getHibernateTemplate().get(CPProductBrowse.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICPProductBrowseDao#findByModel(java.lang.String)
	 */
	@Override
	public List<CPProductBrowse> findByModel(String model){
		List list = getHibernateTemplate().find("from CPProductBrowse where model=?",model);
		if (list==null){
			return new ArrayList<CPProductBrowse>();
		}
		return list;
	}
}
