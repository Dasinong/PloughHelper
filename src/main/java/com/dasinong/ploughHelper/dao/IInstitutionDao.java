package com.dasinong.ploughHelper.dao;

import java.util.List;

import com.dasinong.ploughHelper.model.Institution;

public interface IInstitutionDao extends IEntityDao<Institution> {

	Institution findByName(String name);

	Institution findByCode(String code);

}