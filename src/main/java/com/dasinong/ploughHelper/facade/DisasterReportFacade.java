/**
 * 
 */
package com.dasinong.ploughHelper.facade;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IDisasterReportDao;
import com.dasinong.ploughHelper.model.DisasterReport;


/**
 * @author Dell
 *
 */
@Transactional
public class DisasterReportFacade implements IDisasterReportFacade {

	IDisasterReportDao idrd;

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IDisasterReportFacade#disasterReport(java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Object insertDisasterReport(String cropName, Long fieldId,
			String disasterType, String disasterName, String affectedArea,
			String eruptionTime, String disasterDist, String fieldOperations,
			String imageFilenames) throws NullPointerException{
		
		HashMap<String,Object> result  = new HashMap<String,Object>();
    	DisasterReport dr = new DisasterReport(cropName, fieldId, disasterType, disasterName, affectedArea,
    			eruptionTime, disasterDist, fieldOperations, imageFilenames);
    	idrd = (IDisasterReportDao) ContextLoader.getCurrentWebApplicationContext().getBean("disasterReportDao");
    	idrd.save(dr);
    	
    	result.put("respCode", 200);
    	result.put("message", "插入成功");
    	return result;
	}
	
	public static void main(String[] args) {
		DisasterReportFacade drf = new DisasterReportFacade();
		String cropName = "水稻";
		Long fieldId = 1L;
		String disasterType = "病害";
		String disasterName = "稻飞虱";
		String affectedArea = "根";
		String eruptionTime = "突然爆发";
		String disasterDist = "整片田地都受灾害"; //disaster distribution 灾害分布
		String fieldOperations = "近期打药";
		String imageFilenames = "虫害照片1.jpg"; //存放图片文件名，多个文件名用逗号分隔，最多六张图片
		drf.insertDisasterReport(cropName, fieldId, disasterType, disasterName, affectedArea, eruptionTime, disasterDist, fieldOperations, imageFilenames);
	}

}