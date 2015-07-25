package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SystemManagement {
	@RequestMapping(value = "/weatherIssue", produces="application/json")
	@ResponseBody
	public Object insertSoilReport(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> result = new HashMap<String,Object>();
		String issue = request.getParameter("issue");
		String monitorLocationId = request.getParameter("monitorLocationId");
		if (issue==null || issue.equals("") || monitorLocationId==null || monitorLocationId.equals("")){
			result.put("message", "请输入monitorLocationId和issue");
			result.put("respCode", 300);
			return result;
		}
		System.out.println("Issue: " + issue +" on "+monitorLocationId);
		result.put("message", "提交成功");
		result.put("respCode", 200);
		return result;
	}
}
