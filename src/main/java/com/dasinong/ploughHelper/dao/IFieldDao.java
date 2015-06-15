package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.Field;

public interface IFieldDao {

	public abstract void save(Field field);

	public abstract void update(Field field);

	public abstract void delete(Field field);

	public abstract Field findByFieldName(String fieldName);

	public abstract Field findById(Long id);

}