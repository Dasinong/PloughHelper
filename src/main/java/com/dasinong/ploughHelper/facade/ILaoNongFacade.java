package com.dasinong.ploughHelper.facade;

import com.dasinong.ploughHelper.model.User;

public interface ILaoNongFacade {

	public abstract Object getLaoNong(double lat, double lon, User user);

	public abstract Object getLaoNong(Integer areaId, User user);

}