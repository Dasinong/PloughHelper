package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.NatDisSpec;

public interface INatDisSpecDao {

	public abstract void save(NatDisSpec natDisSpec);

	public abstract void update(NatDisSpec natDisSpec);

	public abstract void delete(NatDisSpec natDisSpec);

	public abstract NatDisSpec findByNatDisSpecName(String natDisSpecName);

	public abstract NatDisSpec findById(Long id);

}