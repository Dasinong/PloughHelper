package com.dasinong.ploughHelper.facade;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IStoreDao;
import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.exceptions.UserTypeAlreadyDefinedException;
import com.dasinong.ploughHelper.model.Store;
import com.dasinong.ploughHelper.model.StoreSource;
import com.dasinong.ploughHelper.model.StoreStatus;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.UserType;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

@Transactional
public class StoreFacade implements IStoreFacade {

	@Override
	public Object create(User user, String name, String desc, Long locationId, String streetAndNumber, Double latitude,
			Double longtitude, String ownerName, String phone, StoreSource source, int type) throws Exception {
		IStoreDao storeDao = (IStoreDao) ContextLoader.getCurrentWebApplicationContext().getBean("storeDao");
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");

		// If store is added from registration flow, user type should be null.
		if (source.equals(StoreSource.REGISTRATION) && user.getUserType() != null) {
			throw new UserTypeAlreadyDefinedException(user.getUserId(), user.getUserType());
		}

		Store store = new Store();
		store.setName(name);
		store.setDescription(desc);
		store.setLocationId(locationId);
		store.setStreetAndNumber(streetAndNumber);
		store.setOwnerName(ownerName);
		store.setOwnerId(user.getUserId());
		store.setPhone(phone);
		store.setLatitude(latitude);
		store.setLongtitude(longtitude);
		store.setType(type);
		store.setSource(StoreSource.REGISTRATION);
		store.setStatus(StoreStatus.PENDING);
		store.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		store.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		storeDao.save(store);

		// If store is added from registration flow, make user retailer
		if (source.equals(StoreSource.REGISTRATION)) {
			user.setUserType(UserType.RETAILER);
			userDao.update(user);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("store", store);
		result.put("respCode", 200);
		result.put("message", "农资店创建成功");
		result.put("data", data);
		return result;
	}

}
