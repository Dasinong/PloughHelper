package com.dasinong.ploughHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.util.FullTextSearch;

@Controller
public class BaiKeController {
	
	private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/searchWord", produces="application/json")
	@ResponseBody
	public Object searchWord(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		HashMap<String,Object> content = new HashMap<String,Object>();
		String key = request.getParameter("key");
		if (key==null ||key.equals("")){
			result.put("respCode", 300);
			result.put("message", "参数错误");
		};
		
		FullTextSearch bs = null;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
		     bs = new FullTextSearch("variety","E:/index/varietyIndex");
		}
		else{
			bs = new FullTextSearch("variety","/usr/local/tomcat7/webapps/lucene/variety");
		}
		bs.setHighlighterFormatter("<font color='red'>", "</font>");

		String[] a = {"varietyName", "varietySource"};
		String[] b = {"varietyName", "varietyId", "varietySource"};
		try {
			HashMap[] h = bs.search(key, a, b);
			content.put("variety", h);
		} catch (ParseException | IOException | InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		
		
		
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
		     bs = new FullTextSearch("petDisSpec","E:/index/petDisSpecIndex");
		}
		else{
			bs = new FullTextSearch("petDisSpec","/usr/local/tomcat7/webapps/lucene/petDisSpec");
		}
		bs.setHighlighterFormatter("<font color='red'>", "</font>");
		String[] resource = {"petDisSpecName", "cropName","description"};
		String[] searchResult= {"petDisSpecName", "petDisSpecId", "cropName","description","type"};
		try {
			HashMap<String,String>[] h = bs.search(key, resource, searchResult);
			List<HashMap<String,String>> ill = new ArrayList<HashMap<String,String>>();
			List<HashMap<String,String>> pest = new ArrayList<HashMap<String,String>>();
			List<HashMap<String,String>> grass = new ArrayList<HashMap<String,String>>();
			if (h!=null){
				for (int i=0;i<h.length;i++){
					if (h[i].get("type").equals("病害")) ill.add(h[i]);
					if (h[i].get("type").equals("虫害")) pest.add(h[i]);
					if (h[i].get("type").equals("草害")) grass.add(h[i]);
				}
			}
			content.put("ill",ill);
			content.put("pest",pest);
			content.put("grass",grass);
		} catch (ParseException | IOException | InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
	
		result.put("respCode", 200);
		result.put("message", "检索成功");
		result.put("data",content);
	  return result;
	}
	
	
	@RequestMapping(value = "/createIndex", produces="application/json")
	@ResponseBody
	public Object createIndex(HttpServletRequest request, HttpServletResponse response) {
		FullTextSearch bs = null;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
		     bs = new FullTextSearch("variety","E:/index/varietyIndex");
		}
		else{
			bs = new FullTextSearch("variety","/usr/local/tomcat7/webapps/lucene/variety");
		}
		bs.setHighlighterFormatter("<font color='red'>", "</font>");
		bs.createVarietyIndex();
		String[] a = {"varietyName", "varietySource"};
		String[] b = {"varietyName", "varietyId", "varietySource"};
		try {
			HashMap[] h = bs.search("玉米", a, b);
			System.out.println(h.length);
			for(int k = 0; k < h.length; k++){
				if(h[k] == null){
					break;
				}
				System.out.println(h[k].toString());
			}
		} catch (ParseException | IOException | InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
	 
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
		     bs = new FullTextSearch("petDisSpec","E:/index/petDisSpecIndex");
		}
		else{
			bs = new FullTextSearch("petDisSpec","/usr/local/tomcat7/webapps/lucene/petDisSpec");
		}
		bs.setHighlighterFormatter("<font color='red'>", "</font>");
		bs.createPetIndex(); // only need create index once
		String[] resource = {"petDisSpecName", "cropName","description"};
		String[] result= {"petDisSpecName", "petDisSpecId", "cropName","description","type"};
		try {
			HashMap[] h = bs.search("玉米", resource, result);
			System.out.println(h.length);
			for(int k = 0; k < h.length; k++){
				if(h[k] == null){
					break;
				}
				System.out.println(h[k].toString());
			}
		} catch (ParseException | IOException | InvalidTokenOffsetsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	  return "OK";
	}
	
	
	

}
