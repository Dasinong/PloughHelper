package com.dasinong.ploughHelper.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dasinong.ploughHelper.model.SubScribeList;

public interface ISubScribeListDao extends IEntityDao<SubScribeList> {

	public abstract List<SubScribeList> findByOwnerId(Long oid);

}