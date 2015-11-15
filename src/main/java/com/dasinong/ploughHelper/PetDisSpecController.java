package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.facade.IPetDisSpecFacade;
import com.dasinong.ploughHelper.facade.IPetSoluFacade;
import com.dasinong.ploughHelper.util.HttpServletRequestX;


@Controller
public class PetDisSpecController extends RequireUserLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(PetDisSpecController.class);

	IPetDisSpecFacade petDisSpecFacade;
	IPetSoluFacade petSoluFacade;
	
	@RequestMapping(value = "/getPetDisBySubStage", produces="application/json")
	@ResponseBody
	public Object getPetDisBySubStage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String,Object> result = new HashMap<String,Object>();
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		
		Long subStageId = requestX.getLong("sugStageId");
		Long varietyId = requestX.getLongOptional("varietyId", -1L);

		petDisSpecFacade = (IPetDisSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecFacade");
		
		return petDisSpecFacade.getPetDisBySubStage(subStageId,varietyId);
	}
	
	@RequestMapping(value = "/getPetDisSpecDetial", produces="application/json")
	@ResponseBody
	public Object getPetDisSpecDetial(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		
		Long petDisSpecId = requestX.getLong("petDisSpecId");

		petDisSpecFacade = (IPetDisSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecFacade");
		
		return petDisSpecFacade.getPetDisDetail(petDisSpecId);
	}
	
	
	@RequestMapping(value = "/getPetSolu", produces="application/json")
	@ResponseBody
	public Object getPetSolu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		
		Long petSoluId = requestX.getLong("petSoluId");

		petSoluFacade = (IPetSoluFacade) ContextLoader.getCurrentWebApplicationContext().getBean("petSoluFacade");
		return petSoluFacade.getPetSoluDetail(petSoluId);
	}
}
