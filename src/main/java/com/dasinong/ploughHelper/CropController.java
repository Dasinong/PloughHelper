package com.dasinong.ploughHelper;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.contentLoader.LoadStep;

@Controller
public class CropController {

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
