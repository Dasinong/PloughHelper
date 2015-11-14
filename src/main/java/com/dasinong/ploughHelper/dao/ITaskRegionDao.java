package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.TaskRegion;

public interface ITaskRegionDao extends IEntityDao<TaskRegion> {

	List<TaskRegion> findByTaskRegion(String region);

}
