package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.NatDisSpecDao;
import com.dasinong.ploughHelper.model.NatDisSpec;

public class NatDisSpecBo {
	NatDisSpecDao natDisSpecDao;

	public void setNatDisSpecDao(NatDisSpecDao natDisSpecDao){
		this.natDisSpecDao = natDisSpecDao;
	}
	
	public void save(NatDisSpec natDisSpec) {
		natDisSpecDao.save(natDisSpec);
	}


	public void update(NatDisSpec natDisSpec) {
		natDisSpecDao.update(natDisSpec);
	}

	public void delete(NatDisSpec natDisSpec) {
		natDisSpecDao.delete(natDisSpec);
	}

	public NatDisSpec findByNatDisName(String natDisSpecName) {
		return natDisSpecDao.findByNatDisName(natDisSpecName);
	}

}
