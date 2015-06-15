package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.PetDis;

public interface IPetDisDao {

	public abstract void save(PetDis petDis);

	public abstract void update(PetDis petDis);

	public abstract void delete(PetDis petDis);

	public abstract PetDis findByPetDisName(String petDisName);

}