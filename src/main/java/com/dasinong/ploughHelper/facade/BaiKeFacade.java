package com.dasinong.ploughHelper.facade;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ICPProductDao;
import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.dao.IVarietyDao;
import com.dasinong.ploughHelper.model.CPProduct;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.Variety;
import com.dasinong.ploughHelper.outputWrapper.CPProductWrapper;
import com.dasinong.ploughHelper.outputWrapper.PetDisSpecWrapper;
import com.dasinong.ploughHelper.outputWrapper.VarietyWrapper;

@Transactional
public class BaiKeFacade implements IBaiKeFacade {
	IVarietyDao varietyDao;
	IPetDisSpecDao petDisSpecDao;
	ICPProductDao cpproductDao;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IBaiKeFacade#getCPProductById(java.lang.Long)
	 */
	@Override
	public Object getCPProductById(Long id) {
		HashMap<String,Object> result  = new HashMap<String,Object>();
    	cpproductDao = (ICPProductDao) ContextLoader.getCurrentWebApplicationContext().getBean("cPProductDao");
		CPProduct pro = cpproductDao.findById(id);
    	if (pro==null){
    		result.put("respCode", 400);
    		result.put("message","农药未找到");
    		return result;
    	}
    	CPProductWrapper  cpw = new CPProductWrapper(pro);
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", cpw);
    	return result;
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IBaiKeFacade#getVarietyById(java.lang.Long)
	 */
	@Override
	public Object getVarietyById(Long id) {
		HashMap<String,Object> result  = new HashMap<String,Object>();
    	varietyDao = (IVarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		Variety v = varietyDao.findById(id);
    	if (v==null){
    		result.put("respCode", 400);
    		result.put("message","品种未找到");
    		return result;
    	}
    	VarietyWrapper vw = new VarietyWrapper(v);
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", vw);
    	return result;
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IBaiKeFacade#getPetDisSpecById(java.lang.Long)
	 */
	@Override
	public Object getPetDisSpecById(Long id) {
		HashMap<String,Object> result  = new HashMap<String,Object>();
		petDisSpecDao = (IPetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		PetDisSpec pds = petDisSpecDao.findById(id);
    	if (pds==null){
    		result.put("respCode", 400);
    		result.put("message","病虫草害未找到");
    		return result;
    	}
    	PetDisSpecWrapper  pdsw = new PetDisSpecWrapper(pds);
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", pdsw);
    	return result;
	}
}
