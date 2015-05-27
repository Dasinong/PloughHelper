package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.SubStageDao;
import com.dasinong.ploughHelper.model.SubStage;

public class SubStageBo {
	SubStageDao subStageDao;

	public void setSubStageDao(SubStageDao subStageDao){
		this.subStageDao = subStageDao;
	}
	
	public void save(SubStage subStage) {
		subStageDao.save(subStage);
	}


	public void update(SubStage subStage) {
		subStageDao.update(subStage);
	}

	public void delete(SubStage subStage) {
		subStageDao.delete(subStage);
	}

	public SubStage findBySubStageName(String subStageName) {
		return subStageDao.findBySubStageName(subStageName);
	}

}
