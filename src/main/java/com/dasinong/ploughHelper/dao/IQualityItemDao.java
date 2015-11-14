package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.QualityItem;

public interface IQualityItemDao extends IEntityDao<QualityItem> {

	public abstract QualityItem findByQualityItemName(String qualityItemName);

}