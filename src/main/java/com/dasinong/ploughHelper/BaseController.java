package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dasinong.ploughHelper.exceptions.ParameterMissingException;
import com.dasinong.ploughHelper.exceptions.ResourceNotFoundException;
import com.dasinong.ploughHelper.exceptions.UserNotFoundInSessionException;

public class BaseController {

	// TODO (xiahonggao): make all controllers extend this controller
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(UserNotFoundInSessionException.class)
	@ResponseBody
	public Object handleUserNotFoundError(
		HttpServletRequest req, 
		UserNotFoundInSessionException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 100);
		result.put("respMsg", "用户没有登录");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(ParameterMissingException.class)
	@ResponseBody
	public Object hanleMissingParameterError(
		HttpServletRequest req, 
		ParameterMissingException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 300);
		result.put("respMsg", exception.getParamName() + "参数缺失");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public Object handleResourceNotFound(
		HttpServletRequest req, 
		ResourceNotFoundException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 404);
		result.put("respMsg", "无法找到"+exception.getResourceType()+exception.getResourceId());
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	public Object handleDatabaseError(
		HttpServletRequest req, 
		DataAccessException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 300);
		result.put("respMsg", "数据库访问错误");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleError(HttpServletRequest req, Exception exception) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 500);
		result.put("respMsg", "服务器内部错误");
		return result;
	}
}
