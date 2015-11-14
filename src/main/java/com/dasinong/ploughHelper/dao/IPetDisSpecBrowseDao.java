package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.PetDisSpecBrowse;

public interface IPetDisSpecBrowseDao extends IEntityDao<PetDisSpecBrowse> {

	public abstract List<PetDisSpecBrowse> findByType(String type);
	
	public abstract List<PetDisSpecBrowse> findByCropIdAndType(Long cropId, String type);
	
}