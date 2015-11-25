package com.dasinong.ploughHelper;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.xml.sax.SAXException;

import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.facade.IWeatherFacade;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

@Controller
public class WeatherController extends BaseController {

	IWeatherFacade wf;

	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@RequestMapping(value = "/loadWeather", produces = "application/json")
	@ResponseBody
	public Object loadWeather(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		User user = this.getLoginUser(request);

		Map<String, Object> result = new HashMap<String, Object>();
		IWeatherFacade wf = (IWeatherFacade) ContextLoader.getCurrentWebApplicationContext().getBean("weatherFacade");

		if (user == null) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return wf.getWeather(lat, lon);
		}

		Long mlid = requestX.getLongOptional("monitorLocationId", -1L);
		if (mlid == -1L) {
			double lat = requestX.getDouble("lat");
			double lon = requestX.getDouble("lon");
			return wf.getWeather(lat, lon);
		}

		return wf.getWeather(mlid);
	}

}
