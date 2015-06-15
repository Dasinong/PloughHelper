package com.dasinong.ploughHelper.dao;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.Step;

public class StepDao extends HibernateDaoSupport implements IStepDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IStepDao#save(com.dasinong.ploughHelper.model.Step)
	 */
	@Override
	public void save(Step step) {
		getHibernateTemplate().save(step);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IStepDao#update(com.dasinong.ploughHelper.model.Step)
	 */
	@Override
	public void update(Step step) {
		getHibernateTemplate().update(step);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IStepDao#delete(com.dasinong.ploughHelper.model.Step)
	 */
	@Override
	public void delete(Step step) {
		getHibernateTemplate().delete(step);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IStepDao#findByStepName(java.lang.String)
	 */
	@Override
	public Step findByStepName(String stepName) {
		List list = getHibernateTemplate().find(
				"from Step where stepName=?",stepName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (Step) list.get(0);
	}

}
