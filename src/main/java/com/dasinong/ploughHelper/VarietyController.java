package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ICropDao;
import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.IVarietyDao;
import com.dasinong.ploughHelper.facade.IVarietyFacade;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.Variety;


@Controller
public class VarietyController {
	
	private static final Logger logger = LoggerFactory.getLogger(VarietyController.class);

	@RequestMapping(value = "/getVarietyList",produces="application/json")
	@ResponseBody
	public Object getVariety(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> result = new HashMap<String,Object>();
		String cropName = request.getParameter("cropName");
		String cropId = request.getParameter("cropId");
		String locationId = request.getParameter("locationId");
		String province = request.getParameter("province");
		if (cropId!=null&&!cropId.equals("")&&province!=null&&!province.equals("")){
			IVarietyFacade vf = (IVarietyFacade) ContextLoader.getCurrentWebApplicationContext().getBean("varietyFacade");
			Long cropid;
			try{
				cropid = Long.parseLong(cropId);
			}catch(Exception e){
				result.put("respCode", 305);
				result.put("message","cropId内容格式错误");
		        return result;
			}
			return vf.getVariety(cropid, province);
		}
		if (cropId!=null&&!cropId.equals("")&&locationId!=null&&!locationId.equals("")){
			IVarietyFacade vf = (IVarietyFacade) ContextLoader.getCurrentWebApplicationContext().getBean("varietyFacade");
			Long cropid;
			try{
				cropid = Long.parseLong(cropId);
			}catch(Exception e){
				result.put("respCode", 315);
				result.put("message","cropId内容格式错误");
		        return result;
			}
			Long locationid;
			try{
				locationid = Long.parseLong(locationId);
			}catch(Exception e){
				result.put("respCode", 316);
				result.put("message","locationId内容格式错误");
		        return result;
			}
			return vf.getVariety(cropid, locationid);
		}
		if (cropName!=null&&!cropName.equals("")&&province!=null&&!province.equals("")){
			IVarietyFacade vf = (IVarietyFacade) ContextLoader.getCurrentWebApplicationContext().getBean("varietyFacade");
			return vf.getVariety(cropName, province);
		}
		if (cropName!=null&&!cropName.equals("")&&locationId!=null&&!locationId.equals("")){
			IVarietyFacade vf = (IVarietyFacade) ContextLoader.getCurrentWebApplicationContext().getBean("varietyFacade");
			Long locationid;
			try{
				locationid = Long.parseLong(locationId);
			}catch(Exception e){
				result.put("respCode", 316);
				result.put("message","locationId内容格式错误");
		        return result;
			}
			return vf.getVariety(cropName, locationid);
		}
		result.put("respCode", 300);
		result.put("message", "缺少参数");
		return result;
	}

}
