package com.dasinong.ploughHelper;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dasinong.ploughHelper.model.User;


@Controller
public class PetDisController extends RequireUserLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(PetDisController.class);
	
	@Autowired
    ServletContext servletContext; 
	
	@RequestMapping(value = "/getPetDisByLocation", produces="application/json")
	@ResponseBody
	public Object getPetDisByLocation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		
		Long locationId;
		try{
			locationId = Long.parseLong(request.getParameter("locationId"));
		}
		catch(Exception e){
			result.put("respCode",300);
			result.put("message","参数错误");
			return result;
		}
	
		return result;
	}
	
	
	@RequestMapping(value = "/uploadPetDisPic", produces="application/json")
	@ResponseBody
	public Object uploadPetDisPic(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = this.getLoginUser(request);
		Map<String,Object> result = new HashMap<String,Object>();
		
	    System.out.println(System.getProperty("user.dir"));
		Map<String,MultipartFile> imgFiles = request.getFileMap();
		MultipartFile imgFile;
		
		if (!imgFiles.isEmpty()){
			Iterator<Map.Entry<String,MultipartFile>> it = imgFiles.entrySet().iterator();
			while (it.hasNext()){
				Map.Entry<String,MultipartFile> entry = it.next();
				imgFile = entry.getValue();
				String ext="";
				String[] origf = imgFile.getOriginalFilename().split("\\.");
				if (origf.length>=2){
					ext = origf[origf.length-1];
				}

				String filePath = this.servletContext.getRealPath("/");
				Date date = new Date();
				String fileName = ""+ date.getTime();
				fileName = fileName.substring(4);
			    Random rnd = new Random();
				fileName = fileName+rnd.nextInt(9999);
				fileName = fileName +user.getUserId()+"."+ext;
				System.out.println(filePath+"../userPetDis/" + fileName);
				File dest = new File(filePath+"../userPetDis/" + fileName);
				imgFile.transferTo(dest);
			}
		}
		
		String cropName;
		String harmType;
		String harmName;
		String harmPart;
		String harmTime;
		String harmDis;
		String operation;
		cropName = request.getParameter("cropName");
		System.out.println(cropName);
		
		result.put("respCode", 200);
		result.put("message","上传成功");
	    return result;
	}
	
	

}
