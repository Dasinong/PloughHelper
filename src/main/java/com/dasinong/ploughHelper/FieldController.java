package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.inputParser.FieldParser;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;



@Controller
public class FieldController {
	private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
	
	@Autowired
	private ITaskSpecDao taskSpecDao;
	
	
	@RequestMapping(value = "/createField", produces="application/json")
	@ResponseBody
	public Object createField(HttpServletRequest request, HttpServletResponse response)  {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
	    IFieldDao fd = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
	   
	    try {
			FieldParser fieldp = new FieldParser(request);
			Field f = fieldp.getField();
			f.setUser(user);
			fd.save(f);
			user.getFields().add(f);
			FieldWrapper fw = new FieldWrapper(f,taskSpecDao);
			result.put("respCode", 200);
			result.put("message", "添加田地成功");
			result.put("data",fw);
			return result;
		} catch (Exception e) {
			result.put("respCode",500);
			result.put("message", e.getCause());
			return result;
		}
	}
		
}
