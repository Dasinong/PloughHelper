package com.dasinong.ploughHelper.bo;

import java.util.Map;
import java.util.Set;

import com.dasinong.ploughHelper.dao.QualityItemValueDao;
import com.dasinong.ploughHelper.model.QualityItemValue;

public class QualityItemValueBo {
	QualityItemValueDao qualityItemValueDao;

	public void setQualityItemValueDao(QualityItemValueDao qualityItemValueDao){
		this.qualityItemValueDao = qualityItemValueDao;
	}
	
	public void save(QualityItemValue qualityItemValue) {
		qualityItemValueDao.save(qualityItemValue);
	}


	public void update(QualityItemValue qualityItemValue) {
		qualityItemValueDao.update(qualityItemValue);
	}

	public void delete(QualityItemValue qualityItemValue) {
		qualityItemValueDao.delete(qualityItemValue);
	}

	
	public Map<Long,String> findByVarietyId(Long varietyId) {
		return qualityItemValueDao.findByVarietyId(varietyId);
	}

}
