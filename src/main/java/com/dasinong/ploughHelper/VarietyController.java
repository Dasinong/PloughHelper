package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.dasinong.ploughHelper.dao.UserDao;
import com.dasinong.ploughHelper.inputParser.UserParser;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.Variety;
import com.dasinong.ploughHelper.outputWrapper.UserWrapper;

@Controller
public class VarietyController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/getVarietyList",produces="application/json")
	@ResponseBody
	public Object getVariety(HttpServletRequest request, HttpServletResponse response) {
		IVarietyDao varietyDao = (IVarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			String cropName = request.getParameter("cropName");
			String cropId = request.getParameter("cropId");
			String locationId = request.getParameter("locationId");
			String province = request.getParameter("province");
			if ((cropName==null && cropId==null)||(locationId==null && province==null)){
				result.put("respCode", 300);
				result.put("message", "缺少参数");
				return result;
			}
			
			long cId;
			if (cropId==null){
				ICropDao cropDao = (ICropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
				Crop crop = cropDao.findByCropName(cropName);
				cId = crop.getCropId();
			}
			else{
				cId = Long.parseLong(cropId);
			}
			String p;
			if (province==null){
				ILocationDao locationDao = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
				Location l = locationDao.findById(Long.parseLong(locationId));
				p = l.getProvince();
			}
			else{
				p = province;
			}
				
			List<Variety> lv = varietyDao.findByCropRegion(cId, p);
			Map<String,HashMap<String,Long>> vlist = new  HashMap<String,HashMap<String,Long>>();
			for (Variety v: lv){
				if (!vlist.containsKey(v.getVarietyName())){
					HashMap<String,Long> nrec = new HashMap<String,Long>();
					nrec.put(v.getSubId(),v.getVarietyId());
					vlist.put(v.getVarietyName(), nrec);
				}
				else{
					vlist.get(v.getVarietyName()).put(v.getSubId(), v.getVarietyId());
				}
				
			}
			
			result.put("respCode",200);
			result.put("message", "加载品种列表成功");
			result.put("data",vlist);
			
			return result;
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("message", e.getMessage());
			return result;
		}
	}

}
