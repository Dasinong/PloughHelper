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
import com.dasinong.ploughHelper.bo.NatDisSpecBo;
import com.dasinong.ploughHelper.bo.PetDisBo;
import com.dasinong.ploughHelper.bo.PetDisSpecBo;
import com.dasinong.ploughHelper.bo.PetSoluBo;
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
import com.dasinong.ploughHelper.model.NatDisSpec;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.PetSolu;
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
		NatDisSpecBo natDisBo = (NatDisSpecBo) ContextLoader.getCurrentWebApplicationContext().getBean("natDisSpecBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			SubStage ss1 = subStageBo.findBySubStageName("m1s1");
			SubStage ss2 = subStageBo.findBySubStageName("m1s2");
			
			NatDisSpec natDis1 = new NatDisSpec();
			NatDisSpec natDis2 = new NatDisSpec();
			natDis1.setNatDisSpecName("刮风");
			natDis2.setNatDisSpecName("下雨");
			natDis1.getSubStages().add(ss1);
			natDis1.getSubStages().add(ss2);
			natDis2.getSubStages().add(ss1);
			
			natDisBo.save(natDis1);
			natDisBo.save(natDis2);
			

			ss1.getNatDisSpecs().add(natDis1);
			ss1.getNatDisSpecs().add(natDis2);
			ss2.getNatDisSpecs().add(natDis1);
			subStageBo.update(ss1);
			subStageBo.update(ss2);
			
			for (NatDisSpec di:ss1.getNatDisSpecs()){
				System.out.println(di.getNatDisSpecName());
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
		NatDisSpecBo natDisBo = (NatDisSpecBo) ContextLoader.getCurrentWebApplicationContext().getBean("natDisSpecBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Location l1 = locationBo.findByLocationName("上海");

			
			NatDisSpec natDis1 = natDisBo.findByNatDisName("刮风");
			NatDisSpec natDis2 = natDisBo.findByNatDisName("下雨");

            l1.getNatDisSpecs().add(natDis1);
            l1.getNatDisSpecs().add(natDis2);
			
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
		PetDisSpecBo petDisBo = (PetDisSpecBo) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecBo");
		SubStageBo subStageBo = (SubStageBo) ContextLoader.getCurrentWebApplicationContext().getBean("subStageBo") ;
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Location l1 = locationBo.findByLocationName("上海");
			SubStage ss1 = subStageBo.findBySubStageName("m1s1");
			SubStage ss2 = subStageBo.findBySubStageName("m1s2");
			
			PetDisSpec petDis1 = new PetDisSpec();
			PetDisSpec petDis2 = new PetDisSpec();
			petDis1.setPetDisSpecName("一号病");
			petDis2.setPetDisSpecName("二号病");

			petDisBo.save(petDis1);
			petDisBo.save(petDis2);
			
            l1.getPetDisSpecs().add(petDis1);
            l1.getPetDisSpecs().add(petDis2);
			locationBo.update(l1);
			
            ss1.getPetDisSpecs().add(petDis1);
            ss2.getPetDisSpecs().add(petDis1);
            ss2.getPetDisSpecs().add(petDis2);
            subStageBo.update(ss1);
            subStageBo.update(ss2);
            
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
	
	
	
	@RequestMapping(value = "/tesmPeDs", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tmPeD(HttpServletRequest request, HttpServletResponse response) {
		FieldBo fieldbo = (FieldBo) ContextLoader.getCurrentWebApplicationContext().getBean("fieldBo");
		Field field1 = fieldbo.findByFieldName("上海1");
		Field field2 = fieldbo.findByFieldName("上海历史");
		PetDisBo petDisbo = (PetDisBo) ContextLoader.getCurrentWebApplicationContext().getBean("petDisBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			HashMap<Long, PetDis> set1 = new HashMap<Long, PetDis>();
			PetDis petDis11=new PetDis(false);
			PetDis petDis12=new PetDis(false);
			PetDis petDis21=new PetDis(false);
			PetDis petDis22=new PetDis(true);
			
			petDisbo.save(petDis11);
			petDisbo.save(petDis12);
			petDisbo.save(petDis21);
			petDisbo.save(petDis22);
			
			set1.put(10L, petDis11);
			set1.put(11L, petDis12);
			
			field1.setPetDiss(set1);
			fieldbo.update(field1);
			
			HashMap<Long, PetDis> set2 = new HashMap<Long, PetDis>();
			set2.put(11L, petDis21);
			set2.put(12L, petDis22);
			field2.setPetDiss(set2);
			fieldbo.update(field2);
			
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
	
	@RequestMapping(value = "/tesmNaDs", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tmNaD(HttpServletRequest request, HttpServletResponse response) {
		FieldBo fieldbo = (FieldBo) ContextLoader.getCurrentWebApplicationContext().getBean("fieldBo");
		Field field1 = fieldbo.findByFieldName("上海1");
		Field field2 = fieldbo.findByFieldName("上海历史");
		NatDisBo natDisbo = (NatDisBo) ContextLoader.getCurrentWebApplicationContext().getBean("natDisBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			HashMap<Long, NatDis> set1 = new HashMap<Long, NatDis>();
			NatDis natDis11=new NatDis(false);
			NatDis natDis12=new NatDis(false);
			NatDis natDis21=new NatDis(false);
			NatDis natDis22=new NatDis(true);
			
			natDisbo.save(natDis11);
			natDisbo.save(natDis12);
			natDisbo.save(natDis21);
			natDisbo.save(natDis22);
			
			set1.put(10L, natDis11);
			set1.put(11L, natDis12);
			
			field1.setNatDiss(set1);
			fieldbo.update(field1);
			
			HashMap<Long, NatDis> set2 = new HashMap<Long, NatDis>();
			set2.put(11L, natDis21);
			set2.put(12L, natDis22);
			field2.setNatDiss(set2);
			fieldbo.update(field2);
			
			
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
	
	
	
	@RequestMapping(value = "/tesiPDSSol", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tiPDSSol(HttpServletRequest request, HttpServletResponse response) {
		PetDisSpecBo petDisSpecbo = (PetDisSpecBo) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecBo");
		PetSoluBo psbo = (PetSoluBo) ContextLoader.getCurrentWebApplicationContext().getBean("petSoluBo");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			HashMap<Long, PetDis> set1 = new HashMap<Long, PetDis>();
			PetDisSpec ps1 = petDisSpecbo.findByPetDisName("一号病");
			PetDisSpec ps2 = petDisSpecbo.findByPetDisName("二号病");
			
			PetSolu petSolu11 = new PetSolu();
			PetSolu petSolu12 = new PetSolu();
			PetSolu petSolu21 = new PetSolu();
			PetSolu petSolu22 = new PetSolu();
			PetSolu petSolu23 = new PetSolu();
			petSolu11.setPetSoluName("治疗一号病1");
			petSolu11.setPetDisSpec(ps1);
			petSolu11.setCure(true);
			petSolu11.setOther("This is for test");
			petSolu12.setPetSoluName("预防一号病2");
			petSolu12.setPetDisSpec(ps1);
			petSolu12.setCure(false);
			petSolu12.setOther("This is for test");
			petSolu21.setPetSoluName("治疗二号病1");
			petSolu21.setPetDisSpec(ps2);
			petSolu21.setCure(true);
			petSolu21.setOther("This is for test");
			petSolu22.setPetSoluName("预防二号病2");
			petSolu22.setPetDisSpec(ps2);
			petSolu22.setCure(false);
			petSolu22.setOther("This is for test");
			petSolu23.setPetSoluName("治疗二号病3");
			petSolu23.setPetDisSpec(ps2);
			petSolu23.setCure(true);
			petSolu23.setOther("This is for test");
			
			psbo.save(petSolu11);
			psbo.save(petSolu12);
			psbo.save(petSolu21);
			psbo.save(petSolu22);
			psbo.save(petSolu23);
			
            ps1.getPetSolus().add(petSolu11);
            ps1.getPetSolus().add(petSolu12);
            ps2.getPetSolus().add(petSolu21);
            ps2.getPetSolus().add(petSolu22);
            ps2.getPetSolus().add(petSolu23);
            
			
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
