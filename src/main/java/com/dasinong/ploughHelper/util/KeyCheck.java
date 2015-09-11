package com.dasinong.ploughHelper.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class KeyCheck implements HandlerInterceptor{

	public String[] allowUrls;
	public void setAllowUrls(String[] allowUrls){
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
		String requestUrl = request.getRequestURI().replace(request.getContextPath(),"");
		System.out.println(requestUrl);  
        if(null != allowUrls && allowUrls.length>=1)  
            for(String url : allowUrls) {    
                if(requestUrl.contains(url)) {    
                    return true;    
                }    
            }  
        
        String key = request.getParameter("key");
        String queryString = request.getQueryString();
        

        
        if ("yxxwhgz".equals(key)) return true;
        else {
        	for (String device : Env.getEnv().checkDevice){
        		if (request.getHeader("user-agent").toLowerCase().contains(device)){
        			System.out.println("warning: "+ request.getHeader("user-agent"));
        			return true;
        		}
        	}
        	return true;
        }
	}

}
