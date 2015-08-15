package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.TaskRegion;

public interface ITaskRegionDao {

	List<TaskRegion> findByTaskRegion(String region);

}
