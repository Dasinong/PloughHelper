package com.dasinong.ploughHelper.daotest.utils;

import java.sql.Timestamp;
import java.util.Random;

import com.dasinong.ploughHelper.model.Store;
import com.dasinong.ploughHelper.model.StoreSource;
import com.dasinong.ploughHelper.model.StoreStatus;
import com.dasinong.ploughHelper.model.User;

public class TestDataUtils {

	public static User genRandomUser() {
		Random rnd = new Random();
		User user = new User();
		user.setUserName("赵日天" + rnd.nextInt(99999));
		user.setAddress("断罪小学风云堂");
		user.setCreateAt(TestDataUtils.getCurrentTimeInSeconds());
		user.setAuthenticated(true);
		user.setIsPassSet(false);
		user.setCellPhone("139192" + rnd.nextInt(99999));
		return user;
	}

	public static Store genRandomStore(Long userId, Long locationId) {
		Random rnd = new Random();
		Store store = new Store();
		Timestamp now = TestDataUtils.getCurrentTimeInSeconds();

		store.setName("断罪小学第" + rnd.nextInt(9999) + "分部");
		store.setDescription("我是赵日天");
		store.setOwnerId(userId);
		store.setLocationId(locationId);
		store.setStreetAndNumber("学院路" + rnd.nextInt(9999) + "号");
		store.setLatitude(39.0);
		store.setLongtitude(119.0);
		store.setPhone("021-66253696");
		store.setCellphone("13671134134");
		store.setType(1);
		store.setStatus(StoreStatus.ACCEPTED);
		store.setSource(StoreSource.IMPORT_BY_BACKEND);
		store.setCreatedAt(now);
		store.setUpdatedAt(now);
		return store;
	}

	private static Timestamp getCurrentTimeInSeconds() {
		// % 1000 is needed because mysql stores timestamp in seconds
		// while System.currentTimeMillis returns milli seconds
		Long mseconds = System.currentTimeMillis();
		mseconds = mseconds - mseconds % 1000;
		return new Timestamp(mseconds);
	}
}