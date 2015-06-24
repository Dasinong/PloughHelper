package com.dasinong.ploughHelper;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dasinong.ploughHelper.facade.ISoilFacade;
import com.dasinong.ploughHelper.model.User;


@Controller
public class PetDisController {
	
	@RequestMapping(value = "/petDisCon", produces="application/json")
	@ResponseBody
	public Object loadSoilS(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		ISoilFacade sf = (ISoilFacade) ContextLoader.getCurrentWebApplicationContext().getBean("soilFacade");
		return result;
	}
	
	
	@RequestMapping(value = "/uploadPetDisPic", produces="application/json")
	@ResponseBody
	public Object uploadPet(MultipartHttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		
	    System.out.println(System.getProperty("user.dir"));
		Map<String,MultipartFile> imgFiles = request.getFileMap();
		MultipartFile imgFile;
		
		if (!imgFiles.isEmpty()){
			Iterator<Map.Entry<String,MultipartFile>> it = imgFiles.entrySet().iterator();
			while (it.hasNext()){
				Map.Entry<String,MultipartFile> entry = it.next();
				imgFile = entry.getValue();
				String ext = imgFile.getName().split(".")[1];
				System.out.println(imgFile.getContentType());
				String filePath = request.getSession().getServletContext().getRealPath("/");
				Date date = new Date();
				String fileName = ""+ date.getTime();
				fileName = fileName +user.getUserId()+"."+ext;
				System.out.println(filePath+fileName);
				//File dest = new File(fileName+fileName);
				//File dest = new File("E:/git/PloughHelper/src/main/java/avater/"+fileName);
				File dest = new File("/usr/local/tomcat7/webapps/ploughHelper/WEB-INF/avater/"+fileName);
				imgFile.transferTo(dest);
			}
		}
		result.put("respCode", 200);
		result.put("message","上传成功");
	    return result;
	}

}
