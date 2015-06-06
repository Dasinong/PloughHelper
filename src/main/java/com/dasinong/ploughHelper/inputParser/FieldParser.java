package com.dasinong.ploughHelper.inputParser;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.User;
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
        FieldDao fd = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
        try{
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
            field.setLocation(lId);
            field.setVariety(vId);*/
        }
        catch(Exception e){
        	throw e;
        }
       
       
	}
	public Field getField() {
		return field;
	}
}
