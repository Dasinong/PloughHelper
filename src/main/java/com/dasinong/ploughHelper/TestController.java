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
import com.dasinong.ploughHelper.bo.PetDisBo;
import com.dasinong.ploughHelper.bo.QualityItemBo;
import com.dasinong.ploughHelper.bo.QualityItemValueBo;
import com.dasinong.ploughHelper.bo.StepBo;
import com.dasinong.ploughHelper.bo.SubStageBo;
import com.dasinong.ploughHelper.bo.TaskBo;
import com.dasinong.ploughHelper.bo.TaskSpecBo;
import com.dasinong.ploughHelper.bo.VarietyBo;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.QualityItem;
import com.dasinong.ploughHelper.model.QualityItemValue;
import com.dasinong.ploughHelper.model.Step;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.TaskSpec;
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
	
	@RequestMapping(value = "/teslLocNaD", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tlLocNaD(HttpServletRequest request, HttpServletResponse response) {
		
		
		LocationBo locationBo = (LocationBo) ContextLoader.getCurrentWebApplicationContext().getBean("locationBo");
		NatDisBo natDisBo = (NatDisBo) ContextLoader.getCurrentWebApplicationContext().getBean("natDisBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Location l1 = locationBo.findByLocationName("上海");

			
			NatDis natDis1 = natDisBo.findByNatDisName("一号病");
			NatDis natDis2 = natDisBo.findByNatDisName("二号病");

            l1.getNatDiss().add(natDis1);
            l1.getNatDiss().add(natDis2);
			
            locationBo.update(l1);
			
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
	
	
	@RequestMapping(value = "/tesiPeDLocSta", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tiPeDLocSta(HttpServletRequest request, HttpServletResponse response) {
		
		
		LocationBo locationBo = (LocationBo) ContextLoader.getCurrentWebApplicationContext().getBean("locationBo");
		PetDisBo petDisBo = (PetDisBo) ContextLoader.getCurrentWebApplicationContext().getBean("petDisBo");
		SubStageBo subStageBo = (SubStageBo) ContextLoader.getCurrentWebApplicationContext().getBean("subStageBo") ;
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Location l1 = locationBo.findByLocationName("上海");
			SubStage ss1 = subStageBo.findBySubStageName("m1s1");
			SubStage ss2 = subStageBo.findBySubStageName("m1s2");
			
			PetDis petDis1 = new PetDis();
			PetDis petDis2 = new PetDis();
			petDis1.setPetDisName("刮风");
			petDis2.setPetDisName("下雨");

			petDisBo.save(petDis1);
			petDisBo.save(petDis2);
			
            l1.getPetDiss().add(petDis1);
            l1.getPetDiss().add(petDis2);
			locationBo.update(l1);
			
            ss1.getPetDiss().add(petDis1);
            ss2.getPetDiss().add(petDis1);
            ss2.getPetDiss().add(petDis2);
            
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

	@RequestMapping(value = "/tesiCroQuI", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tiCroQuI(HttpServletRequest request, HttpServletResponse response) {
		
		CropBo cropBo = (CropBo) ContextLoader.getCurrentWebApplicationContext().getBean("cropBo");
		QualityItemBo qualityItemBo = (QualityItemBo) ContextLoader.getCurrentWebApplicationContext().getBean("qualityItemBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Crop crop = cropBo.findByCropName("水稻");
			QualityItem qi1 = new QualityItem();
			QualityItem qi2 = new QualityItem();
			QualityItem qi3 = new QualityItem();
			qi1.setQualityItemName("香味");
			qi1.setCrop(crop);
			qi2.setQualityItemName("抗药性");
			qi2.setCrop(crop);
			qi3.setQualityItemName("抗旱");
			qi3.setCrop(crop);
			qualityItemBo.save(qi1);
			qualityItemBo.save(qi2);
			qualityItemBo.save(qi3);
			
			for(QualityItem q: crop.getQualityItems()){
				System.out.println(q.getQualityItemName());
			}
			crop.getQualityItems().add(qi1);
			crop.getQualityItems().add(qi2);
			crop.getQualityItems().add(qi3);
			for(QualityItem q: crop.getQualityItems()){
				System.out.println(q.getQualityItemName());
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
	
	
	@RequestMapping(value = "/tesiSuSTaSSte", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tiSuSTaSSte(HttpServletRequest request, HttpServletResponse response) {
		
		SubStageBo subStageBo = (SubStageBo) ContextLoader.getCurrentWebApplicationContext().getBean("subStageBo");
		TaskSpecBo taskSpecBo = (TaskSpecBo) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecBo");
		StepBo stepBo = (StepBo) ContextLoader.getCurrentWebApplicationContext().getBean("stepBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			SubStage ss1 = subStageBo.findBySubStageName("m1s1");
			SubStage ss2 = subStageBo.findBySubStageName("m1s2");
			TaskSpec task1 = new TaskSpec();
			TaskSpec task2 = new TaskSpec();
			task1.setTaskSpecName("种田");
			task1.setSubStage(ss1);
			ss1.getTaskSpecs().add(task1);
					
			task2.setTaskSpecName("浇水");
			task2.setSubStage(ss2);
			ss2.getTaskSpecs().add(task2);
			
			taskSpecBo.save(task1);
			taskSpecBo.save(task2);
			
			Step step11 = new Step();
			step11.setStepName("种田1");
			step11.setTaskSpec(task1);
			task1.getSteps().add(step11);
			Step step12 = new Step();
			step12.setStepName("种田2");
			step12.setTaskSpec(task1);
			task1.getSteps().add(step12);
			Step step21 = new Step();
			step21.setStepName("浇水1");
			step21.setTaskSpec(task2);
			task2.getSteps().add(step21);
			Step step22 = new Step();
			step22.setStepName("浇水2");
			step22.setTaskSpec(task2);
			task2.getSteps().add(step22);
			
			stepBo.save(step11);
			stepBo.save(step12);
			stepBo.save(step21);
			stepBo.save(step22);
			
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
	
	
	@RequestMapping(value = "/tesmTask", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tmTask(HttpServletRequest request, HttpServletResponse response) {
		FieldBo fieldbo = (FieldBo) ContextLoader.getCurrentWebApplicationContext().getBean("fieldBo");
		Field field1 = fieldbo.findByFieldName("上海1");
		Field field2 = fieldbo.findByFieldName("上海历史");
		TaskBo taskbo = (TaskBo) ContextLoader.getCurrentWebApplicationContext().getBean("taskBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			HashMap<Long, Task> set1 = new HashMap<Long, Task>();
			Task task11=new Task(false);
			Task task12=new Task(false);
			Task task21=new Task(false);
			Task task22=new Task(true);
			
			taskbo.save(task11);
			taskbo.save(task12);
			taskbo.save(task21);
			taskbo.save(task22);
			
			set1.put(10L, task11);
			set1.put(11L, task12);
			
			field1.setTasks(set1);
			fieldbo.update(field1);
			
			HashMap<Long, Task> set2 = new HashMap<Long, Task>();
			set2.put(11L, task21);
			set2.put(12L, task22);
			field2.setTasks(set2);
			fieldbo.update(field2);
			
		return result;
		}
		catch(Exception e)
		{
			result.put("status", "500");
			result.put("cause", e.getCause());
			return result;
		}
	}
	
	
	@RequestMapping(value = "/tesmQIV", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tmQIV(HttpServletRequest request, HttpServletResponse response) {
		VarietyBo varietybo = (VarietyBo) ContextLoader.getCurrentWebApplicationContext().getBean("varietyBo");
		Variety variety1 = varietybo.findByVarietyName("TestVariety1");
		Variety variety2 = varietybo.findByVarietyName("TestVariety2");
		QualityItemValueBo qivbo = (QualityItemValueBo) ContextLoader.getCurrentWebApplicationContext().getBean("qualityItemValueBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			HashMap<Long, QualityItemValue> set1 = new HashMap<Long, QualityItemValue>();
			QualityItemValue qiv11=new QualityItemValue(10L,"1");
			QualityItemValue qiv12=new QualityItemValue(11L,"1");
			QualityItemValue qiv21=new QualityItemValue(10L,"1");
			QualityItemValue qiv22=new QualityItemValue(12L,"1");
			
			qivbo.save(qiv11);
			qivbo.save(qiv12);
			qivbo.save(qiv21);
			qivbo.save(qiv22);
			
			set1.put(qiv11.getQualityItemId(), qiv11);
			set1.put(qiv12.getQualityItemId(), qiv12);
			
			variety1.setQualityItemValues(set1);
			varietybo.update(variety1);
			
			HashMap<Long, QualityItemValue> set2 = new HashMap<Long, QualityItemValue>();
			set2.put(qiv21.getQualityItemId(), qiv21);
			set2.put(qiv22.getQualityItemId(), qiv22);
			variety2.setQualityItemValues(set2);
			varietybo.update(variety2);
			
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
