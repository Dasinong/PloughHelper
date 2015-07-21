package com.dasinong.ploughHelper.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.CPProduct;

public class CPProductDao extends HibernateDaoSupport implements ICPProductDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICPProductDao#save(com.dasinong.ploughHelper.model.CPProduct)
	 */
	@Override
	public void save(CPProduct cPProduct) {
		getHibernateTemplate().save(cPProduct);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICPProductDao#update(com.dasinong.ploughHelper.model.CPProduct)
	 */
	@Override
	public void update(CPProduct cPProduct) {
		getHibernateTemplate().update(cPProduct);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICPProductDao#delete(com.dasinong.ploughHelper.model.CPProduct)
	 */
	@Override
	public void delete(CPProduct cPProduct) {
		getHibernateTemplate().delete(cPProduct);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICPProductDao#findByCPProductName(java.lang.String)
	 */
	@Override
	public CPProduct findByRegisterationId(String registerationId) {
		List list = getHibernateTemplate().find(
				"from CPProduct where registerationId=?",registerationId);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (CPProduct) list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetDisSpecDao#findById(java.lang.Long)
	 */
	@Override
	public CPProduct findById(Long id) {
		return (CPProduct) this.getHibernateTemplate().get(CPProduct.class,id);
	}
	
	@Override
	public List<CPProduct> findByIngredient(String ingredient) {
		List list = getHibernateTemplate().find(
				"from CPProduct where activeIngredient=?",ingredient);
		if (list==null){
			return new ArrayList<CPProduct>();
		}
		return list;
	}

}