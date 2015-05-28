package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.QualityItemDao;
import com.dasinong.ploughHelper.model.QualityItem;

public class QualityItemBo {
	QualityItemDao qualityItemDao;

	public void setQualityItemDao(QualityItemDao qualityItemDao){
		this.qualityItemDao = qualityItemDao;
	}
	
	public void save(QualityItem qualityItem) {
		qualityItemDao.save(qualityItem);
	}


	public void update(QualityItem qualityItem) {
		qualityItemDao.update(qualityItem);
	}

	public void delete(QualityItem qualityItem) {
		qualityItemDao.delete(qualityItem);
	}

	public QualityItem findByQualityItemName(String qualityItemName) {
		return qualityItemDao.findByQualityItemName(qualityItemName);
	}

}
