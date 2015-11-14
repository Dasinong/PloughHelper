package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.PetSolu;

public interface IPetSoluDao extends IEntityDao<PetSolu> {

	public abstract PetSolu findByPetSoluName(String petSoluName);

}