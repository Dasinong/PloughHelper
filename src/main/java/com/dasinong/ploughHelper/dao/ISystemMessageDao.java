package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.SystemMessage;

public interface ISystemMessageDao extends IEntityDao<SystemMessage> {

	public List<SystemMessage> findAllValid();
}
