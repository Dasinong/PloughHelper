package com.dasinong.ploughHelper;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IStoreDao;
import com.dasinong.ploughHelper.dao.IWeatherSubscriptionDao;
import com.dasinong.ploughHelper.model.Store;
import com.dasinong.ploughHelper.model.StoreSource;
import com.dasinong.ploughHelper.model.StoreStatus;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

@Controller
public class StoreController extends RequireUserLoginController {

	@RequestMapping(value = "/stores", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object reorderWeatherSubscriptions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IStoreDao storeDao = (IStoreDao) ContextLoader.getCurrentWebApplicationContext().getBean("storeDao");
		User user = this.getLoginUser(request);
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		
		String name = requestX.getString("name");
		String desc = requestX.getString("description");
		Long locationId = requestX.getLong("locationId");
		String streetAndNumber = requestX.getString("streetAndNumber");
		Double latitude = requestX.getDoubleOptional("latitude", null);
		Double longtitude = requestX.getDoubleOptional("longtitude", null);
		String ownerName = requestX.getString("ownerName");
		String phone = requestX.getString("phone");
		int type = requestX.getInt("type");
		
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
		
		Map<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("store", store);
		result.put("respCode", 200);
		result.put("message", "农资店创建成功");
		result.put("data", data);
		return result;
	}
}
