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

import com.dasinong.ploughHelper.facade.IPetDisSpecFacade;
import com.dasinong.ploughHelper.model.User;

@Controller
public class PetDisSpecController {
	private static final Logger logger = LoggerFactory.getLogger(PetDisController.class);

	IPetDisSpecFacade petDisSpecFacade;
	
	@RequestMapping(value = "/getPetDisBySubStage", produces="application/json")
	@ResponseBody
	public Object getPetDisBySubStage(HttpServletRequest request, HttpServletResponse response){
		HashMap<String,Object> result = new HashMap<String,Object>();
		User user = (User) request.getSession().getAttribute("User");
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		
		Long subStageId;
		try{
			subStageId = Long.parseLong(request.getParameter("subStageId"));
		}
		catch(Exception e){
			result.put("respCode",300);
			result.put("message","参数错误");
			return result;
		}

		petDisSpecFacade = (IPetDisSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecFacade");
		
		return petDisSpecFacade.getPetDisBySubStage(subStageId);
	}
}
