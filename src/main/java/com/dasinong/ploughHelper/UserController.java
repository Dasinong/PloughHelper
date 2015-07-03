package com.dasinong.ploughHelper;

import java.io.File;
import java.io.IOException;
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

import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.inputParser.UserParser;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.UserWrapper;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/regUser",produces="application/json")
	@ResponseBody
	public Object reg(HttpServletRequest request, HttpServletResponse response) {
	
		IUserDao userdao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user= (new UserParser(request)).getUser();
			userdao.save(user);
				
			UserWrapper userWrapper = new UserWrapper(user);
			result.put("data",userWrapper);
			result.put("respCode",200);
			result.put("message","注册成功");
			
			request.getSession().setAttribute("User", user);
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
	public Object login(HttpServletRequest request, HttpServletResponse response) {
	
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
	
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user!=null){
				result.put("respCode", 200);
				result.put("message", "已经登录");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				return result;
			}
			
			String userName = request.getParameter("username");
			user = userDao.findByUserName(userName);
			if (user==null){
				result.put("respCode",110);
				result.put("message", "用户不存在");
				return result;
			}
		
			String passWord = request.getParameter("password");
			if (user.getPassword().equals(passWord)){
				request.getSession().setAttribute("User", user);
				result.put("respCode",200);
				result.put("message", "登陆成功");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
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
	public Object authRegLog(HttpServletRequest request, HttpServletResponse response) {
	
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
	
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user!=null){
				result.put("respCode", 200);
				result.put("message", "已经登录");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				return result;
			}
			
			String cellphone = request.getParameter("cellphone");
			user = userDao.findByCellphone(cellphone);
			if (user!=null){
				request.getSession().setAttribute("User", user);
				result.put("respCode",200);
				result.put("message", "用户已存在,登陆");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
				return result;
			}
			else{
				user = new User();
				user.setCellPhone(cellphone);
				user.setUserName(cellphone);
				user.setPassword(cellphone.substring(cellphone.length()-6));

				userDao.save(user);
				request.getSession().setAttribute("User", user);
				result.put("respCode", 200);
				result.put("message", "注册成功");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
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
	
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user!=null){
				result.put("respCode", 200);
				result.put("message", "注销成功");
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
			if (user!=null){
				request.getSession().setAttribute("User", user);
				result.put("respCode",200);
				result.put("message", "用户已存在");
				UserWrapper userWrapper = new UserWrapper(user);
				result.put("data",userWrapper);
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
	
	@RequestMapping(value = "/loadUserProfile",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object loadUserProfile(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user==null){
				result.put("respCode", 100);
				result.put("message", "尚未登陆");
				return result;
			}
		    result.put("respCode", 200);
		    result.put("message", "获取成功");
		    UserWrapper userwrapper = new UserWrapper(user);
		    result.put("data",userwrapper);
		    return result;
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("respDes", e.getCause().getMessage());
			return result;
		}
	}
	
	
	@RequestMapping(value = "/updateProfile",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object updateProfile(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user==null){
				result.put("respCode", 100);
				result.put("message", "尚未登陆");
				return result;
			}
			IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
			
			String userName = request.getParameter("username");
			String cellphone = request.getParameter("cellphone");
			String address = request.getParameter("address");
			String pictureId = request.getParameter("pictureId");
			String telephone = request.getParameter("telephone");
			String isAuth = request.getParameter("isAuth");
			
			//String oldCellphone = user.getCellPhone();
			
			if (userName!=null && !userName.isEmpty()){
			user.setUserName(userName);
			}
			if (address!=null && !address.isEmpty()){
			user.setAddress(address);
			}
			if (cellphone!=null && !cellphone.isEmpty()){
			user.setCellPhone(cellphone);
			}
			if (pictureId!=null && !pictureId.isEmpty()){
			user.setPictureId(pictureId);
			}
			if (telephone!=null && !telephone.isEmpty()){
				user.setTelephone(telephone);
			}
			/*
			if (!oldCellphone.equals(cellphone)){
				user.setAuthenticated(false);
			}*/
			if (isAuth!=null && !isAuth.isEmpty()){
				user.setAuthenticated(true);
			}
			userDao.update(user);
			
		    result.put("respCode", 200);
		    result.put("message", "更新成功");
		    UserWrapper userwrapper = new UserWrapper(user);
		    result.put("data",userwrapper);
		    return result;
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("respDes", e.getCause().getMessage());
			return result;
		}
	}
	
	@RequestMapping(value = "/isAuth",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object isAuth(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user==null){
				result.put("respCode", 100);
				result.put("message", "尚未登陆");
				return result;
			}
			
			if (user.getAuthenticated()){
			    result.put("respCode", 200);
			    result.put("message", "已验证");
			    return result;
			}
			else{
				result.put("respCode", 120);
				result.put("message", "尚未验证");
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
	
	@RequestMapping(value = "/setAuth",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object setAuth(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user==null){
				result.put("respCode", 100);
				result.put("message", "尚未登陆");
				return result;
			}
			
			if (user.getAuthenticated()){
			    result.put("respCode", 200);
			    result.put("message", "已验证");
			    return result;
			}
			else{
				user.setAuthenticated(true);
				result.put("respCode", 210);
			    result.put("message", "提交验证");
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
	
	
	@RequestMapping(value = "/uploadAvater",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object uploadAvater(MultipartHttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user==null){
				result.put("respCode", 100);
				result.put("message", "尚未登陆");
				return result;
			}
            System.out.println(System.getProperty("user.dir"));
			MultipartFile imgFile = request.getFile("file");

			if (!imgFile.isEmpty()){
				String origFileName = imgFile.getOriginalFilename();
				String[] imgt = origFileName.split("\\.");
				String ext="";
				System.out.println(imgt.length);
				if (imgt.length>=2)
				{
					ext = imgt[imgt.length-1];
				}
				
				String filePath = request.getSession().getServletContext().getRealPath("/");
				Random rnd = new Random();
				String fileName = user.getCellPhone()+rnd.nextInt(9999)+"."+ext;
				System.out.println(filePath +"../avater/" +fileName);
				File dest = new File(filePath+"../avater/"+fileName);
				imgFile.transferTo(dest);
				user.setPictureId(fileName);
				IUserDao userdao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
				userdao.update(user);
			}
			result.put("respCode", 200);
			result.put("message","上传成功");
		    return result;
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("respDes", e.getCause().getMessage());
			return result;
		}
		
	}
	
	
	@RequestMapping(value = "/updatePassword",produces="application/json;charset=utf-8")
	@ResponseBody
	public Object updatePassword(MultipartHttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user = (User) request.getSession().getAttribute("User");
			if (user==null){
				result.put("respCode", 100);
				result.put("message", "尚未登陆");
				return result;
			}

			String oldPassword = request.getParameter("oPassword");
			String newPassword = request.getParameter("nPassword");
			
			if (oldPassword==null || newPassword == null){
				result.put("respCode",300);
				result.put("message","参数缺失");
				return result;
			}
			
			if (!user.getPassword().equals(oldPassword)){
				result.put("respCode",320);
				result.put("message", "旧密码错误");
				return result;
			}
			
			user.setPassword(newPassword);
			IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
			userDao.update(user);
			
		    return result;
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("respDes", e.getCause().getMessage());
			return result;
		}
	}
	
	public static void main(String[] args){
		String[] imgt = "crop_cache_file.jpg".split("\\.");
		System.out.println(imgt.length);
	}
}
