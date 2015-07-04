package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.CPProduct;

public interface ICPProductDao {

	public abstract void save(CPProduct cPProduct);

	public abstract void update(CPProduct cPProduct);

	public abstract void delete(CPProduct cPProduct);

	public abstract CPProduct findByCPProductName(String cPProductName);

	CPProduct findById(Long id);

}