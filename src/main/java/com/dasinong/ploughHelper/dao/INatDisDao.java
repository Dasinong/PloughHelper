package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.NatDis;

public interface INatDisDao extends IEntityDao<NatDis> {

	public abstract NatDis findByNatDisName(String natDisName);

}