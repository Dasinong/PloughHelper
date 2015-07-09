package com.dasinong.ploughHelper.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.dao.ISubStageDao;
import com.dasinong.ploughHelper.dao.ITaskDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.dao.IVarietyDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.TaskSpec;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.Variety;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;
import com.dasinong.ploughHelper.weather.AllLocation;

@Transactional
public class FieldFacade implements IFieldFacade {

	IFieldDao fd;
	ITaskSpecDao taskSpecDao;
	ITaskDao taskDao;
	ILocationDao ldDao; 
    IVarietyDao varietyDao;
    ISubStageDao subStageDao;
    IPetDisSpecDao petDisSpecDao;
	
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
		taskDao = (ITaskDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskDao");
		petDisSpecDao = (IPetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
	    
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
	        	 if (variety.getSubStages()!=null && variety.getSubStages().size()!=0){
	        		 csid = variety.getSubStages().iterator().next().getSubStageId();
	        	 }
	        	 else csid=0L;
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
	         
	         //初始化所有常见任务
	         if (variety.getSubStages()!=null){
		         for(SubStage ss : variety.getSubStages()){
		        	if (ss.getTaskSpecs()!=null){
		        		for (TaskSpec ts : ss.getTaskSpecs()){
		        			 Task t = new Task(ts,false);
		        			 t.setFieldId(field.getFieldId());
		        			 taskDao.save(t);
		        			 if (field.getTasks()==null) field.setTasks(new HashMap<Long,Task>());
		        			 field.getTasks().put(t.getTaskId(), t);
		        		}
		        	 }
		         }
	         }
	        
	        if (user.getFields()!=null){
	        	user.getFields().add(field);
	        }
	        else{
	        	user.setFields(new HashSet<Field>());
	        	user.getFields().add(field);
	        }

			FieldWrapper fw = new FieldWrapper(field,taskSpecDao,1);
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
	
	@Override
	public Object addWeatherAlert(NatDis natdis){
		
		Map<String,Object> result = new HashMap<String,Object>();

		return result;
		
	}

	@Override
	public Object changeField(Long fieldId, Long currentStageId) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		fd = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		taskSpecDao = (ITaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		Field f = fd.findById(fieldId);
		f.setCurrentStageID(currentStageId);
		fd.update(f);
		FieldWrapper fw = new FieldWrapper(f,taskSpecDao,2);
		result.put("respCode", 200);
		result.put("message", "添加田地成功");
		result.put("data",fw);
		return result;
	}
}
