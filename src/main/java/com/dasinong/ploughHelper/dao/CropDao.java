package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Field;

public class CropDao extends HibernateDaoSupport implements ICropDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICropDao#save(com.dasinong.ploughHelper.model.Crop)
	 */
	@Override
	public void save(Crop crop) {
		getHibernateTemplate().save(crop);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICropDao#update(com.dasinong.ploughHelper.model.Crop)
	 */
	@Override
	public void update(Crop crop) {
		getHibernateTemplate().update(crop);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICropDao#delete(com.dasinong.ploughHelper.model.Crop)
	 */
	@Override
	public void delete(Crop crop) {
		getHibernateTemplate().delete(crop);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ICropDao#findByCropName(java.lang.String)
	 */
	@Override
	public Crop findByCropName(String cropName) {
		List list = getHibernateTemplate().find(
				"from Crop where cropName=?",cropName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Crop) list.get(0);
	}
	
	@Override
	public Crop findById(Long id) {
		return (Crop) this.getHibernateTemplate().get(Crop.class,id);
	}
	
}
