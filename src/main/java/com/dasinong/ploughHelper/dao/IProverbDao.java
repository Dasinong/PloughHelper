package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.Proverb;

public interface IProverbDao extends IEntityDao<Proverb> {

	public abstract Proverb findByLunarCalender(String lunar);

	public abstract Proverb findByWeather(String weather);

	public abstract Proverb findByMonth(String month);

	public abstract Proverb findByAccident();

}