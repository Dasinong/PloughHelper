package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.User;

public interface IUserDao extends IEntityDao<User> {

	User findByUserName(String userName);

	User findByCellphone(String cellphone);

	User findByQQ(String qqtoken);

	User findByWeixin(String weixintoken);

	long getUIDbyRef(String refcode);

	public List<User> getAllUsersWithEmptyRefCode();

	List<User> getAllUsersWithPassword();

}
