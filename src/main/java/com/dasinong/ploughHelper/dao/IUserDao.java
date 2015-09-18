package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.User;

public interface IUserDao {

	void save(User user);

	void update(User user);

	void delete(User user);
	
	User findById(Long id);

	User findByUserName(String userName);

	User findByCellphone(String cellphone);

	User findByQQ(String qqtoken);
	
	User findByWeixin(String weixintoken);
	
	long getUIDbyRef(String refcode);
	
	public List<User> getAllUser();

}
