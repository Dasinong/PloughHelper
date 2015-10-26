package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.PetDisSpecBrowse;

public interface IPetDisSpecBrowseDao {

	public abstract void save(PetDisSpecBrowse petDisSpecBrowse);
	
	public abstract void update(PetDisSpecBrowse petDisSpecBrowse);

	public abstract PetDisSpecBrowse findById(Long id);

	public abstract List<PetDisSpecBrowse> findByType(String type);
	
	public abstract List<PetDisSpecBrowse> findByCropIdAndType(Long cropId, String type);

	public abstract List<PetDisSpecBrowse> getAll();
	
}