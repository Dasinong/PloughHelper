package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.PetDisDao;
import com.dasinong.ploughHelper.model.PetDis;

public class PetDisBo {
	PetDisDao petDisDao;

	public void setPetDisDao(PetDisDao petDisDao){
		this.petDisDao = petDisDao;
	}
	
	public void save(PetDis petDis) {
		petDisDao.save(petDis);
	}


	public void update(PetDis petDis) {
		petDisDao.update(petDis);
	}

	public void delete(PetDis petDis) {
		petDisDao.delete(petDis);
	}

	public PetDis findByPetDisName(String petDisName) {
		return petDisDao.findByPetDisName(petDisName);
	}

}
