package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;



import com.dasinong.ploughHelper.dao.CropDao;
import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.dao.UserDao;
import com.dasinong.ploughHelper.dao.VarietyDao;
import com.dasinong.ploughHelper.inputParser.UserParser;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.Variety;
import com.dasinong.ploughHelper.security.Token;


@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/regUser",produces="application/json")
	@ResponseBody
	public Object reg(HttpServletRequest request, HttpServletResponse response) {
	
		UserDao userdao = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user= (new UserParser(request)).getUser();
			userdao.save(user);
						
			result.put("data",user);
			result.put("respCode",200);
			result.put("message","注册成功");
			Token token = new Token(user.getUserId());
			request.getSession().setAttribute("Token", token);
			return result;
		}
		catch(Exception e)
		{
			result.put("respCode", "500");
			result.put("message", e.getMessage());
			return result;
		}
	}
	
	@RequestMapping(value = "/login",produces="application/json")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response) {
	
		UserDao userDao = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
	
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Token token = (Token) request.getSession().getAttribute("Token");
			if (token!=null){
				result.put("respCode", 200);
				result.put("message", "已经登录");
				return result;
			}
			
			String userName = request.getParameter("userName");
			User user = userDao.findByUserName(userName);
			if (user==null){
				result.put("respCode",110);
				result.put("message", "用户不存在");
				return result;
			}
		
			String passWord = request.getParameter("password");
			if (user.getPassword().equals(passWord)){
				token = new Token(user.getUserId());
				request.getSession().setAttribute("Token", token);
				result.put("respCode",200);
				result.put("message", "登陆成功");
				return result;
			}
			else{
				result.put("respCode", 120);
				result.put("message", "密码错误");
				return result;
			}
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("respDes", e.getCause().getMessage());
			return result;
		}
	}
	
	@RequestMapping(value = "/authRegLog",produces="application/json")
	@ResponseBody
	public Object authRegLog(HttpServletRequest request, HttpServletResponse response) {
	
		UserDao userDao = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
	
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Token token = (Token) request.getSession().getAttribute("Token");
			if (token!=null){
				result.put("respCode", 200);
				result.put("message", "已经登录");
				return result;
			}
			
			String cellphone = request.getParameter("cellphone");
			User user = userDao.findByCellphone(cellphone);
			if (user!=null){
				token = new Token(user.getUserId());
				request.getSession().setAttribute("Token", token );
				result.put("respCode",200);
				result.put("message", "用户已存在,登陆");
				result.put("data",user);
				return result;
			}
			else{
				user = new User();
				user.setCellPhone(cellphone);
				user.setUserName(cellphone);
				user.setPassword(cellphone.substring(cellphone.length()-6));

				userDao.save(user);
				token = new Token(user.getUserId());
				request.getSession().setAttribute("Token", token);
				result.put("respCode", 200);
				result.put("message", "注册成功");
				result.put("data", user);
				return result;
			}
		    
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("respDes", e.getCause().getMessage());
			return result;
		}
	}

}
