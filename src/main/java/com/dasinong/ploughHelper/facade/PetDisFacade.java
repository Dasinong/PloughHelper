package com.dasinong.ploughHelper.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.dao.ISubStageDao;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.outputWrapper.PetDisSpecWrapper;

@Transactional
public class PetDisFacade implements IPetDisFacade {
	

	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IPetDisFacade#getPetDisByLocation(java.lang.Long)
	 */
	@Override
	public Object getPetDisByLocation(Long locationId){
		HashMap<String,Object> result = new HashMap<String,Object>();
		//TODO: depends on region field content.
		return result;
	}
	
	

	
}
