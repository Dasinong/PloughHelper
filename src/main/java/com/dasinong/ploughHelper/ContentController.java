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
import com.dasinong.ploughHelper.contentLoader.LoadStep;
import com.dasinong.ploughHelper.contentLoader.LoadVariety;

@Controller
public class ContentController {
	
private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/loadLocation", produces="application/json")
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
	
	@RequestMapping(value = "/loadStep", produces="application/json")
	@ResponseBody
	public Object loadStep(HttpServletRequest request, HttpServletResponse response) {
	  LoadStep ls = new LoadStep();
	  try {
		ls.readFile();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
	  return "OK";
	}
	
	@RequestMapping(value = "/loadVariety", produces="application/json")
	@ResponseBody
	public Object loadVariety(HttpServletRequest request, HttpServletResponse response) {
	  LoadVariety lv = new LoadVariety();
	  try {
		lv.readFile();;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
	  return "OK";
	}

	@RequestMapping(value = "/testVariety", produces="application/json")
	@ResponseBody
	public Object testVariety(HttpServletRequest request, HttpServletResponse response) {
	  LoadVariety lv = new LoadVariety();
	  lv.test();
	  
	  return "OK";
	}
}
