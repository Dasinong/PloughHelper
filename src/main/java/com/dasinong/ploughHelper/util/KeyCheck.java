package com.dasinong.ploughHelper.util;

import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class KeyCheck implements HandlerInterceptor {

	public String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		System.out.println(requestUrl);
		if (null != allowUrls && allowUrls.length >= 1)
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}

		String apikey = request.getParameter("apikey");
		String deviceId = request.getParameter("deviceId");
		String time = request.getParameter("time");

		if (apikey != null && deviceId != null && time != null && !"".equals(apikey) && !"".equals(deviceId)
				&& !"".equals(time)) {
			byte[] skey = "yxxwhgz".getBytes();
			SecretKeySpec signingKey = new SecretKeySpec(skey, "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal((deviceId + time).getBytes());
			byte[] encodeBytes = Base64.encodeBase64(rawHmac);
			String finalkey = new String(encodeBytes, "utf-8");
			finalkey = URLEncoder.encode(finalkey, "utf-8");
			if (finalkey.equals(apikey))
				return true;
			else {
				System.out.println("UserAgent: " + request.getHeader("user-agent"));
				System.out.println("KeyError: " + request.getQueryString());
				return true;
			}
		} else {
			/*
			 * for (String device : Env.getEnv().checkDevice){ if
			 * (request.getHeader("user-agent").toLowerCase().contains(device)){
			 * System.out.println("warning: "+ request.getHeader("user-agent"));
			 * return true; } }
			 */
			System.out.println("UserAgent: " + request.getHeader("user-agent"));
			System.out.println("KeyError: " + request.getQueryString());
			return true;
		}
	}

	public static void main(String[] args) {

		// deviceId=&apikey=HGM%2FyAJia%2F5f0RpiscOh%2FH0WYro%3D%0A&
		String deviceId = "d871-476f-2e3c-77fb";
		String time = "2341";
		byte[] skey = "yxxwhgz".getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(skey, "HmacSHA1");
		Mac mac = null;
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal((deviceId + time).getBytes());
			byte[] encodeBytes = Base64.encodeBase64(rawHmac);
			// byte[] encodeBytes = Base64.getEncoder().encode(rawHmac);
			String finalkey = new String(encodeBytes, "utf-8");
			finalkey = URLEncoder.encode(finalkey, "utf-8");
			System.out.println(finalkey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
