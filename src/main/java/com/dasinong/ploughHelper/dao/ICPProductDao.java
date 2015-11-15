package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.CPProduct;

public interface ICPProductDao extends IEntityDao<CPProduct> {

	public abstract CPProduct findByRegisterationId(String registerationId);

	List<CPProduct> findByIngredient(String ingredient);

}