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
import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.dao.IWeatherSubscriptionDao;
import com.dasinong.ploughHelper.facade.IStoreFacade;
import com.dasinong.ploughHelper.model.Store;
import com.dasinong.ploughHelper.model.StoreSource;
import com.dasinong.ploughHelper.model.StoreStatus;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

@Controller
public class StoreController extends RequireUserLoginController {

	@RequestMapping(value = "/stores", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object reorderWeatherSubscriptions(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IStoreFacade facade = (IStoreFacade) ContextLoader.getCurrentWebApplicationContext().getBean("storeFacade");
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
		StoreSource source = StoreSource.values()[requestX.getInt("source")];

		return facade.create(user, name, desc, locationId, streetAndNumber, latitude, longtitude, ownerName, phone,
				source, type);
	}
}
