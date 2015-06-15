package com.dasinong.ploughHelper.dao;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.Variety;

public class SubStageDao extends HibernateDaoSupport implements ISubStageDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubStageDao#save(com.dasinong.ploughHelper.model.SubStage)
	 */
	@Override
	public void save(SubStage subStage) {
		getHibernateTemplate().save(subStage);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubStageDao#update(com.dasinong.ploughHelper.model.SubStage)
	 */
	@Override
	public void update(SubStage subStage) {
		getHibernateTemplate().update(subStage);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubStageDao#delete(com.dasinong.ploughHelper.model.SubStage)
	 */
	@Override
	public void delete(SubStage subStage) {
		getHibernateTemplate().delete(subStage);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubStageDao#findBySubStageName(java.lang.String)
	 */
	@Override
	public SubStage findBySubStageName(String subStageName) {
		List list = getHibernateTemplate().find(
				"from SubStage where subStageName=?",subStageName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (SubStage) list.get(0);
	}

	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.ISubStageDao#findById(java.lang.Long)
	 */
	@Override
	public SubStage findById(Long id) {
		return (SubStage) this.getHibernateTemplate().get(SubStage.class,id);
	}
}
