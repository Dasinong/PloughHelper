package com.dasinong.ploughHelper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dasinong.ploughHelper.accessTokenManager.UserAccessTokenManager;
import com.dasinong.ploughHelper.dao.ISecurityCodeDao;
import com.dasinong.ploughHelper.dao.IUserAccessTokenDao;
import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.dao.UserDao;
import com.dasinong.ploughHelper.inputParser.UserParser;
import com.dasinong.ploughHelper.model.SecurityCode;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.UserAccessToken;
import com.dasinong.ploughHelper.outputWrapper.UserWrapper;
import com.dasinong.ploughHelper.util.Env;
import com.dasinong.ploughHelper.util.Refcode;
import com.dasinong.ploughHelper.util.SmsService;
import com.dasinong.ploughHelper.viewerContext.ViewerContext;

@Controller
public class UserLoginController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/regUser",produces="application/json")
	@ResponseBody
	public Object reg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ViewerContext vc = this.getViewerContext(request);
		IUserDao userdao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		UserAccessTokenManager tokenManager = new UserAccessTokenManager();
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user= (new UserParser(request)).getUser();
			user.setIsPassSet(false);
			user.setAuthenticated(true);
			user.setCreateAt(new Date());
			String refcode;
			do{
				refcode = Refcode.GenerateRefcode();
			}
			while (userdao.getUIDbyRef(refcode)>0);
			user.setRefcode(refcode);
			userdao.save(user);
				
			UserWrapper userWrapper = new UserWrapper(user);
			result.put("data",userWrapper);
			result.put("respCode",200);
			result.put("message","注册成功");
			
			// TODO (xiahonggao): deprecate session
			UserAccessToken token = null;
			if (vc.getAppId() != null)
				token = tokenManager.generate(user.getUserId(), vc.getAppId());
			request.getSession().setAttribute("User", user);
			request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
			
			if (token != null)
				result.put("accessToken", token.getToken());
			
			return result;
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("message", e.getMessage());
			return result;
		}
	}
	
	@RequestMapping(value = "/login",produces="application/json")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ViewerContext vc = this.getViewerContext(request);
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		UserAccessTokenManager tokenManager = new UserAccessTokenManager();
	
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			// TODO (xiahonggao): remove this code when android native
			// starts passing cellphone
			User user = this.getLoginUser(request);
			if (user!=null){
				result.put("respCode", 200);
				result.put("message", "已经登录");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				return result;
			}
			
			String cellphone = request.getParameter("cellphone");
			user = userDao.findByCellphone(cellphone);
			if (user == null){
				result.put("respCode",110);
				result.put("message", "用户不存在");
				return result;
			}
		
			String passWord = request.getParameter("password");
			if (user.validatePassword(passWord)) {
				user.setLastLogin(new Date());			
				userDao.update(user);
				
				// TODO (xiahonggao): deprecate session
				UserAccessToken token = null;
				if (vc.getAppId() != null)
					token = tokenManager.generate(user.getUserId(), vc.getAppId());
				request.getSession().setAttribute("User", user);
				request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
				
				result.put("respCode",200);
				result.put("message", "登陆成功");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				if (token != null)
					result.put("accessToken", token.getToken());
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
	
	@RequestMapping(value = "/authRegLog",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object authRegLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ViewerContext vc = this.getViewerContext(request);
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		UserAccessTokenManager tokenManager = new UserAccessTokenManager();
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = this.getLoginUser(request);
			if (user!=null){
				result.put("respCode", 200);
				result.put("message", "已经登录");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				UserAccessToken token = null;
				
				// TODO (xiahonggao): remove if block once android frontend starts
				// passing appId;
				if (vc.getAppId() != null)
					token = tokenManager.generate(user.getUserId(), vc.getAppId());
				if (token != null)
					result.put("accessToken", token.getToken());
				
				return result;
			}
			
			String cellphone = request.getParameter("cellphone");
			user = userDao.findByCellphone(cellphone);
			if (user!=null){
				user.setLastLogin(new Date());
				
				userDao.update(user);
				
				// TODO (xiahonggao): deprecate session
				UserAccessToken token = null;
				if (vc.getAppId() != null)
					token = tokenManager.generate(user.getUserId(), vc.getAppId());
				request.getSession().setAttribute("User", user);
				request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
				
				result.put("respCode",200);
				result.put("message", "用户已存在,登陆");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				if (token != null)
					result.put("accessToken", token.getToken());
				return result;
			} else {
				user = new User();
				user.setCellPhone(cellphone);
				
				String refcode;
				do {
					refcode = Refcode.GenerateRefcode();
				}
				while (userDao.getUIDbyRef(refcode)>0);
				user.setRefcode(refcode);
				
				//@Xiyao
				//Generally saying authRegLog should not be used as register entry
				String channel =  request.getParameter("channel");
				user.setChannel(channel);
				try{
					int institutionId = Integer.parseInt(request.getParameter("institutionId"));
					user.setInstitutionId(institutionId);
				}
				catch(Exception e){
					System.out.println("Issue with institutionId");
				}
				

				user.setUserName("");
				user.setAuthenticated(true);
				user.setCreateAt(new Date());

				userDao.save(user);
				
				// TODO (xiahonggao): deprecate session
				UserAccessToken token = null;
				if (vc.getAppId() != null)
					token = tokenManager.generate(user.getUserId(), vc.getAppId());
				request.getSession().setAttribute("User", user);
				request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
				
				result.put("respCode", 200);
				result.put("message", "注册成功");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				if (token != null)
					result.put("accessToken", token.getToken());
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
	
	
	
	
	@RequestMapping(value = "/logout",produces="application/json")
	@ResponseBody
	public Object logout(HttpServletRequest request, HttpServletResponse response) {
		IUserAccessTokenDao tokenDao = (IUserAccessTokenDao) ContextLoader.getCurrentWebApplicationContext().getBean("userAccessTokenDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		UserAccessTokenManager tokenManager = new UserAccessTokenManager();
		
		try{
			User user = this.getLoginUser(request);
			ViewerContext vc = this.getViewerContext(request);
			if (user!=null){
				result.put("respCode", 200);
				result.put("message", "注销成功");
				
				// TODO (xiahonggao): deprecate session
				tokenDao.deleteByUserIdAndAppId(vc.getUserId(), vc.getAppId());
				request.getSession().removeAttribute("User");
				
				return result;
			}
			else{
				result.put("respCode", 110);
				result.put("message", "用户尚未登陆");
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
	
	
	@RequestMapping(value = "/checkUser",produces="application/json")
	@ResponseBody
	public Object checkUser(HttpServletRequest request, HttpServletResponse response) {
	
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
	
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			String cellphone = request.getParameter("cellphone");
			User user = userDao.findByCellphone(cellphone);
			if (user != null) {
				result.put("respCode",200);
				result.put("message", "用户已存在");
				result.put("data",true);
				return result;
			} else{
				result.put("respCode", 200);
				result.put("message", "用户不存在，请先注册");
				result.put("data",false);
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
	
	@RequestMapping(value = "/isPassSet",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object isPassSet(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			// if user comes from login flow, check cellphone
			String cellphone = request.getParameter("cellphone");
			if (cellphone == null) {
				result.put("respCode", 300);
				result.put("message", "cellphone缺失");
			    return result;
			}
			
			IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
			User user = userDao.findByCellphone(cellphone);
				
			if (user == null) {
				result.put("respCode", 110);
				result.put("message", "用户不存在");
			    return result;
			}
			
			result.put("respCode", 200);
			result.put("message", "检验密码是否初始化成功");
			result.put("data", user.getIsPassSet());
			return result;
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("respDes", e.getCause().getMessage());
			return result;
		}
	}
	
	@RequestMapping(value = "/requestSecurityCode",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object requestSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		ISecurityCodeDao codeDao = (ISecurityCodeDao) ContextLoader.getCurrentWebApplicationContext().getBean("securityCodeDao");
	
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			String cellphone = request.getParameter("cellphone");
			User user = userDao.findByCellphone(cellphone);
			if (user!=null){
				SmsService sms = new SmsService();
				String securityCode = sms.generateSecurityCode(6);
				SmsService.securityCodeSMS(securityCode, cellphone);
				
				// TODO (xiahonggao): deprecate session
				SecurityCode codeObj = codeDao.create(securityCode);
				request.getSession().setAttribute("securityCode", securityCode);
				
				result.put("respCode",200);
				result.put("message", "临时密码已经发送");
				result.put("data", codeObj.getCodeId());
				return result;
			}
			else{
				result.put("respCode", 110);
				result.put("message", "用户不存在，请先注册");
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
	
	@RequestMapping(value = "/loginWithSecCode",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object loginWithSecCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ViewerContext vc = this.getViewerContext(request);
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		ISecurityCodeDao codeDao = (ISecurityCodeDao) ContextLoader.getCurrentWebApplicationContext().getBean("securityCodeDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		UserAccessTokenManager tokenManager = new UserAccessTokenManager();
		
		try{
			String cellphone = request.getParameter("cellphone");
			String seccode = request.getParameter("seccode");
			String codeId = request.getParameter("codeId");
			
			// TODO (xiahonggao): deprecate session
			String savedCode = null;
			if (codeId != null) {
				SecurityCode codeObj = codeDao.findById(Long.valueOf(codeId));
				savedCode = codeObj.getCode();
			} else {
				savedCode = (String) request.getSession().getAttribute("securityCode");
			}
			
			if (seccode == null){
				result.put("respCode",310);
				result.put("message", "未输入验证码");
				return result;
			}
			
			if (savedCode == null){
				result.put("respCode",312);
				result.put("message", "验证码未初始化");
				return result;
			}
			User user = userDao.findByCellphone(cellphone);
			if (user!=null){
				if (savedCode.equals(seccode)){
					user.setAuthenticated(true);
				    user.setLastLogin(new Date());
				    if (user.getRefcode()==null){
						String refcode;
						do{
							refcode = Refcode.GenerateRefcode();
						}
						while (userDao.getUIDbyRef(refcode)>0);
						user.setRefcode(refcode);
					}
					userDao.update(user);
					
					// TODO (xiahonggao): deprecate session
					UserAccessToken token = null;
					if (vc.getAppId() != null)
						token = tokenManager.generate(user.getUserId(), vc.getAppId());
					request.getSession().removeAttribute("securityCode");
					request.getSession().setAttribute("User", user);
					request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
					
					UserWrapper userWrapper = new UserWrapper(user);
					result.put("data",userWrapper);
					result.put("respCode",200);
					result.put("message", "登陆成功");
					if (token != null)
						result.put("accessToken", token.getToken());
					return result;
				}
				else{
					result.put("respCode",115);
					result.put("message", "验证码错误");
					return result;
				}
			}
			else{
				result.put("respCode", 110);
				result.put("message", "用户不存在，请先注册");
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
	
	
	@RequestMapping(value = "/qqAuthRegLog",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object qqAuthRegLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ViewerContext vc = this.getViewerContext(request);
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		UserAccessTokenManager tokenManager = new UserAccessTokenManager();
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = this.getLoginUser(request);
			if (user!=null){
				result.put("respCode", 200);
				result.put("message", "已经登录");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				return result;
			}
			
			String qqtoken = request.getParameter("qqtoken");
			String avater = request.getParameter("avater");
			String username = request.getParameter("username");
			user = userDao.findByQQ(qqtoken);
			if (user!=null){
				user.setLastLogin(new Date());
				userDao.update(user);
				
				// TODO (xiahonggao): deprecate session
				UserAccessToken token = null;
				if (vc.getAppId() != null)
					token = tokenManager.generate(user.getUserId(), vc.getAppId());
				request.getSession().setAttribute("User", user);
				request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
				
				result.put("respCode",200);
				result.put("message", "用户已存在,登陆");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				if (token != null)
					result.put("accessToken", token.getToken());
				return result;
			}
			else{
				user = new User();
				user.setQqtoken(qqtoken);
				user.setUserName(username);
				user.setPictureId(avater);
				
				user.setCreateAt(new Date());

				String channel =  request.getParameter("channel");
				user.setChannel(channel);
				try{
					int institutionId = Integer.parseInt(request.getParameter("institutionId"));
					user.setInstitutionId(institutionId);
				}
				catch(Exception e){
					System.out.println("Issue with institutionId");
				}
				
				
				String refcode;
				do{
					refcode = Refcode.GenerateRefcode();
				}
				while (userDao.getUIDbyRef(refcode)>0);
				user.setRefcode(refcode);

				userDao.save(user);
				
				// TODO (xiahonggao): deprecate session
				UserAccessToken token = null;
				if (vc.getAppId() != null)
					token = tokenManager.generate(user.getUserId(), vc.getAppId());
				request.getSession().setAttribute("User", user);
				request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
				
				result.put("respCode", 200);
				result.put("message", "注册成功");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				if (token != null)
					result.put("accessToken", token.getToken());
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
	
	
	@RequestMapping(value = "/weixinAuthRegLog",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object weixinAuthRegLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ViewerContext vc = this.getViewerContext(request);
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		UserAccessTokenManager tokenManager = new UserAccessTokenManager();
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = this.getLoginUser(request);
			if (user!=null){
				result.put("respCode", 200);
				result.put("message", "已经登录");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				return result;
			}
			
			String weixintoken = request.getParameter("weixintoken");
			String avater = request.getParameter("avater");
			String username = request.getParameter("username");
			user = userDao.findByWeixin(weixintoken);
			if (user!=null){
				user.setLastLogin(new Date());
				
				// TODO (xiahonggao): deprecate session
				UserAccessToken token = null;
				if (vc.getAppId() != null)
					token = tokenManager.generate(user.getUserId(), vc.getAppId());
				request.getSession().setAttribute("User", user);
				request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
				
				result.put("respCode",200);
				result.put("message", "用户已存在,登陆");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				if (token != null)
					result.put("accessToken", token.getToken());
				return result;
			}
			else{
				user = new User();
				user.setWeixintoken(weixintoken);
				user.setUserName(username);
				user.setPictureId(avater);
				user.setCreateAt(new Date());
				
				String refcode;
				do{
					refcode = Refcode.GenerateRefcode();
				}
				while (userDao.getUIDbyRef(refcode)>0);
				user.setRefcode(refcode);

				String channel =  request.getParameter("channel");
				user.setChannel(channel);
				try{
					int institutionId = Integer.parseInt(request.getParameter("institutionId"));
					user.setInstitutionId(institutionId);
				}
				catch(Exception e){
					System.out.println("Issue with institutionId");
				}
				
				userDao.save(user);
				
				// TODO (xiahonggao): deprecate session
				UserAccessToken token = null;
				if (vc.getAppId() != null)
					token = tokenManager.generate(user.getUserId(), vc.getAppId());
				request.getSession().setAttribute("User", user);
				request.getSession().setMaxInactiveInterval(Env.getEnv().sessionTimeout);
				
				result.put("respCode", 200);
				result.put("message", "注册成功");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				if (token != null)
					result.put("accessToken", token.getToken());
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