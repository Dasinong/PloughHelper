package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Step;
import com.dasinong.ploughHelper.model.User;

public class UserDao extends HibernateDaoSupport implements IUserDao{
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserDao#save(com.dasinong.ploughHelper.model.User)
	 */
	@Override
	public void save(User user) {
		getHibernateTemplate().save(user);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserDao#update(com.dasinong.ploughHelper.model.User)
	 */
	@Override
	public void update(User user) {
		getHibernateTemplate().update(user);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserDao#delete(com.dasinong.ploughHelper.model.User)
	 */
	@Override
	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	
	@Override
	public User findById(Long id) {
		return (User) this.getHibernateTemplate().get(User.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserDao#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		List list = getHibernateTemplate().find(
				"from User where userName=?",userName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IUserDao#findByCellphone(java.lang.String)
	 */
	@Override
	public User findByCellphone(String cellphone) {
		List list = getHibernateTemplate().find(
				"from User where cellphone=?",cellphone);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}


	@Override
	public User findByQQ(String qqtoken) {
		List list = getHibernateTemplate().find(
				"from User where qqtoken=?",qqtoken);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}


	@Override
	public User findByWeixin(String weixintoken) {
		List list = getHibernateTemplate().find(
				"from User where weixintoken=?",weixintoken);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}

	
	@Override
	public long getUIDbyRef(String refcode) {
		List list  = getHibernateTemplate().find(
				"from User where refcode=?",refcode);
		if (list.size()>0) return ((User) list.get(0)).getUserId();
		else return -1;
	}
}
