package com.dasinong.ploughHelper.facade;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ICPProductDao;
import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.dao.IVarietyDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.SoilTestReport;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;
import com.dasinong.ploughHelper.outputWrapper.SoilTestReportWrapper;
import com.dasinong.ploughHelper.util.LunarHelper;

public class BaikeFacade {
	IVarietyDao varietyDao;
	IPetDisSpecDao petDisSpecDao;
	ICPProductDao cPProductDao;
	
	/*
	@Override
	public Object LoadHome(User user,String fieldId){
		fieldDao = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		taskSpecDao = (ITaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (user.getFields()==null){
			result.put("respCode",110);
			result.put("message","用户没有田地，请先添加");
			return result;
		}
		
		HashMap<String,Long> fieldList = new HashMap<String,Long>();
		for (Field f: user.getFields()){
			fieldList.put(f.getFieldName(),f.getFieldId());
		}
		result.put("fieldList",fieldList);

		Calendar today = Calendar.getInstance();
		Map<String,String> date = new HashMap<String,String>();
		date.put("date", ""+today.getTime().getDate());
		date.put("day", ""+today.getTime().getDay());
		LunarHelper lh = new LunarHelper(today);
		String jieqi = lh.getJieQi();
		if (jieqi.equals("")){
			date.put("lunar", lh.getDay());
		}
		else{
			date.put("lunar", jieqi);
		}
		
		result.put("date", date);
		
		if (fieldId==null || fieldId.equals("")){
			result.put("respCode", 200);
			result.put("Message", "读取田地成功");
			Field f = user.getFields().iterator().next();
			FieldWrapper fw = new FieldWrapper(f,taskSpecDao);
			result.put("currentField",fw);
			
			long latest=-1;
			SoilTestReport latestR=null;
			for (SoilTestReport str : f.getSoilTestReports()){
				if (str.soilTestReportId>latest){
					latestR = str;
					latest =str.soilTestReportId;
				}
			}
			if (latestR!=null){
				SoilTestReportWrapper strw = new SoilTestReportWrapper(latestR);
				result.put("latestReport", strw);
			}
			return result;
		}
		else{
			Long fid = Long.parseLong(fieldId);
			Field f= fieldDao.findById(fid);
			if (f==null){
				result.put("respCode",120);
				result.put("message","fieldId不存在");
				return result;
			}else{
				result.put("respCode", 200);
				result.put("message", "读取田地成功");
				FieldWrapper fw = new FieldWrapper(f,taskSpecDao);
				result.put("currentField",fw);
				
				long latest=-1;
				SoilTestReport latestR=null;
				for (SoilTestReport str : f.getSoilTestReports()){
					if (str.soilTestReportId>latest){
						latestR = str;
						latest =str.soilTestReportId;
					}
				}
				if (latestR!=null){
					SoilTestReportWrapper strw = new SoilTestReportWrapper(latestR);
					result.put("latestReport", strw);
				}
				return result;
			}
		}
	}
	*/
}
