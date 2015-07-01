package com.dasinong.ploughHelper.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.ISubStageDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.dao.IVarietyDao;
import com.dasinong.ploughHelper.inputParser.FieldParser;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.Variety;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;
import com.dasinong.ploughHelper.weather.AllLocation;

@Transactional
public class FieldFacade implements IFieldFacade {

	IFieldDao fd;
	ITaskSpecDao taskSpecDao;
	ILocationDao ldDao; 
    IVarietyDao varietyDao;
    ISubStageDao subStageDao;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IFieldFacade#createField(com.dasinong.ploughHelper.model.User, com.dasinong.ploughHelper.inputParser.FieldParser)
	 */
	@Override
	public Object createField(User user, String fieldName, Date startDate,
			boolean isActive, boolean seedingortransplant, double area,
			long locationId, long varietyId, String currentStageId,String yield) {
		fd = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		ldDao = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		varietyDao = (IVarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		subStageDao = (ISubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao");
		taskSpecDao = (ITaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
	    
	    Map<String,Object> result = new HashMap<String,Object>();
	    try {
	    	 Location location = ldDao.findById(locationId);
	         Variety variety = varietyDao.findById(varietyId);
	         if (location==null || variety ==null){
	          	Exception e = new Exception("locationId或varietyId无效");
	           	throw e;
	         }
	         Long csid; 
	         Long yie;
	         if (currentStageId == null || currentStageId.equalsIgnoreCase("")){
	        	 csid = variety.getSubStages().iterator().next().getSubStageId();
	         }
	         else{
	        	 csid = Long.parseLong(currentStageId);
	         }
	         if (yield == null || yield.equalsIgnoreCase("")){
	        	 yie = 0L;
	         }
	         else{
	        	 yie = Long.parseLong(yield);
	         }
	         if (fieldName == null || fieldName.equals("")){
	        	 fieldName = location.getCommunity()+variety.getVarietyName();
	         }
	         Field field = new Field();
	         field.setFieldName(fieldName);
	         field.setIsActive(isActive);
	         field.setSeedortrans(seedingortransplant);
	         field.setArea(area);
	         field.setStartDate(startDate);
	         field.setLocation(location);
	         field.setVariety(variety);
	         field.setCurrentStageID(csid);
	         field.setUser(user);   
	         field.setYield(yie);
	         
	         double lat = location.getLatitude();
	         double lon = location.getLongtitude();
	         int monitorLocationId = AllLocation.getLocation().getNearest(lat, lon);
	         field.setMonitorLocationId(monitorLocationId);
	         fd.save(field);

 			user.getFields().add(field);

			FieldWrapper fw = new FieldWrapper(field,taskSpecDao);
			result.put("respCode", 200);
			result.put("message", "添加田地成功");
			result.put("data",fw);
			return result;
		} catch (Exception e) {
			result.put("respCode",500);
			result.put("message", e.getCause());
			return result;
		}

	}
}
