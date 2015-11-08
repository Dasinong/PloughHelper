package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dasinong.ploughHelper.exceptions.GenerateAppAccessTokenException;
import com.dasinong.ploughHelper.exceptions.GenerateUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidAppAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.MultipleUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.ParameterMissingException;
import com.dasinong.ploughHelper.exceptions.ResourceNotFoundException;
import com.dasinong.ploughHelper.exceptions.UserNotFoundInSessionException;
import com.dasinong.ploughHelper.exceptions.WeatherAlreadySubscribedException;

public class BaseController {

	// TODO (xiahonggao): make all controllers extend this controller
	
	/**
	 * Range 100 - 200 is reserved for session/token
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(UserNotFoundInSessionException.class)
	@ResponseBody
	public Object handleUserNotFoundError(
		HttpServletRequest req, 
		UserNotFoundInSessionException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 100);
		result.put("message", "用户没有登录");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(InvalidAppAccessTokenException.class)
	@ResponseBody
	public Object handleInvalidAppAccessTokenException(
		HttpServletRequest req, 
		InvalidAppAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 110);
		result.put("message", "不合法的App Access Token");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(GenerateAppAccessTokenException.class)
	@ResponseBody
	public Object handleGenerateAppAccessTokenException(
		HttpServletRequest req, 
		GenerateAppAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 111);
		result.put("message", "无法产生App Access Token (appId=" + exception.getAppId() + ")");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(InvalidUserAccessTokenException.class)
	@ResponseBody
	public Object handleInvalidUserAccessTokenException(
		HttpServletRequest req, 
		InvalidUserAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 120);
		result.put("message", "不合法的User Access Token");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(MultipleUserAccessTokenException.class)
	@ResponseBody
	public Object handleMultipleUserAccessTokenException(
		HttpServletRequest req, 
		MultipleUserAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 121);
		result.put("message", "多个User Access Token");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(GenerateUserAccessTokenException.class)
	@ResponseBody
	public Object handleGenerateUserAccessTokenException(
		HttpServletRequest req, 
		GenerateUserAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 122);
		result.put("message", "无法生成UserAccessToken");
		return result;
	}
	
	/**
	 * Range 300 - 400 is reserved for parameter validation
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(ParameterMissingException.class)
	@ResponseBody
	public Object hanleMissingParameterError(
		HttpServletRequest req, 
		ParameterMissingException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 300);
		if (exception.getParamName() != null) {
			result.put("message", exception.getParamName() + "参数缺失");
		} else {
			result.put("message", "参数不全");
		}
		return result;
	}
	
	/**
	 * range 400 - 499 is reserved for resource errors
	 * 500 is reversed for unknown server error
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public Object handleResourceNotFound(
		HttpServletRequest req, 
		ResourceNotFoundException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 404);
		result.put("message", "无法找到"+exception.getResourceType()+exception.getResourceId());
		return result;
	}
	
	/**
	 * Range 1000 - 1100 is reserved for database exception
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	public Object handleDatabaseError(
		HttpServletRequest req, 
		DataAccessException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 1000);
		result.put("message", "数据库访问错误");
		return result;
	}
		
	/**
	 * Range 1100 - 1200 is reserved for weather subscription
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(WeatherAlreadySubscribedException.class)
	@ResponseBody
	public Object handleWeatherAlreadySubscribed(HttpServletRequest req, Exception exception) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 1100);
		result.put("message", "已经关注了该地区得天气");
		return result;
	}
	
	/**
	 * This catches every exception and returns 500 which means
	 * uncaught internal error.
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleError(HttpServletRequest req, Exception exception) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 500);
		result.put("message", "服务器内部错误");
		return result;
	}
	
}
