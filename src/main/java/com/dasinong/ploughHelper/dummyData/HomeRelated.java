package com.dasinong.ploughHelper.dummyData;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.CPProductDao;
import com.dasinong.ploughHelper.dao.CropDao;
import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.dao.LocationDao;
import com.dasinong.ploughHelper.dao.NatDisDao;
import com.dasinong.ploughHelper.dao.NatDisSpecDao;
import com.dasinong.ploughHelper.dao.PetDisDao;
import com.dasinong.ploughHelper.dao.PetDisSpecDao;
import com.dasinong.ploughHelper.dao.PetSoluDao;
import com.dasinong.ploughHelper.dao.SubStageDao;
import com.dasinong.ploughHelper.dao.TaskDao;
import com.dasinong.ploughHelper.dao.TaskSpecDao;
import com.dasinong.ploughHelper.dao.UserDao;
import com.dasinong.ploughHelper.dao.VarietyDao;
import com.dasinong.ploughHelper.model.CPProduct;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.NatDisSpec;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.PetSolu;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.TaskSpec;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.Variety;

public class HomeRelated {
	
	@Transactional
	public void initKnowledgePool(){
		PetDisSpecDao petDisSpecDao = (PetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		NatDisSpecDao natDisSpecDao = (NatDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("natDisSpecDao");
		
		TaskSpecDao taskSpecDao = (TaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		LocationDao locationDao = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		SubStageDao subStageDao = (SubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao");
		CropDao cropDao = (CropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		
		//This part supposed to be loaded from knowledge pool
		PetDisSpec pds1 = new PetDisSpec("稻瘟病","病害","黑旋风1",5,100,500,"预防","个体","根部","褐色","三角形","这是一个很严重的病。");
		PetDisSpec pds2 = new PetDisSpec("稻瘟病1","病害","黑旋风2",5,100,500,"预防","个体","根部","褐色","三角形","这是一个很严重的病。");
		PetDisSpec pds3 = new PetDisSpec("虫害1","虫害","黑旋风",5,100,500,"治疗","个体","叶部","黄色","散点","这是一个很严重的病。");
		PetDisSpec pds4 = new PetDisSpec("草害1","草害","黑旋风",5,100,500,"治疗","群体","根部","黑色","三角形","这是一个很严重的病。");
		PetDisSpec pds5 = new PetDisSpec("草害2","草害","黑旋风",5,100,500,"预防","群体","根部","褐色","三角形","这是一个很严重的病。");
		
	    petDisSpecDao.save(pds1);
	    petDisSpecDao.save(pds2);
	    petDisSpecDao.save(pds3);
	    petDisSpecDao.save(pds4);
	    petDisSpecDao.save(pds5);
	    
	    NatDisSpec nds1 = new NatDisSpec("台风","收衣服","大风到了");
	    NatDisSpec nds2 = new NatDisSpec("大雨","抽水","大风到了");
	    natDisSpecDao.save(nds1);
	    natDisSpecDao.save(nds2);
	    
	    Crop crop = new Crop("水稻");
	    cropDao.save(crop);
	    
	    Variety variety = new Variety("南茛56",crop);
	    varietyDao.save(variety);
	    
	    SubStage s1 = new SubStage("播种1","播种期");
	    SubStage s2 = new SubStage("播种2","播种期");
	    SubStage s3 = new SubStage("分叶1","分叶期");
	    SubStage s4 = new SubStage("分叶2","分叶期");
	    SubStage s5 = new SubStage("收获1","收获期");
	    SubStage s6 = new SubStage("收获2","收获期");
	    s1.getVarieties().add(variety);
	    s2.getVarieties().add(variety);
	    s3.getVarieties().add(variety);
	    s4.getVarieties().add(variety);
	    s5.getVarieties().add(variety);
	    s6.getVarieties().add(variety);
	    subStageDao.save(s1);
	    subStageDao.save(s2);
	    subStageDao.save(s3);
	    subStageDao.save(s4);
	    subStageDao.save(s5);
	    subStageDao.save(s6);
	    
	    variety.getSubStages().add(s1);
	    variety.getSubStages().add(s2);
	    variety.getSubStages().add(s3);
	    variety.getSubStages().add(s4);
	    variety.getSubStages().add(s5);
	    variety.getSubStages().add(s6);
        varietyDao.update(variety);
	    
	    
	    TaskSpec ts11 = new TaskSpec("播种1任务1",s1,"田地管理");
	    TaskSpec ts12 = new TaskSpec("播种1任务2",s1);
	    TaskSpec ts21 = new TaskSpec("播种2任务1",s2);
	    TaskSpec ts22 = new TaskSpec("播种2任务2",s2);
	    TaskSpec ts31 = new TaskSpec("播种3任务1",s3);
	    TaskSpec ts51 = new TaskSpec("播种5任务1",s5);
	    TaskSpec ts52 = new TaskSpec("播种5任务2",s5);
	    TaskSpec ts61 = new TaskSpec("播种6任务1",s6);
	    taskSpecDao.save(ts11);
	    taskSpecDao.save(ts12);
	    taskSpecDao.save(ts21);
	    taskSpecDao.save(ts22);
	    taskSpecDao.save(ts31);
	    taskSpecDao.save(ts51);
	    taskSpecDao.save(ts52);
	    taskSpecDao.save(ts61);
	    
	    s1.getTaskSpecs().add(ts11);
	    s1.getTaskSpecs().add(ts12);
	    s2.getTaskSpecs().add(ts21);
	    s2.getTaskSpecs().add(ts22);
	    s3.getTaskSpecs().add(ts31);
	    s5.getTaskSpecs().add(ts51);
	    s5.getTaskSpecs().add(ts52);
	    s6.getTaskSpecs().add(ts61);
	    
	    subStageDao.update(s1);
	    subStageDao.update(s2);
	    subStageDao.update(s3);
	    subStageDao.update(s5);
	    subStageDao.update(s6);
	    
	    Location location = new Location("华东","上海","上海","杨浦","杨浦","香獐圆",31.11111,130.111111);
	    locationDao.save(location);
	}
	
	@Transactional
	public void initBussinessObject(){
		UserDao userDao = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		LocationDao locationDao = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		TaskDao taskDao = (TaskDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskDao");
		PetDisDao petDisDao = (PetDisDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisDao");
		NatDisDao natDisDao = (NatDisDao) ContextLoader.getCurrentWebApplicationContext().getBean("natDisDao");
		PetDisSpecDao petDisSpecDao = (PetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		NatDisSpecDao natDisSpecDao = (NatDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("natDisSpecDao");
		FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		
		User user = new User("13112345678","11111111","13112345678","上海");
		userDao.save(user);
		
		Location location = locationDao.findById(10L);
		Variety variety = varietyDao.findByVarietyName("南茛56");
		
		Field field = new Field("测试田",variety,user,location);
		
		//Initialize task
		Iterator<SubStage> i = variety.getSubStages().iterator(); 
		SubStage ss =  i.next();
		System.out.println(ss);
		field.setCurrentStageID(ss.getSubStageId());
		HashMap<Long,Task> tasks = new HashMap<Long,Task>();
		for(TaskSpec t : ss.getTaskSpecs()){
			Task task = new Task(t,false);
			taskDao.save(task);
			tasks.put(task.getTaskId(),task);
		}
		field.setTasks(tasks);
		
		//Initialize warning;
		HashMap<Long,NatDis> natDiss = new HashMap<Long,NatDis>();
		NatDisSpec natDisSpec = natDisSpecDao.findByNatDisSpecName("台风");
		NatDis natDis = new NatDis(natDisSpec,false);
		natDisDao.save(natDis);
		natDiss.put(natDis.getNatDisId(), natDis);
		field.setNatDiss(natDiss);
		
		HashMap<Long,PetDis> petDiss = new HashMap<Long,PetDis>();
		PetDisSpec petDisSpec= petDisSpecDao.findByPetDisSpecName("稻瘟病1");
		PetDis petDis = new PetDis(petDisSpec,false);
		petDisDao.save(petDis);
		petDiss.put(petDis.getPetDisId(), petDis);
		field.setPetDiss(petDiss);
		
		fieldDao.save(field);
	}
}
