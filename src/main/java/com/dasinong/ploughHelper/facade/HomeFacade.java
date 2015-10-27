package com.dasinong.ploughHelper.facade;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.datapool.AllMonitorLocation;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.MonitorLocation;
import com.dasinong.ploughHelper.model.SoilTestReport;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;
import com.dasinong.ploughHelper.outputWrapper.SoilTestReportWrapper;
import com.dasinong.ploughHelper.util.LunarHelper;
import com.dasinong.ploughHelper.weather.SoilLiquid;

@Transactional
public class HomeFacade implements IHomeFacade {
	
   // @Autowired
    private IFieldDao fieldDao;
    
  //  @Autowired
    private ITaskSpecDao taskSpecDao;
    
    /* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IHomeFacade#LoadHome(com.dasinong.ploughHelper.model.User, java.lang.String)
	 */
	@Override
	public Object LoadHome(User user,Long fieldId,int taskType){
		//taskType means how would you like to load task related content.
		//1 for all
		//2 for current stage
		//3 for not required
		fieldDao = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		taskSpecDao = (ITaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		//Lunar Date part
		result.put("date",this.getLunar());
		
		//Put field List
		HashMap<String,Long> fieldList = new HashMap<String,Long>();
		for (Field f: user.getFields()){
			if (fieldList.keySet().contains(f.getFieldName())){
				System.out.println("Duplicated field for same user");
			}
			else{
				fieldList.put(f.getFieldName(),f.getFieldId());
			}
		}
		result.put("fieldList",fieldList);
	
		//Put field Detail
		Field f=null;
		f= fieldDao.findById(fieldId);
		if (f==null){
			result.put("respCode",120);
			result.put("message","fieldId不存在");
			return result;
		}
		
		FieldWrapper fw = new FieldWrapper(f,taskSpecDao,taskType);
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
		
		//Get Hum
		MonitorLocation ml=null;
		try {
			ml = AllMonitorLocation.getInstance().getMonitorLocation(f.getMonitorLocationId());
			if (ml!=null){
				double soilHum = SoilLiquid.getSoilLi().getSoil(ml.getLatitude(), ml.getLongitude());
				result.put("soilHum", soilHum);
			}
		} catch (Exception e) {
			System.out.println("Load monitor location field or soilLiquid failed");
		}
	
		result.put("respCode", 200);
		result.put("message", "读取田地成功");
		return result;
	}	
	
	public Object LoadHome(double lat,double lon){
		HashMap<String,Object> result = new HashMap<String,Object>();
		//Lunar Date part
		result.put("date",this.getLunar());
				
		double soilHum=0;
		try {
			soilHum = SoilLiquid.getSoilLi().getSoil(lat, lon);
		} catch (Exception e) {
            System.out.println("Load soilHum failed");
		}
		result.put("soilHum", soilHum);
		return result;
	}
	
	private Object getLunar(){
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
        return date;
	}
}
