package com.dasinong.ploughHelper.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.CPProductBrowseDao;
import com.dasinong.ploughHelper.dao.ICPProductBrowseDao;
import com.dasinong.ploughHelper.dao.ICPProductDao;
import com.dasinong.ploughHelper.dao.ICropDao;
import com.dasinong.ploughHelper.dao.IPetDisSpecBrowseDao;
import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.dao.IVarietyBrowseDao;
import com.dasinong.ploughHelper.dao.IVarietyDao;
import com.dasinong.ploughHelper.model.CPProduct;
import com.dasinong.ploughHelper.model.CPProductBrowse;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.PetDisSpecBrowse;
import com.dasinong.ploughHelper.model.Variety;
import com.dasinong.ploughHelper.model.VarietyBrowse;
import com.dasinong.ploughHelper.outputWrapper.CPProductWrapper;
import com.dasinong.ploughHelper.outputWrapper.CropWrapper;
import com.dasinong.ploughHelper.outputWrapper.PetDisSpecWrapper;
import com.dasinong.ploughHelper.outputWrapper.VarietyWrapper;

@Transactional
public class BaiKeFacade implements IBaiKeFacade {
	ICropDao cropDao;
	IVarietyDao varietyDao;
	IVarietyBrowseDao varietyBrowseDao;
	IPetDisSpecDao petDisSpecDao;
	IPetDisSpecBrowseDao petDisSpecBrowseDao;
	ICPProductDao cPProductDao;
	ICPProductBrowseDao cPProductBrowseDao;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IBaiKeFacade#getCPProductById(java.lang.Long)
	 */
	@Override
	public Object getCPProductById(Long id) {
		HashMap<String,Object> result  = new HashMap<String,Object>();
    	cPProductDao = (ICPProductDao) ContextLoader.getCurrentWebApplicationContext().getBean("cPProductDao");
		CPProduct pro = cPProductDao.findById(id);
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
	
	@Override
	public Object browseVarietyByCropId(Long cropId){
		
		HashMap<String,Object> result  = new HashMap<String,Object>();
		varietyBrowseDao = (IVarietyBrowseDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyBrowseDao");
		List<VarietyBrowse> vbs = varietyBrowseDao.findByCropId(cropId);
    	if (vbs==null || vbs.size()==0){
    		result.put("respCode", 400);
    		result.put("message","该作物不存在");
    		return result;
    	}
    	
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", vbs);
    	return result;
	}
	
	@Override
	public Object browseCPProductByModel(String model){
		HashMap<String,Object> result  = new HashMap<String,Object>();
		cPProductBrowseDao = (ICPProductBrowseDao) ContextLoader.getCurrentWebApplicationContext().getBean("cPProductBrowseDao");
		List<CPProductBrowse> cpbs = cPProductBrowseDao.findByModel(model);
    	if (cpbs==null || cpbs.size()==0){
    		result.put("respCode", 400);
    		result.put("message","该类型农药不存在");
    		return result;
    	}
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", cpbs);
    	return result;
	}
	
	@Override
	public Object browsePetDisByType(String type){
		
		HashMap<String,Object> result  = new HashMap<String,Object>();
		petDisSpecBrowseDao = (IPetDisSpecBrowseDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecBrowseDao");
		List<PetDisSpecBrowse> pdsbs = petDisSpecBrowseDao.findByType(type);
    	if (pdsbs==null || pdsbs.size()==0){
    		result.put("respCode", 400);
    		result.put("message","该类型病虫草害不存在");
    		return result;
    	}
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", pdsbs);
    	return result;
		
	}

	@Override
	public Object getCropByType(String type) {
		HashMap<String,Object> result  = new HashMap<String,Object>();
		cropDao = (ICropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		List<Crop> crops = cropDao.findByType(type);
    	if (crops==null || crops.size()==0){
    		result.put("respCode", 400);
    		result.put("message","该类型作物不存在");
    		return result;
    	}
    	List<CropWrapper> cws = new ArrayList<CropWrapper>();
    	for(Crop c:crops){
    		cws.add(new CropWrapper(c));
    	}
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", cws);
    	return result;
	}
	
	
	@Override
	public Object getVarietysByName(String name) {
		HashMap<String,Object> result  = new HashMap<String,Object>();
		varietyDao = (IVarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		List<Variety> varietys = varietyDao.findVarietysByName(name);
    	if (varietys==null || varietys.size()==0){
    		result.put("respCode", 400);
    		result.put("message","该名称品种不存在");
    		return result;
    	}
    	List<VarietyWrapper> vws = new ArrayList<VarietyWrapper>();
    	for(Variety v: varietys){
    		vws.add(new VarietyWrapper(v));
    	}
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", vws);
    	return result;
	}
	
	@Override
	public Object getCPProdcutsByIngredient(String ingredient) {
		HashMap<String,Object> result  = new HashMap<String,Object>();
		cPProductDao = (ICPProductDao) ContextLoader.getCurrentWebApplicationContext().getBean("cPProductDao");
		List<CPProduct> cpproducts = cPProductDao.findByIngredient(ingredient);
    	if (cpproducts==null || cpproducts.size()==0){
    		result.put("respCode", 400);
    		result.put("message","该有效成分不存在");
    		return result;
    	}
    	List<CPProductWrapper> cppws = new ArrayList<CPProductWrapper>();
    	for(CPProduct cpp: cpproducts){
    		cppws.add(new CPProductWrapper(cpp));
    	}
    	result.put("respCode", 200);
    	result.put("message", "获得成功");
    	result.put("data", cppws);
    	return result;
	}
}
