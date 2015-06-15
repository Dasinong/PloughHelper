package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.User;

public interface IUserDao {

	public abstract void save(User user);

	public abstract void update(User user);

	public abstract void delete(User user);

	public abstract User findByUserName(String userName);

	public abstract User findByCellphone(String cellphone);

}