package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.UserPetDis;

public interface IUserPetDisDao {

	void save(UserPetDis userPetDis);

	void update(UserPetDis userPetDis);

	void delete(UserPetDis userPetDis);

	UserPetDis findById(Long id);

}
