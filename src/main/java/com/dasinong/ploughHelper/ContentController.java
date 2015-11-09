package com.dasinong.ploughHelper;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.contentLoader.IUpdateDisease;
import com.dasinong.ploughHelper.contentLoader.IUpdateStep;
import com.dasinong.ploughHelper.contentLoader.LoadDiseaseAndSolution;
import com.dasinong.ploughHelper.contentLoader.LoadLocation;
import com.dasinong.ploughHelper.contentLoader.LoadProverb;
import com.dasinong.ploughHelper.contentLoader.LoadStep;
import com.dasinong.ploughHelper.contentLoader.LoadVariety;
import com.dasinong.ploughHelper.contentLoader.UpdateDiseasePicture;
import com.dasinong.ploughHelper.contentLoader.UpdateSolution;
import com.dasinong.ploughHelper.contentLoader.UpdateVariety;


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
	
	@RequestMapping(value = "/loadDiseaseSolution", produces="application/json")
	@ResponseBody
	public Object loadDiseaseSolution(HttpServletRequest request, HttpServletResponse response) {
	  LoadDiseaseAndSolution ld = new LoadDiseaseAndSolution();
	  
	  ld.run();
		
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
	
	@RequestMapping(value = "/updateStep", produces="application/json")
	@ResponseBody
	public Object updateStep(HttpServletRequest request, HttpServletResponse response) {
		IUpdateStep us = (IUpdateStep) ContextLoader.getCurrentWebApplicationContext().getBean("updateStep");
//	  us.run();
	  us.update();
	  return "OK";
	}
	
	@RequestMapping(value = "/testStep", produces="application/json")
	@ResponseBody
	public Object testStep(HttpServletRequest request, HttpServletResponse response) {
		IUpdateStep us = (IUpdateStep) ContextLoader.getCurrentWebApplicationContext().getBean("updateStep");
	  us.run();
	  us.update();
	  return "OK";
	}
	
	@RequestMapping(value = "/linkVarietySubStage", produces="application/json")
	@ResponseBody
	public Object linkVarietySubStage(HttpServletRequest request, HttpServletResponse response) {
	  LoadStep ls = new LoadStep();
	  try {
		ls.linkVarietySubstage();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  return "OK";
	}
	
	@RequestMapping(value = "/updateVariety", produces="application/json")
	@ResponseBody
	public Object updateVariety(HttpServletRequest request, HttpServletResponse response) {
		UpdateVariety uv = new UpdateVariety();
		uv.run();
		
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
	
	@RequestMapping(value = "/testUpdateSolution", produces="application/json")
	@ResponseBody
	public Object testUpdateSolution(HttpServletRequest request, HttpServletResponse response) {
	  UpdateSolution us = new UpdateSolution();
	  us.test();
	  
	  return "OK";
	}
	
	@RequestMapping(value = "/updateSolution", produces="application/json")
	@ResponseBody
	public Object updateSolution(HttpServletRequest request, HttpServletResponse response) {
	  UpdateSolution us = new UpdateSolution();
	  us.run();
	  
	  return "OK";
	}
	
	@RequestMapping(value = "/updateDisease", produces="application/json")
	@ResponseBody
	public Object updateDisease(HttpServletRequest request, HttpServletResponse response) {
		IUpdateDisease ud = (IUpdateDisease) ContextLoader.getCurrentWebApplicationContext().getBean("updateDisease");
		 
	   ud.run();
	  
	  return "OK";
	}
	
	@RequestMapping(value = "/updateDiseasePicture", produces="application/json")
	@ResponseBody
	public Object updateDiseasePicture(HttpServletRequest request, HttpServletResponse response) {
		UpdateDiseasePicture udp = new UpdateDiseasePicture();
	   udp.run();
	  
	  return "OK";
	}
	
	@RequestMapping(value = "/loadProverb", produces="application/json")
	@ResponseBody
	public Object loadProverb(HttpServletRequest request, HttpServletResponse response) {
	  LoadProverb lp = new LoadProverb();
	  lp.readFile();
	  
	  return "OK";
	}
	
	@RequestMapping(value = "/loadCrop", produces="application/json")
	@ResponseBody
	public Object loadCrop(HttpServletRequest request, HttpServletResponse response) {
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
	
}