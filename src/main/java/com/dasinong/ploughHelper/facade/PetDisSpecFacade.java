package com.dasinong.ploughHelper.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.dao.ISubStageDao;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.PetSolu;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.outputWrapper.PetDisSpecWrapper;
import com.dasinong.ploughHelper.outputWrapper.PetSoluWrapper;

@Transactional
public class PetDisSpecFacade implements IPetDisSpecFacade {

	IPetDisSpecDao petDisSpecDao;
	ISubStageDao subStageDao;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IPetDisSpecFacade#getPetDisBySubStage(java.lang.Long)
	 */
	@Override
	public Object getPetDisBySubStage(Long subStageId){
		HashMap<String,Object> result = new HashMap<String,Object>();
		petDisSpecDao = (IPetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		subStageDao = (ISubStageDao) ContextLoader.getCurrentWebApplicationContext().getBean("subStageDao");
		SubStage sb = subStageDao.findById(subStageId);
		List<PetDisSpecWrapper> pws = new ArrayList<PetDisSpecWrapper>();
		if (sb.getPetDisSpecs()==null){
			result.put("respCode", 203);
			result.put("message", "当前阶段无常见病虫草害");
			return result;
		}
			
		for(PetDisSpec p: sb.getPetDisSpecs()){
			PetDisSpecWrapper pw = new PetDisSpecWrapper(p);
			pws.add(pw);
		}
		
		result.put("respCode",200);
		result.put("message", "获取常见病虫草害成功");
		result.put("data", pws);
		return result;
	}
	
	@Override
	public Object getPetDisDetail(Long petDisSpecId){
		HashMap<String,Object> result = new HashMap<String,Object>();
		petDisSpecDao = (IPetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		PetDisSpec pds = petDisSpecDao.findById(petDisSpecId);
		HashMap<String,Object> data = new HashMap<String,Object>();
		PetDisSpecWrapper pdsw = new PetDisSpecWrapper(pds);
		data.put("petDisSpec", pdsw);
		if (pds.getPetSolus()!=null && pds.getPetSolus().size()!=0){
			List<PetSoluWrapper> psws = new ArrayList<PetSoluWrapper>(); 
			for (PetSolu ps : pds.getPetSolus()){
				PetSoluWrapper psw = new PetSoluWrapper(ps);
				psws.add(psw);
			}
			data.put("petSolutions", psws);
		}
		result.put("respCode",200);
		result.put("message", "获取常见病虫草害成功");
		result.put("data", data);
		return result;
	}

}
