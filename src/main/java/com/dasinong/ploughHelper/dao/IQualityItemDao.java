package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.QualityItem;

public interface IQualityItemDao {

	public abstract void save(QualityItem qualityItem);

	public abstract void update(QualityItem qualityItem);

	public abstract void delete(QualityItem qualityItem);

	public abstract QualityItem findByQualityItemName(String qualityItemName);

}