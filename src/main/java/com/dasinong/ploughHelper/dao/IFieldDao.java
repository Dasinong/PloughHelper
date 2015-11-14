package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Field;

public interface IFieldDao extends IEntityDao<Field> {

	public abstract Field findByFieldName(String fieldName);

}