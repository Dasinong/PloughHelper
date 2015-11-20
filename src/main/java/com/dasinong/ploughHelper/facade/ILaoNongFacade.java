package com.dasinong.ploughHelper.facade;

import com.dasinong.ploughHelper.model.User;

public interface ILaoNongFacade {
	
	public abstract Object getLaoNongs_DEPRECATED(double lat, double lon, User user);

	public abstract Object getLaoNongs_DEPRECATED(Long areaId, User user);

	public abstract Object getLaoNongs(double lat, double lon, User user);

	public abstract Object getLaoNongs(Long areaId, User user);

}
