package com.dasinong.ploughHelper.bo;

import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.model.Field;

public class FieldBo {
	FieldDao fieldDao;

	public void setFieldDao(FieldDao fieldDao){
		this.fieldDao = fieldDao;
	}
	
	public void save(Field field) {
		fieldDao.save(field);
	}


	public void update(Field field) {
		fieldDao.update(field);
	}

	public void delete(Field field) {
		fieldDao.delete(field);
	}

	public Field findByFieldName(String fieldName) {
		return fieldDao.findByFieldName(fieldName);
	}

}
