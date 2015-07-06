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

import com.dasinong.ploughHelper.facade.IHomeFacade;
import com.dasinong.ploughHelper.facade.ILaoNongFacade;
import com.dasinong.ploughHelper.model.User;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
   
	@RequestMapping(value = "/home", produces="application/json")
	@ResponseBody
	public Object home(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		IHomeFacade hf = (IHomeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("homeFacade");
		HashMap<String,Object> result = new HashMap<String,Object>();
		//用户未登陆,或用户没有田地,需根据地点获取基础信息
		if (user==null){
			double lat;
			double lon;
			try{
				lat = Double.parseDouble(request.getParameter("lat"));
				lon = Double.parseDouble(request.getParameter("lon"));
			}catch (Exception e){
				result.put("respCode", 306);
				result.put("message", "用户未登陆,请输入浮点格式lat,lon");
				return result;
			}
			return hf.LoadHome(lat, lon);
		}
		
		if (user.getFields()==null||user.getFields().size()==0){
			double lat;
			double lon;
			try{
				lat = Double.parseDouble(request.getParameter("lat"));
				lon = Double.parseDouble(request.getParameter("lon"));
			}catch (Exception e){
				result.put("respCode", 307);
				result.put("message", "用户尚未创建田地,请输入浮点格式lat,lon");
				return result;
			}
			return hf.LoadHome(lat, lon);
		}

		String fieldId = request.getParameter("fieldId");
		Long fId;
		if (fieldId==null || fieldId.equals("")){
			fId=user.getFields().iterator().next().getFieldId(); //用户没有指定田,默认使用第一片
		}else{
			try{
				fId = Long.parseLong(fieldId);
			}catch(Exception e){
				result.put("respCode", 310);
				result.put("message", "fieldId参数错误内容格式错误");
				return result;
			}
		}
		//如果没有田地,输入fieldId=-1;
		if (fId==-1L){
			double lat;
			double lon;
			try{
				lat = Double.parseDouble(request.getParameter("lat"));
				lon = Double.parseDouble(request.getParameter("lon"));
			}catch (Exception e){
				result.put("respCode", 315);
				result.put("message", "使用当前位置，请输入浮点格式lat,lon");
				return result;
			}
			return hf.LoadHome(lat, lon);
		}
		return hf.LoadHome(user, fId);
		
	}
	
	
	
	
	@RequestMapping(value = "/getLaoNong", produces="application/json")
	@ResponseBody
	public Object getLaoNong(HttpServletRequest request, HttpServletResponse response) {
		
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		ILaoNongFacade lnf = (ILaoNongFacade) ContextLoader.getCurrentWebApplicationContext().getBean("laoNongFacade");
		//用户未登陆,或用户没有田地,需根据地点获取基础信息
		if (user==null){
				double lat;
				double lon;
				try{
					lat = Double.parseDouble(request.getParameter("lat"));
					lon = Double.parseDouble(request.getParameter("lon"));
				}catch (Exception e){
					result.put("respCode", 306);
					result.put("message", "用户未登陆,请输入浮点格式lat,lon");
					return result;
				}
				return lnf.getLaoNong(lat, lon);
			}
				
		if (user.getFields()==null||user.getFields().size()==0){
			double lat;
			double lon;
			try{
				lat = Double.parseDouble(request.getParameter("lat"));
				lon = Double.parseDouble(request.getParameter("lon"));
			}catch (Exception e){
				result.put("respCode", 307);
				result.put("message", "用户尚未创建田地,请输入浮点格式lat,lon");
				return result;
			}
			return lnf.getLaoNong(lat, lon);
		}
		
		String monitorLocationId = request.getParameter("monitorLocationId");
		Integer mlId;
		if ( monitorLocationId==null ||  monitorLocationId.equals("")){
			 mlId=user.getFields().iterator().next().getMonitorLocationId(); //用户没有指定田,默认使用第一片
		}else{
			try{
				mlId = Integer.parseInt(monitorLocationId);
			}catch(Exception e){
				result.put("respCode", 313);
				result.put("message", "monitorLocationId参数错误内容格式错误");
				return result;
			}
		}
		//如果没有田地,输入fieldId=-1;
		if (mlId==-1){
			double lat;
			double lon;
			try{
				lat = Double.parseDouble(request.getParameter("lat"));
				lon = Double.parseDouble(request.getParameter("lon"));
			}catch (Exception e){
				result.put("respCode", 315);
				result.put("message", "使用当前位置，请输入浮点格式lat,lon");
				return result;
			}
			return lnf.getLaoNong(lat, lon);
		}
		return lnf.getLaoNong(mlId);
	}
	
}
