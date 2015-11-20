package com.dasinong.ploughHelper.daotest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dasinong.ploughHelper.dao.IInstitutionDao;
import com.dasinong.ploughHelper.dao.IStoreDao;
import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.daotest.utils.TestDataUtils;
import com.dasinong.ploughHelper.model.Institution;
import com.dasinong.ploughHelper.model.Store;
import com.dasinong.ploughHelper.model.StoreSource;
import com.dasinong.ploughHelper.model.StoreStatus;
import com.dasinong.ploughHelper.model.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/webapp/WEB-INF/spring/beans/ModelBeans.xml",
		"file:./src/main/webapp/WEB-INF/spring/database/TestDataSource.xml",
		"file:./src/main/webapp/WEB-INF/spring/database/Hibernate.xml" })
public class StoreDaoTest {

	@Autowired
	private IStoreDao storeDao;

	@Autowired
	private IUserDao userDao;

	@Test
	public void testCRUD() {
		// Create a store
		User owner = TestDataUtils.genRandomUser();
		userDao.save(owner);
		Store store = TestDataUtils.genRandomStore(owner.getUserId(), 1L);
		storeDao.save(store);
		Long storeId = store.getId();
		Store savedStore = storeDao.findById(storeId);

		// Assert the first store is created correctly
		Assert.assertEquals(store.getName(), savedStore.getName());
		Assert.assertEquals(store.getDescription(), savedStore.getDescription());
		Assert.assertEquals(store.getOwnerId(), savedStore.getOwnerId());
		Assert.assertEquals(store.getLocationId(), savedStore.getLocationId());
		Assert.assertEquals(store.getStreetAndNumber(), savedStore.getStreetAndNumber());
		Assert.assertEquals(store.getCellphone(), savedStore.getCellphone());
		Assert.assertEquals(store.getLatitude(), savedStore.getLatitude());
		Assert.assertEquals(store.getLongtitude(), savedStore.getLongtitude());
		Assert.assertEquals(store.getPhone(), savedStore.getPhone());
		Assert.assertEquals(store.getCellphone(), savedStore.getCellphone());
		Assert.assertEquals(store.getStatus(), savedStore.getStatus());
		Assert.assertEquals(store.getSource(), savedStore.getSource());
		Assert.assertEquals(store.getType(), savedStore.getType());
		Assert.assertEquals(store.getCreatedAt(), savedStore.getCreatedAt());
		Assert.assertEquals(store.getUpdatedAt(), savedStore.getUpdatedAt());

		// Create another score
		Store anotherStore = TestDataUtils.genRandomStore(owner.getUserId(), 2L);
		storeDao.save(anotherStore);
		Long anotherStoreId = anotherStore.getId();
		List<Store> stores = storeDao.findAll();

		// Assert both can be found
		Assert.assertEquals(stores.size(), 2);
		Assert.assertEquals(stores.get(0).getId(), store.getId());
		Assert.assertEquals(stores.get(1).getId(), anotherStore.getId());

		// Delete store
		storeDao.delete(store);
		storeDao.delete(anotherStore);
		store = storeDao.findById(storeId);
		Assert.assertNull(store);
		store = storeDao.findById(anotherStoreId);
		Assert.assertNull(store);

		// Clean test user
		userDao.delete(owner);
	}
}
