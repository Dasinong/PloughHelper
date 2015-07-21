package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.CPProduct;

public interface ICPProductDao {

	public abstract void save(CPProduct cPProduct);

	public abstract void update(CPProduct cPProduct);

	public abstract void delete(CPProduct cPProduct);

	public abstract CPProduct findByRegisterationId(String registerationId);
	
	public abstract CPProduct findById(Long id);

	List<CPProduct> findByIngredient(String ingredient);

}