package com.dasinong.ploughHelper;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dasinong.ploughHelper.facade.IDisasterReportFacade;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

@Controller
public class DisasterReportController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(DisasterReportController.class);

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/insertDisasterReport", produces = "application/json")
	@ResponseBody
	public Object insertDisasterReport(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpServletRequestX requestX = new HttpServletRequestX(request);

		String cropName = requestX.getString("cropName");
		Long fieldId = requestX.getLong("fieldId");
		String disasterType = requestX.getString("disasterType");
		String disasterName = requestX.getString("disasterName");
		String affectedArea = requestX.getString("affectedArea");
		String eruptionTime = requestX.getString("eruptionTime");
		String disasterDist = requestX.getString("disasterDist"); // disaster
																	// distribution
																	// 灾害分布
		String fieldOperations = requestX.getString("fieldOperations");
		String imageFilenames = ""; // 存放图片文件名，多个文件名用逗号分隔，最多六张图片

		try {
			System.out.println("szc:" + System.getProperty("user.dir"));
			Map<String, MultipartFile> imgFiles = request.getFileMap();
			MultipartFile imgFile;
			if (!imgFiles.isEmpty()) {
				Iterator<Map.Entry<String, MultipartFile>> it = imgFiles.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, MultipartFile> entry = it.next();
					imgFile = entry.getValue();
					String ext = "";
					String[] origf = imgFile.getOriginalFilename().split("\\.");
					if (origf.length >= 2) {
						ext = origf[origf.length - 1];
					}

					String filePath = this.servletContext.getRealPath("/");
					Date date = new Date();
					String fileName = "" + date.getTime();
					fileName = fileName.substring(4);
					Random rnd = new Random();
					fileName = fileName + rnd.nextInt(9999);
					// fileName = fileName +user.getUserId()+"."+ext;
					fileName = fileName + "." + ext;
					System.out.println(filePath + "../userPetDis/" + fileName);
					File dest = new File(filePath + "../userPetDis/" + fileName);
					imgFile.transferTo(dest);
					imageFilenames += fileName + ",";
				}
				imageFilenames = imageFilenames.substring(0, imageFilenames.length() - 1);
				System.out.println(imageFilenames);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("pictures upload error!");
			result.put("respCode", 100);
			result.put("message", "图片上传失败");
			return result;
		}

		IDisasterReportFacade idrf = (IDisasterReportFacade) ContextLoader.getCurrentWebApplicationContext()
				.getBean("disasterReportFacade");
		return idrf.insertDisasterReport(cropName, fieldId, disasterType, disasterName, affectedArea, eruptionTime,
				disasterDist, fieldOperations, imageFilenames);
	}
}
