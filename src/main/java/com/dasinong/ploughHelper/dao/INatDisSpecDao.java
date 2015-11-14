package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.NatDisSpec;

public interface INatDisSpecDao extends IEntityDao<NatDisSpec> {

	public abstract NatDisSpec findByNatDisSpecName(String natDisSpecName);

}