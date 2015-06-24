package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.UserPetDis;

public class UserPetDisDao extends HibernateDaoSupport implements IUserPetDisDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserPetDisDao#save(com.dasinong.ploughHelper.model.UserPetDis)
	 */

	@Override
	public void save(UserPetDis userPetDis) {
		getHibernateTemplate().save(userPetDis);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserPetDisDao#update(com.dasinong.ploughHelper.model.UserPetDis)
	 */
	@Override
	public void update(UserPetDis userPetDis) {
		getHibernateTemplate().update(userPetDis);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserPetDisDao#delete(com.dasinong.ploughHelper.model.UserPetDis)
	 */
	@Override
	public void delete(UserPetDis userPetDis) {
		getHibernateTemplate().delete(userPetDis);
	}

	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserPetDisDao#findByCellphone(java.lang.String)
	 */
	@Override
	public UserPetDis findById(Long id) {
		List list = getHibernateTemplate().find(
				"from UserPetDis where id=?",id);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (UserPetDis) list.get(0);
	}

}
