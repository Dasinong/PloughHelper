package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.PetDisSpecDao;
import com.dasinong.ploughHelper.model.PetDisSpec;

public class PetDisSpecBo {
	PetDisSpecDao petDisSpecDao;

	public void setPetDisSpecDao(PetDisSpecDao petDisSpecDao){
		this.petDisSpecDao = petDisSpecDao;
	}
	
	public void save(PetDisSpec petDisSpec) {
		petDisSpecDao.save(petDisSpec);
	}


	public void update(PetDisSpec petDisSpec) {
		petDisSpecDao.update(petDisSpec);
	}

	public void delete(PetDisSpec petDisSpec) {
		petDisSpecDao.delete(petDisSpec);
	}

	public PetDisSpec findByPetDisName(String petDisSpecName) {
		return petDisSpecDao.findByPetDisName(petDisSpecName);
	}

}
