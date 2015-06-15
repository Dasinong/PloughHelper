package com.dasinong.ploughHelper.dummyData;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ICPProductDao;
import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.dao.IPetSoluDao;
import com.dasinong.ploughHelper.model.CPProduct;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.PetSolu;

public class IniPetSolCPP {
	@Transactional
	public void test(){
		IPetDisSpecDao petDisSpecDao = (IPetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		IPetSoluDao petSoluDao = (IPetSoluDao) ContextLoader.getCurrentWebApplicationContext().getBean("petSoluDao");
		ICPProductDao cPProductDao = (ICPProductDao) ContextLoader.getCurrentWebApplicationContext().getBean("cPProductDao");
		
		
		PetDisSpec pds1 = new PetDisSpec("稻瘟病","病害","黑旋风1",5,"100","500","预防","个体","根部","褐色","三角形","这是一个很严重的病。");
		PetDisSpec pds2 = new PetDisSpec("稻瘟病1","病害","黑旋风2",5,"100","500","预防","个体","根部","褐色","三角形","这是一个很严重的病。");
		PetDisSpec pds3 = new PetDisSpec("虫害1","虫害","黑旋风",5,"100","500","治疗","个体","叶部","黄色","散点","这是一个很严重的病。");
		PetDisSpec pds4 = new PetDisSpec("草害1","草害","黑旋风",5,"100","500","治疗","群体","根部","黑色","三角形","这是一个很严重的病。");
		PetDisSpec pds5 = new PetDisSpec("草害2","草害","黑旋风",5,"100","500","预防","群体","根部","褐色","三角形","这是一个很严重的病。");
		
	    petDisSpecDao.save(pds1);
	    petDisSpecDao.save(pds2);
	    petDisSpecDao.save(pds3);
	    petDisSpecDao.save(pds4);
	    petDisSpecDao.save(pds5);
	    
	    PetSolu ps11 = new PetSolu("治疗1",pds1,true,false);
	    PetSolu ps12 = new PetSolu("治疗2",pds1,true,true);
	    PetSolu ps13 = new PetSolu("治疗3",pds1,true,true);
	    PetSolu ps14 = new PetSolu("治疗4",pds1,true,false);
	    PetSolu ps21 = new PetSolu("治疗5",pds2,true,false);
	    PetSolu ps31 = new PetSolu("治疗虫害1",pds3,true,false);
	    PetSolu ps51 = new PetSolu("预防草害1",pds5,false,false);
	    PetSolu ps52 = new PetSolu("预防草害2",pds5,false,false);
	    
	    petSoluDao.save(ps11);
	    petSoluDao.save(ps12);
	    petSoluDao.save(ps13);
	    petSoluDao.save(ps14);
	    petSoluDao.save(ps21);
	    petSoluDao.save(ps31);
	    petSoluDao.save(ps51);
	    petSoluDao.save(ps52);
	    
	    
	    CPProduct cppd1 = new CPProduct("农药1","各种配料","什么类型","一堆作物","神经病","用量","打电话","多问人");
	    CPProduct cppd2 = new CPProduct("农药2","各种配料","什么类型","一堆作物","神经病","用量","打电话","多问人");
	    CPProduct cppd3 = new CPProduct("农药3","各种配料","什么类型","一堆作物","神经病","用量","打电话","多问人");
	    cPProductDao.save(cppd1);
	    cPProductDao.save(cppd2);
	    cPProductDao.save(cppd3);
	    
	    ps11.getcPProducts().add(cppd1);
	    cppd1.getPetSolus().add(ps11);
	    ps11.getcPProducts().add(cppd2);
	    cppd2.getPetSolus().add(ps11);
	    ps12.getcPProducts().add(cppd3);
	    cppd3.getPetSolus().add(ps12);
	    ps12.getcPProducts().add(cppd1);
	    cppd1.getPetSolus().add(ps12);
	    
	    petSoluDao.update(ps11);
	    petSoluDao.update(ps12);
	}

	

}
