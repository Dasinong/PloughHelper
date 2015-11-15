package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.exceptions.UnexpectedLatAndLonException;
import com.dasinong.ploughHelper.facade.IHomeFacade;
import com.dasinong.ploughHelper.facade.ILaoNongFacade;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

@Controller
public class HomeController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/home", produces = "application/json")
	@ResponseBody
	public Object home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = this.getLoginUser(request);
		IHomeFacade hf = (IHomeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("homeFacade");
		HttpServletRequestX requestX = new HttpServletRequestX(request);

		// 用户未登陆,或用户没有田地,需根据地点获取基础信息
		if (user == null) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return hf.LoadHome(lat, lon);
		}

		if (user.getFields() == null || user.getFields().size() == 0) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return hf.LoadHome(lat, lon);
		}

		Long fId = requestX.getLongOptional("fieldId", null);
		if (fId == null) {
			fId = user.getFields().iterator().next().getFieldId(); // 用户没有指定田,默认使用第一片
		}

		// 如果没有田地,输入fieldId=-1;
		if (fId == -1L) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return hf.LoadHome(lat, lon);
		}

		// 是否加载a.全部任务 b.当前阶段任务 c.不加在任务
		String taskLoadType = requestX.getString("task");
		if (taskLoadType == null || taskLoadType.equals("")) {
			return hf.LoadHome(user, fId, 1);
		} else if (taskLoadType.equals("all")) {
			return hf.LoadHome(user, fId, 1);
		} else if (taskLoadType.equals("currentStage")) {
			return hf.LoadHome(user, fId, 2);
		} else if (taskLoadType.equals("none")) {
			return hf.LoadHome(user, fId, 3);
		}
		return hf.LoadHome(user, fId, 1);
	}

	// TODO (xiahonggao): deprecate /getLaoNong and use /laonongs instead
	@RequestMapping(value = "/getLaoNong", produces = "application/json")
	@ResponseBody
	public Object getLaoNong(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = this.getLoginUser(request);
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		ILaoNongFacade lnf = (ILaoNongFacade) ContextLoader.getCurrentWebApplicationContext().getBean("laoNongFacade");

		// 用户未登陆,或用户没有田地,需根据地点获取基础信息
		if (user == null) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return lnf.getLaoNong(lat, lon, user);
		}

		if (user.getFields() == null || user.getFields().size() == 0) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return lnf.getLaoNong(lat, lon, user);
		}

		Integer mlId = requestX.getIntOptional("monitorLocationId", null);
		if (mlId == null) {
			mlId = user.getFields().iterator().next().getMonitorLocationId(); // 用户没有指定田,默认使用第一片
		}

		// 如果没有田地,输入fieldId=-1;
		if (mlId == -1) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return lnf.getLaoNong(lat, lon, user);
		}
		return lnf.getLaoNong(mlId, user);
	}

	@RequestMapping(value = "/laonongs", produces = "application/json")
	@ResponseBody
	public Object getBanners(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = this.getLoginUser(request);
		ILaoNongFacade lnf = (ILaoNongFacade) ContextLoader.getCurrentWebApplicationContext().getBean("laoNongFacade");
		HttpServletRequestX requestX = new HttpServletRequestX(request);

		if (user == null) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return lnf.getLaoNong(lat, lon, user);
		}

		Integer mlId = requestX.getInt("monitorLocationId");
		return lnf.getLaoNong(mlId, user);
	}

}
