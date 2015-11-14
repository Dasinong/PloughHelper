package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.PetDis;

public interface IPetDisDao extends IEntityDao<PetDis> {

	public abstract PetDis findByPetDisName(String petDisName);

}