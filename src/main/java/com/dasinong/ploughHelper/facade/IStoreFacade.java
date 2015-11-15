package com.dasinong.ploughHelper.facade;

import com.dasinong.ploughHelper.model.StoreSource;
import com.dasinong.ploughHelper.model.User;

public interface IStoreFacade {

	public Object create(User user, String name, String desc, Long locationId, String streetAndNumber, Double latitude,
			Double longtitude, String ownerName, String phone, StoreSource source, int type) throws Exception;
}
