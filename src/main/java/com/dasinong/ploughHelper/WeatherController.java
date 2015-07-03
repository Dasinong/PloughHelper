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
import org.xml.sax.SAXException;

import com.dasinong.ploughHelper.facade.WeatherFacade;
import com.dasinong.ploughHelper.model.User;

@Controller
public class WeatherController {

	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
		
		/**
		 * Simply selects the home view to render by returning its name.
		 * @throws SAXException 
		 * @throws ParserConfigurationException 
		 * @throws ParseException 
		 * @throws IOException 
		 * @throws NumberFormatException 
		 */
	@RequestMapping(value = "/loadWeather", produces="application/json")
	@ResponseBody
	public Object loadWeather(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException, ParseException, ParserConfigurationException, SAXException {

		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		int mlid;
		try{
			mlid =  Integer.parseInt(request.getParameter("monitorLocationId"));
		}catch(Exception e){
			result.put("respCode",300);
			result.put("message","参数错误");
			return result;
		}
		
		WeatherFacade wf = new WeatherFacade();
		return wf.getWeather(mlid);
	}

}
