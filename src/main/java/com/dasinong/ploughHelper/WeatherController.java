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

@Controller
public class WeatherController extends BaseController {

	IWeatherFacade wf;
	
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
	public Object loadWeather(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = this.getLoginUser(request);
				
		Map<String,Object> result = new HashMap<String,Object>();
		IWeatherFacade wf = (IWeatherFacade) ContextLoader.getCurrentWebApplicationContext().getBean("weatherFacade");
		
		if (user==null){
			double lat;
			double lon;
			try{
				lat = Double.parseDouble(request.getParameter("lat"));
				lon = Double.parseDouble(request.getParameter("lon"));
			}catch (Exception e){
				throw new InvalidParameterException("lat;lat","double;double");
				//result.put("respCode", 306);
				//result.put("message", "用户未登陆,请输入浮点格式lat,lon");
				//return result;
			}
			return wf.getWeather(lat, lon);
		}
		
		if (user.getFields()==null || user.getFields().size()==0){
			double lat;
			double lon;
			try{
				lat = Double.parseDouble(request.getParameter("lat"));
				lon = Double.parseDouble(request.getParameter("lon"));
			}catch (Exception e){
				throw new InvalidParameterException("lat;lat","double;double");
				//result.put("respCode", 307);
				//result.put("message", "用户尚无田地,请输入浮点格式lat,lon");
				//return result;
			}
			return wf.getWeather(lat, lon);
		}
	
		int mlid;
		try{
			mlid =  Integer.parseInt(request.getParameter("monitorLocationId"));
		}catch(Exception e){
			throw new InvalidParameterException("monitorLocationId","Integer");
		}
		if (mlid==-1){
			double lat;
			double lon;
			try{
				lat = Double.parseDouble(request.getParameter("lat"));
				lon = Double.parseDouble(request.getParameter("lon"));
			}catch (Exception e){
				throw new InvalidParameterException("lat;lat","double;double");
				//result.put("respCode", 315);
				//result.put("message", "使用当前位置,请输入浮点格式lat,lon");
				//return result;
			}
			return wf.getWeather(lat, lon);
		}
		return wf.getWeather(mlid);
	}

}
