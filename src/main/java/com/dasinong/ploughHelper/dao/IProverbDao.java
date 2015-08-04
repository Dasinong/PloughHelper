package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.Proverb;

public interface IProverbDao {

	public abstract void save(Proverb proverb);

	public abstract void update(Proverb proverb);

	public abstract void delete(Proverb proverb);

	public abstract Proverb findById(Long id);
	
	public abstract Proverb findByLunarCalender(String lunar);
	
	public abstract Proverb findByWeather(String weather);
	
	public abstract Proverb findByMonth(String month);
	
	public abstract Proverb findByAccident();

}