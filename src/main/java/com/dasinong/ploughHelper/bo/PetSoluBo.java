package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.PetSoluDao;
import com.dasinong.ploughHelper.model.PetSolu;

public class PetSoluBo {
	PetSoluDao petSoluDao;

	public void setPetSoluDao(PetSoluDao petSoluDao){
		this.petSoluDao = petSoluDao;
	}
	
	public void save(PetSolu petSolu) {
		petSoluDao.save(petSolu);
	}


	public void update(PetSolu petSolu) {
		petSoluDao.update(petSolu);
	}

	public void delete(PetSolu petSolu) {
		petSoluDao.delete(petSolu);
	}

	public PetSolu findByPetSoluName(String petSoluName) {
		return petSoluDao.findByPetSoluName(petSoluName);
	}

}
