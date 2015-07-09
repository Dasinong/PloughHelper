package com.dasinong.ploughHelper.facade;

import java.util.ArrayList;
import java.util.HashMap;





import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.IStepDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Step;
import com.dasinong.ploughHelper.model.TaskSpec;
import com.dasinong.ploughHelper.outputWrapper.StepWrapper;
import com.dasinong.ploughHelper.outputWrapper.TaskSpecWrapper;

@Transactional
public class TaskSpecFacade implements ITaskSpecFacade {
	
	ITaskSpecDao taskSpecDao;
	IFieldDao fieldDao;
	IStepDao stepDao;
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.ITaskSpecFacade#getTaskSpec(java.lang.Long)
	 */
	@Override
	public Object getTaskSpec(Long taskSpecId) {
		
		//TODO: issue with this path. a.Too slow. b.Empty substage.
		taskSpecDao = (ITaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			TaskSpec taskspec = taskSpecDao.findById(taskSpecId);
			TaskSpecWrapper tsw = new TaskSpecWrapper(taskspec);
			result.put("respCode",200);
			result.put("message","获得任务描述");
			result.put("data", tsw);
			return result;	
		}catch(Exception e){
			result.put("respCode",500);
			result.put("message",e.getMessage());
			return result;
		}
	}
	
	@Override
	public Object getSteps(Long taskSpecId,Long fieldId) {
		stepDao = (IStepDao) ContextLoader.getCurrentWebApplicationContext().getBean("stepDao");
		fieldDao = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			List<Step> steps = stepDao.findByTaskSpecId(taskSpecId);
			Field field = fieldDao.findById(fieldId);
			String region = field.getLocation().getRegion();
			List<StepWrapper> vaildS = new ArrayList<StepWrapper>();
			if (steps==null){
				result.put("respCode",400);
				result.put("message","未找到合适step");
				return result;
			}
			Set<String> pictures = new HashSet<String>();
			for (Step s :  steps){
				if (s.getFitRegion().contains(region)){
					StepWrapper sw = new StepWrapper(s);
					String[] pictureNames = s.getPicture().split(",");
					if (pictureNames!=null && pictureNames.length>=1){
						if (pictures.contains(pictureNames[0])){
							sw.setPicture("");
						}
						else{
							pictures.add(pictureNames[0]);
						}
					}
					vaildS.add(sw);
				}
			}
			
			result.put("respCode",200);
			result.put("message","获得任务描述");
			result.put("data", vaildS);
			return result;	
		}catch(Exception e){
			result.put("respCode",500);
			result.put("message",e.getMessage());
			return result;
		}
	}

}
