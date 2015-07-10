package com.dasinong.ploughHelper;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dasinong.ploughHelper.facade.IDisasterReportFacade;
import com.dasinong.ploughHelper.model.User;

@Controller
public class DisasterReportController {

	private static final Logger logger = LoggerFactory.getLogger(DisasterReportController.class);

	@RequestMapping(value = "/insertDisasterReport", produces="application/json")
	@ResponseBody
	public Object insertDisasterReport(MultipartHttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
/*		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		
*/		
		String cropName = request.getParameter("cropName");
		Long fieldId = Long.parseLong(request.getParameter("fieldId"));
		String disasterType = request.getParameter("disasterType");
		String disasterName = request.getParameter("disasterName");
		String affectedArea = request.getParameter("affectedArea");
		String eruptionTime = request.getParameter("eruptionTime");
		String disasterDist = request.getParameter("disasterDist"); //disaster distribution 灾害分布
		String fieldOperations = request.getParameter("fieldOperations");
		String imageFilenames = ""; //存放图片文件名，多个文件名用逗号分隔，最多六张图片
		
		try{
		    System.out.println("szc:"+System.getProperty("user.dir"));
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
	
					String filePath = request.getSession().getServletContext().getRealPath("/");
					Date date = new Date();
					String fileName = ""+ date.getTime();
					fileName = fileName.substring(4);
				    Random rnd = new Random();
					fileName = fileName+rnd.nextInt(9999);
					//fileName = fileName +user.getUserId()+"."+ext;
					fileName = fileName + "."+ext;
					System.out.println(filePath+"../userPetDis/" + fileName);
					File dest = new File(filePath+"../userPetDis/" + fileName);
					imgFile.transferTo(dest);
					imageFilenames += fileName+",";
				}
				imageFilenames = imageFilenames.substring(0, imageFilenames.length() - 1);
				System.out.println(imageFilenames);
			}
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("pictures upload error!");
			result.put("respCode",100);
			result.put("message","图片上传失败");
			return result;
		}
		
		IDisasterReportFacade idrf = (IDisasterReportFacade) ContextLoader.getCurrentWebApplicationContext().getBean("disasterReportFacade");
		return idrf.insertDisasterReport(cropName, fieldId, disasterType, disasterName, affectedArea, eruptionTime, disasterDist, fieldOperations, imageFilenames);
	}		
}
