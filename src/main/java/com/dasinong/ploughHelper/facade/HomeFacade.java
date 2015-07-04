package com.dasinong.ploughHelper.facade;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ISoilTestReportDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.SoilTestReport;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;
import com.dasinong.ploughHelper.outputWrapper.SoilTestReportWrapper;
import com.dasinong.ploughHelper.util.LunarHelper;
import com.dasinong.ploughHelper.weather.All7dHum;
import com.dasinong.ploughHelper.weather.AllLocation;
import com.dasinong.ploughHelper.weather.MonitorLocation;
import com.dasinong.ploughHelper.weather.SevenDayHumidity;
import com.dasinong.ploughHelper.weather.SoilLiquid;

@Transactional
public class HomeFacade implements IHomeFacade {
	
   // @Autowired
    private IFieldDao fieldDao;
    
  //  @Autowired
    private ITaskSpecDao taskSpecDao;
    
    //  @Autowired
    private ISoilTestReportDao soilReportDao;
    

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IHomeFacade#LoadHome(com.dasinong.ploughHelper.model.User, java.lang.String)
	 */
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
		
		HashMap<String,Long> fieldList = new HashMap<String,Long>();
		if (user.getFields()==null || user.getFields().size()==0){
			result.put("respCode", 400);
			result.put("message", "该用户没有田地");
			return result;
		}
		
		for (Field f: user.getFields()){
			fieldList.put(f.getFieldName(),f.getFieldId());
		}
		result.put("fieldList",fieldList);
	
		Field f=null;
		if (fieldId==null || fieldId.equals("")){
			f = user.getFields().iterator().next();
		}
		else{
			Long fid;
			try{
				fid = Long.parseLong(fieldId);
			}
			catch(Exception e){
				result.put("respCode",300);
				result.put("message","fieldId参数错误");
				return result;
			}
			f= fieldDao.findById(fid);
		}
		if (f==null){
			result.put("respCode",120);
			result.put("message","fieldId不存在");
			return result;
		}
		
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
				
		MonitorLocation ml=null;
		try {
			ml = AllLocation.getLocation().getMonitorLocation(f.getMonitorLocationId());
			double soilHum = SoilLiquid.getSoilLi().getSoil(ml.latitude, ml.longitude);
			result.put("soilHum", soilHum);
		} catch (IOException |ParseException e) {
			System.out.println("Load monitor location field 偶然 failed");
		}
	
		result.put("respCode", 200);
		result.put("message", "读取田地成功");
		return result;
	}	
}
