package com.dasinong.ploughHelper.inputParser;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.dao.LocationDao;
import com.dasinong.ploughHelper.dao.SubStageDao;
import com.dasinong.ploughHelper.dao.VarietyDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.Variety;

public class FieldParser {
	boolean isValid;
	private Field field;
	public FieldParser(HttpServletRequest request ) throws Exception{
		String fieldName =  request.getParameter("fieldName");
		String isActive =  request.getParameter("isActive");
		String seedingortransplant = request.getParameter("seedingortransplant");
		String area = request.getParameter("area");
        String startDate =  request.getParameter("startDate");
        String locationId = request.getParameter("locationId");
		String varietyId =  request.getParameter("varietyId");
        String currentStageId = request.getParameter("currentStageID");
        LocationDao ldDao = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
        VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
        SubStageDao subStageDao = (SubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao");

       	boolean isA = Boolean.getBoolean(isActive);
       	boolean sot = Boolean.parseBoolean(seedingortransplant);
       	double ar = Double.parseDouble(area);
       	long lId = Long.parseLong(locationId);
       	long vId = Long.parseLong(varietyId);
       	long cSd = Long.parseLong(currentStageId);
       	field = new Field();
        field.setFieldName(fieldName);
        field.setIsActive(isA);
        field.setSeedortrans(sot);
        field.setArea(ar);
        /*
        field.setStartDate(startDate);
        */
        Location location = ldDao.findById(lId);
        Variety variety = varietyDao.findById(vId);
        if (location==null || variety ==null){
         	Exception e = new Exception("参数无效");
          	throw e;
        }
        field.setLocation(location);
        field.setVariety(variety);
        field.setCurrentStageID(cSd);
        
        Date date = new Date(startDate);
       
	}
	public Field getField() {
		return field;
	}
}
