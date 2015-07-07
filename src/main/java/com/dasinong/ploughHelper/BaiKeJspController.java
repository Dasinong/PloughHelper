package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.dasinong.ploughHelper.facade.IBaiKeFacade;
import com.dasinong.ploughHelper.outputWrapper.CPProductWrapper;
import com.dasinong.ploughHelper.outputWrapper.PetDisSpecWrapper;
import com.dasinong.ploughHelper.outputWrapper.VarietyWrapper;

public class BaiKeJspController implements Controller {
	
	IBaiKeFacade baiKeFacade;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
    	ModelAndView mv = new ModelAndView();
    	String type = request.getParameter("type");
    	String id = request.getParameter("id");
    	baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
		HashMap<String,Object> result = new HashMap<String,Object>();
    	try{
	    	switch(type){
	    	case "pest":
	    		result = (HashMap<String,Object>)baiKeFacade.getCPProductById(Long.parseLong(id));
	    		PetDisSpecWrapper  pdsw = (PetDisSpecWrapper)result.get("data");
	    		mv = handlePest(mv,pdsw);
	    		break;
	    	case "pesticide":
	    		result = (HashMap<String,Object>)baiKeFacade.getCPProductById(Long.parseLong(id));
	    		CPProductWrapper  cpw = (CPProductWrapper)result.get("data");
	    		mv = handlePesticide(mv, cpw);
	    		break;
	    	case "variety":
	    		 
	    		result = (HashMap<String,Object>)baiKeFacade.getVarietyById(Long.parseLong(id));
	    		VarietyWrapper  vw = (VarietyWrapper)result.get("data");
	    		mv = handleVariety(mv, vw);
	    		break;
	    	}
    	} catch (Exception e){
    		e.printStackTrace();
    		System.out.println("Error happened when handle BaiKeJsp Data!");
    	}
    	
		return mv;
	}
	
	public ModelAndView handlePest(ModelAndView mv, PetDisSpecWrapper  pdsw){
		
		return mv;
	}
	
	public ModelAndView handlePesticide(ModelAndView mv, CPProductWrapper cpw){
		mv.addObject("name", cpw.getName());
		mv.addObject("activeIngredient", cpw.getActiveIngredient());
		mv.addObject("type", cpw.getType());
		mv.addObject("disease", cpw.getDisease());
		mv.addObject("volumn", cpw.getVolumn());
		mv.addObject("guideline", cpw.getGuideline());
		mv.addObject("registrationId", cpw.getRegistrationId());
		mv.addObject("manufacturer", cpw.getManufacturer());
		mv.addObject("tips", cpw.getTip());
		mv.setViewName("BaikePesticide");
		return mv;
	}
	
	public ModelAndView handleVariety(ModelAndView mv, VarietyWrapper vw){
		
		return mv;
	}

}