package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.PetDisSpec;

public interface IPetDisSpecDao extends IEntityDao<PetDisSpec> {
	
	public abstract PetDisSpec findByPetDisSpecName(String petDisSpecName);
	
	public abstract PetDisSpec findByNameAndCrop(String petDisSpecName, String cropName);

}