package com.dasinong.ploughHelper.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IPetSoluDao;
import com.dasinong.ploughHelper.model.CPProduct;
import com.dasinong.ploughHelper.model.PetSolu;
import com.dasinong.ploughHelper.outputWrapper.CPProductWrapper;
import com.dasinong.ploughHelper.outputWrapper.PetDisSpecWrapper;
import com.dasinong.ploughHelper.outputWrapper.PetSoluWrapper;

@Transactional
public class PetSoluFacade implements IPetSoluFacade {
	
	IPetSoluDao petSoluDao;
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IPetSoluFacade#getPetSoluDetail(java.lang.Long)
	 */
	@Override
	public Object getPetSoluDetail(Long petSoluId){
		HashMap<String,Object> result  = new HashMap<String,Object>();
		petSoluDao = (IPetSoluDao) ContextLoader.getCurrentWebApplicationContext().getBean("petSoluDao");
		PetSolu ps = petSoluDao.findById(petSoluId);
		if (ps==null){
			result.put("respCode",404);
			result.put("message","该防治方法不存在");
			return result;
		}
		HashMap<String,Object> data = new HashMap<String,Object>();
		
		PetSoluWrapper psw = new PetSoluWrapper(ps);
		data.put("petSolutions", psw);
		if (ps.getcPProducts()!=null && ps.getcPProducts().size()!=0){
			List<CPProductWrapper> cppws = new ArrayList<CPProductWrapper>(); 
			for (CPProduct cpp : ps.getcPProducts()){
				CPProductWrapper cppw = new CPProductWrapper(cpp);
				cppws.add(cppw);
			}
			data.put("cPProducts", cppws);
		}
		result.put("respCode",200);
		result.put("message", "获取防治方法详情成功");
		result.put("data", data);
		return result;
	}

}