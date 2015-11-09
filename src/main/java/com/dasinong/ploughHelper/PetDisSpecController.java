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


@Controller
public class PetDisSpecController extends RequireUserLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(PetDisSpecController.class);

	IPetDisSpecFacade petDisSpecFacade;
	IPetSoluFacade petSoluFacade;
	
	@RequestMapping(value = "/getPetDisBySubStage", produces="application/json")
	@ResponseBody
	public Object getPetDisBySubStage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		Long subStageId;
		Long varietyId;
		try{
			subStageId = Long.parseLong(request.getParameter("subStageId"));
			try{
				varietyId = Long.parseLong(request.getParameter("varietyId"));
			}
			catch(Exception e){ //for backward compatibility
				varietyId =  -1L;
			}
		}
		catch(Exception e){
			throw new InvalidParameterException("subStageId","Long");
		}

		petDisSpecFacade = (IPetDisSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecFacade");
		
		return petDisSpecFacade.getPetDisBySubStage(subStageId,varietyId);
	}
	
	@RequestMapping(value = "/getPetDisSpecDetial", produces="application/json")
	@ResponseBody
	public Object getPetDisSpecDetial(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		Long petDisSpecId;
		try{
			petDisSpecId = Long.parseLong(request.getParameter("petDisSpecId"));
		}
		catch(Exception e){
			throw new InvalidParameterException("petDisSpecId","Long");
		}

		petDisSpecFacade = (IPetDisSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecFacade");
		
		return petDisSpecFacade.getPetDisDetail(petDisSpecId);
	}
	
	
	@RequestMapping(value = "/getPetSolu", produces="application/json")
	@ResponseBody
	public Object getPetSolu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		Long petSoluId;
		try{
			petSoluId = Long.parseLong(request.getParameter("petSoluId"));
		}
		catch(Exception e){
			throw new InvalidParameterException("petSoluId","Long");
		}

		petSoluFacade = (IPetSoluFacade) ContextLoader.getCurrentWebApplicationContext().getBean("petSoluFacade");
		return petSoluFacade.getPetSoluDetail(petSoluId);
	}
}
