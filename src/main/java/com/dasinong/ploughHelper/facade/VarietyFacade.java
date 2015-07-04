package com.dasinong.ploughHelper.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ICropDao;
import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.IVarietyDao;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.Variety;


@Transactional
public class VarietyFacade implements IVarietyFacade {
	
	IVarietyDao varietyDao;
	ICropDao cropDao;
	ILocationDao locationDao;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IVarietyFacade#getVariety(java.lang.String, long)
	 */
	@Override
	public Object getVariety(String cropName, long locationId) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		cropDao = (ICropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		locationDao = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		
		Crop crop = cropDao.findByCropName(cropName);
		if (crop==null){
			result.put("respCode", 301);
			result.put("message","作物未找到");
			return result;
		}
		Long cropId = crop.getCropId();
		
		Location l = locationDao.findById(locationId);
		if (l==null){
			result.put("respCode", 302);
			result.put("message","地址编号错误");
			return result;
		}
		String province = l.getProvince();
		
		return getVariety(cropId,province);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IVarietyFacade#getVariety(java.lang.String, java.lang.String)
	 */
	@Override
	public Object getVariety(String cropName, String province) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		cropDao = (ICropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		Crop crop = cropDao.findByCropName(cropName);
		if (crop==null){
			result.put("respCode", 301);
			result.put("message","作物未找到");
			return result;
		}
		Long cropId = crop.getCropId();
		
		return getVariety(cropId,province);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IVarietyFacade#getVariety(long, long)
	 */
	@Override
	public Object getVariety(long cropId,long locationId) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		varietyDao = (IVarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		locationDao = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		Location l = locationDao.findById(locationId);
		if (l==null){
			result.put("respCode", 302);
			result.put("message","地址编号错误");
			return result;
		}
		String province = l.getProvince();
		
		return getVariety(cropId,province);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IVarietyFacade#getVariety(long, java.lang.String)
	 */
	@Override
	public Object getVariety(long cropId,String province) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		varietyDao = (IVarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		
		try{
			List<Variety> lv = varietyDao.findByCropRegion(cropId, province);
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
			
			if (lv.size()==0){
				lv = varietyDao.findGenericVariety(cropId);
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
			}
			
			result.put("respCode",200);
			result.put("message", "加载品种列表成功");
			result.put("data",vlist);
			
			return result;
		}catch(Exception e){
			result.put("respCode",500);
			result.put("message", e.getMessage());
			return result;
		}
	}
}

