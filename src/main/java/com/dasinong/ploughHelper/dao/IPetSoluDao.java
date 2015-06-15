package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.PetSolu;

public interface IPetSoluDao {

	public abstract void save(PetSolu petSolu);

	public abstract void update(PetSolu petSolu);

	public abstract void delete(PetSolu petSolu);

	public abstract PetSolu findByPetSoluName(String petSoluName);

}