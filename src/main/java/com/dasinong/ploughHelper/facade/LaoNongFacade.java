package com.dasinong.ploughHelper.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.dasinong.ploughHelper.datapool.AllSystemMessage;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.nohibernate.SystemMessage;
import com.dasinong.ploughHelper.modelTran.LaoNong;
import com.dasinong.ploughHelper.modelTran.NongYan;
import com.dasinong.ploughHelper.modelTran.WeatherAlert;
import com.dasinong.ploughHelper.weather.AllLocation;
import com.dasinong.ploughHelper.weather.GetWeatherAlert;
import com.dasinong.ploughHelper.weather.AgriDisForcast;
import com.dasinong.ploughHelper.weather.AllAgriDisForcast;

public class LaoNongFacade implements ILaoNongFacade {

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.ILaoNongFacade#getLaoNong(double, double)
	 */
	@Override
	public Object getLaoNong(double lat, double lon, User user){
		try{
			Integer mlId = AllLocation.getLocation().getNearest(lat, lon);
			return getLaoNong(mlId,user);
		}
		catch(Exception e){
			HashMap<String,Object> result = new HashMap<String,Object>();
			result.put("respCode", 405);
			result.put("message", "初始化检测地址列表出错");
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.ILaoNongFacade#getLaoNong(java.lang.Integer)
	 */
	@Override
	public Object getLaoNong(Integer areaId, User user){
		HashMap<String,Object> result = new HashMap<String,Object>();
		GetWeatherAlert gwa = new GetWeatherAlert(areaId.toString()); 
		List<WeatherAlert> wa = gwa.getWeatherAlert();
		String DisasterInfo = "";
		String AgriURLTag = "";
		List<LaoNong> newLaoNong = new ArrayList<LaoNong>();
		try{
			AgriDisForcast agri = AllAgriDisForcast.getadf().getadf(areaId);
			if (agri!=null) DisasterInfo = agri.getDisasterInfo();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error happend when get Agriculture Disaster Forcast");
		}
		
		System.out.println("szc: getLaoNong areaId : "+areaId);
		LaoNong laoNong=null;
		
		if(!"".equals(DisasterInfo)){
			laoNong = new LaoNong(0,2,"ohnoface.png","农业预警",DisasterInfo, AgriURLTag);
			result.put("data",laoNong);
			newLaoNong.add(laoNong);
		}
		if (wa!=null){
			boolean first=true;
			for(WeatherAlert w: wa){
				laoNong = new LaoNong(0,2,"ohnoface.png","天气预警",w.shortDescription(),w.urlTag());
				if (first) {
					result.put("data",laoNong);
				    first=false;
				}
				newLaoNong.add(laoNong);
			}
		} 
		List<SystemMessage> sml = AllSystemMessage.getSystemMessage().get_Messages(areaId);
		if (sml!=null && user!=null){
			for(SystemMessage sm:sml){
				if (sm.getChannel().equalsIgnoreCase(user.getChannel()) && (sm.getStartTime().getTime()< (new Date()).getTime())){
					LaoNong ln = new LaoNong(1,3,"closeeyelaugh.png","系统消息",sm.getContent(),"");
					newLaoNong.add(ln);
				}
			}
		}
		
		if (newLaoNong.size()==0){
		    laoNong = NongYan.allNongYan().getNongYan(areaId);
			result.put("data",laoNong);
			newLaoNong.add(laoNong);
		}

		result.put("respCode", 200);
		result.put("message","获得老农成功");
		result.put("newdata", newLaoNong);
		return result;
	}
}
