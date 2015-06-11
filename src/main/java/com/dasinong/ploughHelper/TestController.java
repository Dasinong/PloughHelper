package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.CropDao;
import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.dao.LocationDao;
import com.dasinong.ploughHelper.dao.NatDisDao;
import com.dasinong.ploughHelper.dao.NatDisSpecDao;
import com.dasinong.ploughHelper.dao.PetDisDao;
import com.dasinong.ploughHelper.dao.PetDisSpecDao;
import com.dasinong.ploughHelper.dao.PetSoluDao;
import com.dasinong.ploughHelper.dao.QualityItemDao;
import com.dasinong.ploughHelper.dao.QualityItemValueDao;
import com.dasinong.ploughHelper.dao.StepDao;
import com.dasinong.ploughHelper.dao.SubStageDao;
import com.dasinong.ploughHelper.dao.TaskDao;
import com.dasinong.ploughHelper.dao.TaskSpecDao;
import com.dasinong.ploughHelper.dao.UserDao;
import com.dasinong.ploughHelper.dao.VarietyDao;
import com.dasinong.ploughHelper.dummyData.HomeRelated;
import com.dasinong.ploughHelper.dummyData.IniPetSolCPP;
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
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.Variety;
import com.dasinong.ploughHelper.util.GeoUtil;

@Controller
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/testIniUseCroFieLocVar", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	@Transactional
	public Object tIniCroFieVar(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println("For test");
		UserDao userDao = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		CropDao cropDao = (CropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		LocationDao locationDao = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();

		
		User user2 = new User();
		user2.setAddress("beijing");
		user2.setCellPhone("13112345678");
		user2.setPassword("11111111");
		user2.setUserName("Xiyao3");
		userDao.save(user2);
		
		User user1 = new User();
		user1.setAddress("beijing");
		user1.setCellPhone("13112345678");
		user1.setPassword("11111111");
		user1.setUserName("Xiyao2");
		userDao.save(user1);
			
		User user = new User();
		user.setAddress("beijing");
		user.setCellPhone("13112345678");
		user.setPassword("11111111");
		user.setUserName("Xiyao");
		userDao.save(user);
		
		
		Location location = new Location("华东","上海","上海","上海","虹口区","四平路",31.111111,131.111111);
		locationDao.save(location);
			
			
		Crop crop = new Crop("水稻");
		cropDao.save(crop);
		
		Variety variety1 = new Variety("TestVariety1",crop);
		varietyDao.save(variety1);
		
		Variety variety2 = new Variety("TestVariety2",crop);
		varietyDao.save(variety2);
				
	    Field field = new Field("Test field 1", variety1, user, location);

	    variety1.getFields().add(field);
		fieldDao.save(field);
		
		    
		field = new Field("Test field 2",variety2,user,location);
		variety2.getFields().add(field);
		fieldDao.save(field);
		    
		field = new Field("Test field 3",variety2,user,location);
		variety2.getFields().add(field);
		fieldDao.save(field);
		   
		    
		System.out.println("Done");
		result.put("test","testoutputcheck");
		result.put("status",200);
			
		return result;
	}
	
	@RequestMapping(value = "/testChaCroVar", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tChaCroVar(HttpServletRequest request, HttpServletResponse response) {
		
		CropDao cropDao = (CropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
		  Crop cropo = cropDao.findByCropName("水稻");
		  
		  Crop crop = new Crop("水稻2");
		  cropDao.save(crop);
		  
		  Variety variety1 = varietyDao.findByVarietyName("TestVariety1");
		  variety1.setCrop(crop);
		  
		  cropo.getVarieties().remove(variety1);
		  crop.getVarieties().add(variety1);

		  varietyDao.update(variety1);

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
		
		UserDao userDao = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		LocationDao locationDao = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			
		  User user = userDao.findByUserName("Xiyao");
		  Location location1 = new Location("华东","上海","上海","上海","徐汇区","徐家汇",31.111111,131.111111);
	      locationDao.save(location1);
	          
	      
	      Variety variety1=varietyDao.findByVarietyName("TestVariety1");
		  Field field = new Field("上海1",variety1,user,location1);
		  field.setIsActive(true);
		  fieldDao.save(field);
		  variety1.getFields().add(field);
		  
		  Variety variety2=varietyDao.findByVarietyName("TestVariety2");
		  field = new Field("上海历史",variety2,user,location1);
		  field.setIsActive(false);
		  fieldDao.save(field);
		  
          //Dose reverse happen automatically?
		  for( Field f : variety2.getFields()){
			  System.out.println(f.getFieldName());
		  }
		  variety2.getFields().add(field);
		  
		  
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
		
		VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		SubStageDao subStageDao = (SubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Variety variety1 = varietyDao.findByVarietyName("TestVariety1");
			Variety variety2 = varietyDao.findByVarietyName("TestVariety2");
			SubStage ss1 = new SubStage("m1s1","m1");
			SubStage ss2 = new SubStage("m1s2","m1");
			ss1.getVarieties().add(variety1);
			ss1.getVarieties().add(variety2);
			ss2.getVarieties().add(variety1);
			subStageDao.save(ss1);
			subStageDao.save(ss2);
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
		SubStageDao subStageDao = (SubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao");
		NatDisSpecDao natDisDao = (NatDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("natDisSpecDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			SubStage ss1 = subStageDao.findBySubStageName("m1s1");
			SubStage ss2 = subStageDao.findBySubStageName("m1s2");
			
			NatDisSpec natDis1 = new NatDisSpec("刮风");
			NatDisSpec natDis2 = new NatDisSpec("下雨");
			natDis1.getSubStages().add(ss1);
			natDis1.getSubStages().add(ss2);
			natDis2.getSubStages().add(ss1);
			
			natDisDao.save(natDis1);
			natDisDao.save(natDis2);

			ss1.getNatDisSpecs().add(natDis1);
			ss1.getNatDisSpecs().add(natDis2);
			ss2.getNatDisSpecs().add(natDis1);
			subStageDao.update(ss1);
			subStageDao.update(ss2);
			
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
		LocationDao locationDao = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		NatDisSpecDao natDisDao = (NatDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("natDisSpecDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Location l1 = locationDao.findByLocationName("上海");
			
			NatDisSpec natDis1 = natDisDao.findByNatDisSpecName("刮风");
			NatDisSpec natDis2 = natDisDao.findByNatDisSpecName("下雨");

            l1.getNatDisSpecs().add(natDis1);
            l1.getNatDisSpecs().add(natDis2);
			
            locationDao.update(l1);
			
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
		
		LocationDao locationDao = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		PetDisSpecDao petDisDao = (PetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		SubStageDao subStageDao = (SubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao") ;
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Location l1 = locationDao.findByLocationName("上海");
			SubStage ss1 = subStageDao.findBySubStageName("m1s1");
			SubStage ss2 = subStageDao.findBySubStageName("m1s2");
			
			PetDisSpec petDis1 = new PetDisSpec("一号病");
			PetDisSpec petDis2 = new PetDisSpec("二号病");

			petDisDao.save(petDis1);
			petDisDao.save(petDis2);
			
            l1.getPetDisSpecs().add(petDis1);
            l1.getPetDisSpecs().add(petDis2);
			locationDao.update(l1);
			
            ss1.getPetDisSpecs().add(petDis1);
            ss2.getPetDisSpecs().add(petDis1);
            ss2.getPetDisSpecs().add(petDis2);
            subStageDao.update(ss1);
            subStageDao.update(ss2);
            
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
		
		CropDao cropDao = (CropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		QualityItemDao qualityItemDao = (QualityItemDao) ContextLoader.getCurrentWebApplicationContext().getBean("qualityItemDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Crop crop = cropDao.findByCropName("水稻");
			QualityItem qi1 = new QualityItem("香味",crop);
			QualityItem qi2 = new QualityItem("抗药性",crop);
			QualityItem qi3 = new QualityItem("抗旱",crop);
			qualityItemDao.save(qi1);
			qualityItemDao.save(qi2);
			qualityItemDao.save(qi3);
			
			for(QualityItem q: crop.getQualityItems()){
				System.out.println(q.getQualityItemName());
			}
			crop.getQualityItems().add(qi1);
			crop.getQualityItems().add(qi2);
			crop.getQualityItems().add(qi3);
			for(QualityItem q: crop.getQualityItems()){
				System.out.println(q.getQualityItemName());
			}
			cropDao.update(crop);
		    
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
		
		SubStageDao subStageDao = (SubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao");
		TaskSpecDao taskSpecDao = (TaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		StepDao stepDao = (StepDao) ContextLoader.getCurrentWebApplicationContext().getBean("stepDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			SubStage ss1 = subStageDao.findBySubStageName("m1s1");
			SubStage ss2 = subStageDao.findBySubStageName("m1s2");
			TaskSpec task1 = new TaskSpec("种田",ss1);
			TaskSpec task2 = new TaskSpec("浇水",ss2);
			ss1.getTaskSpecs().add(task1);
			ss2.getTaskSpecs().add(task2);
			
			taskSpecDao.save(task1);
			taskSpecDao.save(task2);
			
			Step step11 = new Step("种田1",task1);
			task1.getSteps().add(step11);
			Step step12 = new Step("种田2",task1);
			task1.getSteps().add(step12);
			Step step21 = new Step("浇水1",task2);
			task2.getSteps().add(step21);
			Step step22 = new Step("浇水2",task2);
			task2.getSteps().add(step22);
			
			stepDao.save(step11);
			stepDao.save(step12);
			stepDao.save(step21);
			stepDao.save(step22);
			
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
		FieldDao fieldbo = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		Field field1 = fieldbo.findByFieldName("上海1");
		Field field2 = fieldbo.findByFieldName("上海历史");
		TaskDao taskbo = (TaskDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskDao");
		
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
		VarietyDao varietybo = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		Variety variety1 = varietybo.findByVarietyName("TestVariety1");
		Variety variety2 = varietybo.findByVarietyName("TestVariety2");
		QualityItemValueDao qivbo = (QualityItemValueDao) ContextLoader.getCurrentWebApplicationContext().getBean("qualityItemValueDao");
		
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
		FieldDao fieldbo = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		Field field1 = fieldbo.findByFieldName("上海1");
		Field field2 = fieldbo.findByFieldName("上海历史");
		PetDisDao petDisbo = (PetDisDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisDao");
		
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
		FieldDao fieldbo = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		Field field1 = fieldbo.findByFieldName("上海1");
		Field field2 = fieldbo.findByFieldName("上海历史");
		NatDisDao natDisbo = (NatDisDao) ContextLoader.getCurrentWebApplicationContext().getBean("natDisDao");
		
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
	@Transactional
	public Object tiPDSSol(HttpServletRequest request, HttpServletResponse response) {
		PetDisSpecDao petDisSpecbo = (PetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		PetSoluDao psbo = (PetSoluDao) ContextLoader.getCurrentWebApplicationContext().getBean("petSoluDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			PetDisSpec ps1 = petDisSpecbo.findByPetDisSpecName("一号病");
			PetDisSpec ps2 = petDisSpecbo.findByPetDisSpecName("二号病");
			
			PetSolu petSolu11 = new PetSolu("治疗一号病1",ps1);
			PetSolu petSolu12 = new PetSolu();
			PetSolu petSolu21 = new PetSolu();
			PetSolu petSolu22 = new PetSolu();
			PetSolu petSolu23 = new PetSolu();
			petSolu11.setPetDisSpec(ps1);
			petSolu11.setIsRemedy(true);

			petSolu12.setPetSoluDes("预防一号病2");
			petSolu12.setPetDisSpec(ps1);
			petSolu12.setIsRemedy(false);

			petSolu21.setPetSoluDes("治疗二号病1");
			petSolu21.setPetDisSpec(ps2);
			petSolu21.setIsRemedy(true);

			petSolu22.setPetSoluDes("预防二号病2");
			petSolu22.setPetDisSpec(ps2);
			petSolu22.setIsRemedy(false);

			petSolu23.setPetSoluDes("治疗二号病3");
			petSolu23.setPetDisSpec(ps2);
			petSolu23.setIsRemedy(true);
			
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
	
	@RequestMapping(value = "/tesddd", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object tesddd(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{

            IniPetSolCPP  ipsl = new IniPetSolCPP();
            ipsl.test();
			
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
	
	
	@RequestMapping(value = "/iniHomeKP",produces="application/json")
	@ResponseBody
	public Object iniHomeKP(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		
		HomeRelated hr = new HomeRelated();
		hr.initKnowledgePool();
		
		
			
		System.out.println("Done");
		result.put("test","testoutputcheck");
		result.put("status",200);
			
		return result;
	}
	
	@RequestMapping(value = "/iniHomeBO",produces="application/json")
	@ResponseBody
	public Object iniHomeBO(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		HomeRelated hr = new HomeRelated();
		hr.initBussinessObject();
			
		System.out.println("Done");
		result.put("test","testoutputcheck");
		result.put("status",200);
			
		return result;
	}
}
