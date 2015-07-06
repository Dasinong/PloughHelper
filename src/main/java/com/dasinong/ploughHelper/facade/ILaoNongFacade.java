package com.dasinong.ploughHelper.facade;

public interface ILaoNongFacade {

	public abstract Object getLaoNong(double lat, double lon);

	public abstract Object getLaoNong(Integer areaId);

}