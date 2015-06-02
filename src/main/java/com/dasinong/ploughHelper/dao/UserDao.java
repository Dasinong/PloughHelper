package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.User;

public class UserDao extends HibernateDaoSupport{
	public void save(User user) {
		getHibernateTemplate().save(user);
	}


	public void update(User user) {
		getHibernateTemplate().update(user);

	}

	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	public User findByUserName(String userName) {
		List list = getHibernateTemplate().find(
				"from User where userName=?",userName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}
	
	public User findByCellphone(String cellphone) {
		List list = getHibernateTemplate().find(
				"from User where cellphone=?",cellphone);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}

}
