package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Institution;

public interface IInstitutionDao {

	void save(Institution institution);

	void update(Institution institution);

	void delete(Institution institution);

	List<Institution> findAll();

	Institution findById(int id);

	Institution findByName(String name);

	Institution findByCode(String code);

}