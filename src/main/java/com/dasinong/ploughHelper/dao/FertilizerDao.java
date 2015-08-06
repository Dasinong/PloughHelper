package com.dasinong.ploughHelper.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.CPProduct;
import com.dasinong.ploughHelper.model.Fertilizer;

public class FertilizerDao extends HibernateDaoSupport implements IFertilizerDao{
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFertilizerDao#save(com.dasinong.ploughHelper.model.Fertilizer)
	 */
	@Override
	public void save(Fertilizer fertilizer) {
		getHibernateTemplate().save(fertilizer);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFertilizerDao#update(com.dasinong.ploughHelper.model.Fertilizer)
	 */
	@Override
	public void update(Fertilizer fertilizer) {
		getHibernateTemplate().update(fertilizer);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFertilizerDao#delete(com.dasinong.ploughHelper.model.Fertilizer)
	 */
	@Override
	public void delete(Fertilizer fertilizer) {
		getHibernateTemplate().delete(fertilizer);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFertilizerDao#findByGeneralName(java.lang.String)
	 */
	@Override
	public List<Fertilizer> findByGeneralName(String generalName) {
		List list = getHibernateTemplate().find(
				"from Fertilizer where generalName=?",generalName);
		if (list==null){
			return new ArrayList<Fertilizer>();
		}
		return list;
	}
	

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IFertilizerDao#findById(java.lang.Long)
	 */
	@Override
	public Fertilizer findById(Long id) {
		return (Fertilizer) this.getHibernateTemplate().get(Fertilizer.class,id);
	}
}
