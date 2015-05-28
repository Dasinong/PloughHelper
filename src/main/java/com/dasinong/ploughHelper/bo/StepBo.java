package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.StepDao;
import com.dasinong.ploughHelper.model.Step;

public class StepBo {
	StepDao stepDao;

	public void setStepDao(StepDao stepDao){
		this.stepDao = stepDao;
	}
	
	public void save(Step step) {
		stepDao.save(step);
	}


	public void update(Step step) {
		stepDao.update(step);
	}

	public void delete(Step step) {
		stepDao.delete(step);
	}

	public Step findByStepName(String stepName) {
		return stepDao.findByStepName(stepName);
	}

}
