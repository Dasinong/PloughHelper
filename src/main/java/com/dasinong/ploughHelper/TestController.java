package com.dasinong.ploughHelper;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.bo.CropBo;
import com.dasinong.ploughHelper.bo.FieldBo;
import com.dasinong.ploughHelper.bo.LocationBo;
import com.dasinong.ploughHelper.bo.NatDisBo;
import com.dasinong.ploughHelper.bo.SubStageBo;
import com.dasinong.ploughHelper.bo.VarietyBo;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.Variety;

@Controller
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/testIniCroFieVar", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tIniCroFieVar(HttpServletRequest request, HttpServletResponse response) {
	
		FieldBo fieldBo = (FieldBo) ContextLoader.getCurrentWebApplicationContext().getBean("fieldBo");
		VarietyBo varietyBo = (VarietyBo) ContextLoader.getCurrentWebApplicationContext().getBean("varietyBo");
		CropBo cropBo = (CropBo) ContextLoader.getCurrentWebApplicationContext().getBean("cropBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Crop crop = new Crop();
			crop.setCropName("水稻");
			crop.setOther("Big father");
			cropBo.save(crop);
			
			Variety variety1 = new Variety();
			variety1.setVarietyName("TestVariety1");
			variety1.setCrop(crop);
			variety1.setOther("This is for test1");
			varietyBo.save(variety1);
		
			Variety variety2 = new Variety();
			variety2.setVarietyName("TestVariety2");
			variety2.setCrop(crop);
			variety2.setOther("This is for test2.Two field assotication with this one");
			varietyBo.save(variety2);
				
		    Field field = new Field();
		    field.setFieldName("Test field 1");
		    field.setVariety(variety1);
		    field.setOther("Associate with variety1");
		    variety1.getFields().add(field);
		    fieldBo.save(field);
		
		    
		    field = new Field();
		    field.setFieldName("Test field 2");
		    field.setVariety(variety2);
		    field.setOther("Associate with variety2");
		    variety2.getFields().add(field);
		    fieldBo.save(field);
		    
		    field = new Field();
		    field.setFieldName("Test field 3");
		    field.setVariety(variety2);
		    field.setOther("Associate with variety2");
		    variety2.getFields().add(field);
		    fieldBo.save(field);
		    
		    
			System.out.println("Done");
			result.put("test","testoutputcheck");
			result.put("status",200);
			
		return result;
		}
		catch(Exception e)
		{
			result.put("status", "500");
			result.put("cause", e.getCause());
			return result;
		}
	}
	
	@RequestMapping(value = "/testChaCroVar", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tChaCroVar(HttpServletRequest request, HttpServletResponse response) {
		
		CropBo cropBo = (CropBo) ContextLoader.getCurrentWebApplicationContext().getBean("cropBo");
		VarietyBo varietyBo = (VarietyBo) ContextLoader.getCurrentWebApplicationContext().getBean("varietyBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
		  Crop cropo = cropBo.findByCropName("水稻");
		  
		  Crop crop = new Crop();
		  crop.setCropName("水稻2");
		  crop.setOther("Big father2");
		  cropBo.save(crop);
		  
		  Variety variety1 = varietyBo.findByVarietyName("TestVariety1");
		  variety1.setCrop(crop);
		  
		  cropo.getVarieties().remove(variety1);
		  crop.getVarieties().add(variety1);

		  varietyBo.update(variety1);

		  System.out.println("Done");
		  result.put("test","testoutputcheck");
		  result.put("status",200);
		
		  return result;
		}
		catch(Exception e)
		{
			result.put("status", "500");
			result.put("info","Run /testIniCroFieVar first");
			result.put("cause", e.getCause());
			return result;
		}
	}
	
	
	@RequestMapping(value = "/testiLocFie", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tiLocFie(HttpServletRequest request, HttpServletResponse response) {
		
		LocationBo locationBo = (LocationBo) ContextLoader.getCurrentWebApplicationContext().getBean("locationBo");
		FieldBo fieldBo = (FieldBo) ContextLoader.getCurrentWebApplicationContext().getBean("fieldBo");
		VarietyBo varietyBo = (VarietyBo) ContextLoader.getCurrentWebApplicationContext().getBean("varietyBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
	      Location location1 = new Location();
	      location1.setLocationName("上海");
	      location1.setOther("has field 1 and 2");
	      locationBo.save(location1);
	          
		  Field field = new Field();
		  field.setIsActive(true);
		  field.setFieldName("上海1");
		  field.setLocation(location1);
		  Variety variety1=varietyBo.findByVarietyName("TestVariety1");
		  field.setVariety(variety1);
		  field.setOther("特殊他");
		  fieldBo.save(field);
		  
		  field = new Field();
		  field.setIsActive(false);
		  field.setFieldName("上海历史");
		  field.setLocation(location1);
		  Variety variety2=varietyBo.findByVarietyName("TestVariety2");
		  field.setVariety(variety2);
		  field.setOther("特殊地");
		  fieldBo.save(field);
    
		  System.out.println("Done");
		  result.put("test","testoutputcheck");
		  result.put("status",200);
		
		  return result;
		}
		catch(Exception e)
		{
			result.put("status", "500");
			result.put("info","Run /testIniCroFieVar first");
			result.put("cause", e.getCause());
			return result;
		}
	}
	
	
	
	@RequestMapping(value = "/tesiVarSuS", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tiVarSuS(HttpServletRequest request, HttpServletResponse response) {
		
		VarietyBo varietyBo = (VarietyBo) ContextLoader.getCurrentWebApplicationContext().getBean("varietyBo");
		SubStageBo subStageBo = (SubStageBo) ContextLoader.getCurrentWebApplicationContext().getBean("subStageBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Variety variety1 = varietyBo.findByVarietyName("TestVariety1");
			Variety variety2 = varietyBo.findByVarietyName("TestVariety2");
			SubStage ss1 = new SubStage();
			SubStage ss2 = new SubStage();
			ss1.setSubStageName("m1s1");
			ss1.setStageName("m1");
			ss2.setSubStageName("m1s2");
			ss2.setStageName("m1");
			ss1.getVarieties().add(variety1);
			ss1.getVarieties().add(variety2);
			ss2.getVarieties().add(variety1);
			subStageBo.save(ss1);
			subStageBo.save(ss2);
			variety1.getSubStages().add(ss1);
			variety1.getSubStages().add(ss2);
			variety2.getSubStages().add(ss1);

		    
			System.out.println("Done");
			result.put("test","testoutputcheck");
			result.put("status",200);
			
		return result;
		}
		catch(Exception e)
		{
			result.put("status", "500");
			result.put("cause", e.getCause());
			return result;
		}
	}
	
	
	
	@RequestMapping(value = "/tesiSusDis", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tiSuSDis(HttpServletRequest request, HttpServletResponse response) {
		
		
		SubStageBo subStageBo = (SubStageBo) ContextLoader.getCurrentWebApplicationContext().getBean("subStageBo");
		NatDisBo natDisBo = (NatDisBo) ContextLoader.getCurrentWebApplicationContext().getBean("natDisBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			SubStage ss1 = subStageBo.findBySubStageName("m1s1");
			SubStage ss2 = subStageBo.findBySubStageName("m1s2");
			
			NatDis natDis1 = new NatDis();
			NatDis natDis2 = new NatDis();
			natDis1.setNatDisName("一号病");
			natDis2.setNatDisName("二号病");
			natDis1.getSubStages().add(ss1);
			natDis1.getSubStages().add(ss2);
			natDis2.getSubStages().add(ss1);
			
			natDisBo.save(natDis1);
			natDisBo.save(natDis2);
			

			ss1.getNatDiss().add(natDis1);
			ss1.getNatDiss().add(natDis2);
			ss2.getNatDiss().add(natDis1);
			
			for (NatDis di:ss1.getNatDiss()){
				System.out.println(di.getNatDisName());
			}
			
		    
			System.out.println("Done");
			result.put("test","testoutputcheck");
			result.put("status",200);
			
		return result;
		}
		catch(Exception e)
		{
			result.put("status", "500");
			result.put("cause", e.getCause());
			return result;
		}
	}

}
