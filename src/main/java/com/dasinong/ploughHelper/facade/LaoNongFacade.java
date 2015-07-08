package com.dasinong.ploughHelper.facade;

import java.util.HashMap;
import java.util.Random;

import com.dasinong.ploughHelper.modelTran.LaoNong;
import com.dasinong.ploughHelper.modelTran.NongYan;
import com.dasinong.ploughHelper.modelTran.WeatherAlert;
import com.dasinong.ploughHelper.weather.AllLocation;
import com.dasinong.ploughHelper.weather.GetWeatherAlert;

public class LaoNongFacade implements ILaoNongFacade {

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.ILaoNongFacade#getLaoNong(double, double)
	 */
	@Override
	public Object getLaoNong(double lat, double lon){
		try{
			Integer mlId = AllLocation.getLocation().getNearest(lat, lon);
			return getLaoNong(mlId);
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
	public Object getLaoNong(Integer areaId){
		HashMap<String,Object> result = new HashMap<String,Object>();
		GetWeatherAlert gwa = new GetWeatherAlert(areaId.toString()); 
		WeatherAlert wa = gwa.getWeatherAlert();
		if (wa!=null){
			result.put("respCode", 200);
			result.put("message","获得老农成功");
			LaoNong laoNong = new LaoNong(0,2,"ohnoface.png","天气预警",wa.shortDescription(),wa.urlTag());
			result.put("data",laoNong);
			return result;
		}
		
		LaoNong laoNong = NongYan.allNongYan().getRndNongYan();
		result.put("respCode", 200);
		result.put("message","获得老农成功");
		result.put("data",laoNong);
		return result;
	}
}
