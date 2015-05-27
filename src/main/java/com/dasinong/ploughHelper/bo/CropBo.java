package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.CropDao;
import com.dasinong.ploughHelper.model.Crop;

public class CropBo {
	CropDao cropDao;

	public void setCropDao(CropDao cropDao){
		this.cropDao = cropDao;
	}
	
	public void save(Crop crop) {
		cropDao.save(crop);
	}


	public void update(Crop crop) {
		cropDao.update(crop);
	}

	public void delete(Crop crop) {
		cropDao.delete(crop);
	}

	public Crop findByCropName(String cropName) {
		return cropDao.findByCropName(cropName);
	}

}
