package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.PetDisSpec;

public interface IPetDisSpecDao {

	public abstract void save(PetDisSpec petDisSpec);

	public abstract void update(PetDisSpec petDisSpec);

	public abstract void delete(PetDisSpec petDisSpec);

	public abstract PetDisSpec findByPetDisSpecName(String petDisSpecName);
	
	public abstract PetDisSpec findByNameAndCrop(String petDisSpecName, String cropName);

	public abstract PetDisSpec findById(Long id);

}