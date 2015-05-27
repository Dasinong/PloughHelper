package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.NatDisDao;
import com.dasinong.ploughHelper.model.NatDis;

public class NatDisBo {
	NatDisDao natDisDao;

	public void setNatDisDao(NatDisDao natDisDao){
		this.natDisDao = natDisDao;
	}
	
	public void save(NatDis natDis) {
		natDisDao.save(natDis);
	}


	public void update(NatDis natDis) {
		natDisDao.update(natDis);
	}

	public void delete(NatDis natDis) {
		natDisDao.delete(natDis);
	}

	public NatDis findByNatDisName(String natDisName) {
		return natDisDao.findByNatDisName(natDisName);
	}

}
