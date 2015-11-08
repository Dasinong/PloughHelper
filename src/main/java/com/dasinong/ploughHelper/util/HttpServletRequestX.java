package com.dasinong.ploughHelper.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.exceptions.MissingParameterException;

/**
 * 
 * @author xiahonggao
 *
 * HttpServeletRequest的wrapper，它的好处是
 * - 封装了各种常见的类型解析
 * - 抛出的异常能被BaseController处理
 * 
 */
public class HttpServletRequestX {
	
	private HttpServletRequest request;

	public HttpServletRequestX(HttpServletRequest request) {
		this.request = request;
	}
	
	public Long getLong(String paramName) throws InvalidParameterException, MissingParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			throw new MissingParameterException(paramName);
		}
		
		try {
			return Long.valueOf(valStr);
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Long");
		}
	}
	
	public Long getLongOptional(String paramName, Long defaultVal) throws InvalidParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			return defaultVal;
		}
		
		try {
			return Long.valueOf(valStr);
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Long");
		}
	}
	
	public Integer getIntOptional(String paramName) throws InvalidParameterException, MissingParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			throw new MissingParameterException(paramName);
		}
		
		try {
			return Integer.parseInt(valStr);
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Integer");
		}
	}
	
	public Integer getIntOptional(String paramName, Integer defaultVal) throws InvalidParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			return defaultVal;
		}
		
		try {
			return Integer.parseInt(valStr);
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Integer");
		}
	}
	
	public Double getDouble(String paramName) throws InvalidParameterException, MissingParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			throw new MissingParameterException(paramName);
		}
		
		try {
			return Double.parseDouble(valStr);
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Double");
		}
	}
	
	public Double getDoubleOptional(String paramName, Double defaultVal) throws InvalidParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			return defaultVal;
		}
		
		try {
			return Double.parseDouble(valStr);
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Double");
		}
	}
	
	public Boolean getBool(String paramName) throws InvalidParameterException, MissingParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			throw new MissingParameterException(paramName);
		}
		
		try {
			return Boolean.parseBoolean(valStr);
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Boolean");
		}
	}
	
	public Boolean getBoolOptional(String paramName, Boolean defaultVal) throws InvalidParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			return defaultVal;
		}
		
		try {
			return Boolean.parseBoolean(valStr);
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Boolean");
		}
	}
	
	public String getString(String paramName) throws MissingParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			throw new MissingParameterException(paramName);
		}
		
		return valStr;
	}
	
	// getString allows empty string (""), while getNonEmptyString doesn't
	public String getNonEmptyString(String paramName) throws MissingParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null || "".equals(valStr)) {
			throw new MissingParameterException(paramName);
		}
		
		return valStr;
	}
	
	public String getStringOptional(String paramName, String defaultVal) {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			return defaultVal;
		}
		
		return valStr;
	}
	
	public Date getDate(String paramName) throws InvalidParameterException, MissingParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			throw new MissingParameterException(paramName);
		}
		
		try {
			try {
				return new Date(Long.parseLong(valStr));
			} catch (Exception ex) {
				return new Date(valStr);
			}
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Date");
		}
	}
	
	public Date getDateOptional(String paramName, Date defaultVal) throws InvalidParameterException {
		String valStr = request.getParameter(paramName);
		if (valStr == null) {
			return defaultVal;
		}
		
		try {
			try {
				return new Date(Long.parseLong(valStr));
			} catch (Exception ex) {
				return new Date(valStr);
			}
		} catch (Exception ex) {
			throw new InvalidParameterException(paramName, "Date");
		}
	}
}
