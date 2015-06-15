package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.NatDis;

public interface INatDisDao {

	public abstract void save(NatDis natDis);

	public abstract void update(NatDis natDis);

	public abstract void delete(NatDis natDis);

	public abstract NatDis findByNatDisName(String natDisName);

}