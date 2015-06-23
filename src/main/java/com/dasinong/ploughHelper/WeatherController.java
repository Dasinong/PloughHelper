package com.dasinong.ploughHelper;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.contentLoader.LoadLocation;

@Controller
public class WeatherController {

	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
		
		/**
		 * Simply selects the home view to render by returning its name.
		 */
	@RequestMapping(value = "/loadWeather", produces="application/json")
	@ResponseBody
	public Object loadLocation(HttpServletRequest request, HttpServletResponse response) {
	  LoadLocation ll = new LoadLocation();
	  try {
		ll.loadLocation();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return "OK";
	}

}
