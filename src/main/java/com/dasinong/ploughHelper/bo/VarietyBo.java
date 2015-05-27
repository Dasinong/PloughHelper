package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.VarietyDao;
import com.dasinong.ploughHelper.model.Variety;



public class VarietyBo {
	VarietyDao varietyDao;

	public void setVarietyDao(VarietyDao varietyDao){
		this.varietyDao = varietyDao;
	}
	
	public void save(Variety variety) {
		varietyDao.save(variety);
	}


	public void update(Variety variety) {
		varietyDao.update(variety);
	}

	public void delete(Variety variety) {
		varietyDao.delete(variety);
	}

	public Variety findByVarietyName(String varietyName) {
		return varietyDao.findByVarietyName(varietyName);
	}

}
