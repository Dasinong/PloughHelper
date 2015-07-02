package com.dasinong.ploughHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		try {
			String[] source = {"varietyName", "varietySource"};
			String[] target = {"varietyName", "varietyId", "varietySource"};
			HashMap<String,String>[] h = bs.search(key, source, target);
			List<HashMap<String,String>> format = new ArrayList<HashMap<String,String>>();
			Set<Integer> idcheck = new HashSet<Integer>();
			if (h!=null){
				for(int i=0;i<h.length;i++){
					if (h[i]!=null){
						if (!idcheck.contains(Integer.parseInt(h[i].get("varietyId")))){
							idcheck.add(Integer.parseInt(h[i].get("varietyId")));
							HashMap<String,String> record = new HashMap<String,String>();
							record.put("id", h[i].get("varietyId"));
							record.put("name", h[i].get("varietyName"));
							record.put("source", h[i].get("varietySource"));
							format.add(record);
						}
					}
				}
			}
			content.put("variety", format);
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
		try {
			String[] source = {"petDisSpecName", "cropName", "sympthon"};
			String[] target= {"petDisSpecName", "petDisSpecId", "cropName", "sympthon", "type"};

			HashMap<String,String>[] h = bs.search(key, source, target);
			List<HashMap<String,String>> ill = new ArrayList<HashMap<String,String>>();
			List<HashMap<String,String>> pest = new ArrayList<HashMap<String,String>>();
			List<HashMap<String,String>> grass = new ArrayList<HashMap<String,String>>();
			Set<Integer> idcheck = new HashSet<Integer>();
			if (h!=null){
				for (int i=0;i<h.length;i++){
					if (h[i]!=null){
						if (!idcheck.contains(Integer.parseInt(h[i].get("petDisSpecId")))){
							idcheck.add(Integer.parseInt(h[i].get("petDisSpecId")));
							HashMap<String,String> record = new HashMap<String,String>();
							record.put("id", h[i].get("petDisSpecId"));
							record.put("name",h[i].get("petDisSpecName"));
							record.put("source",h[i].get("cropName")+" "+h[i].get("sympthon"));
							if (h[i].get("type").equals("病害")) ill.add(record);
							if (h[i].get("type").equals("虫害")) pest.add(record);
							if (h[i].get("type").equals("草害")) grass.add(record);
						}
					}
				}
			}
			content.put("disease",ill);
			content.put("pest",pest);
			content.put("weeds",grass);
		} catch (ParseException | IOException | InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		
		
		
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
		     bs = new FullTextSearch("petDisSpec","E:/index/petCPProductIndex");
		}
		else{
			bs = new FullTextSearch("petDisSpec","/usr/local/tomcat7/webapps/lucene/cPProduct");
		}
		bs.setHighlighterFormatter("<font color='red'>", "</font>");

		try {
			String[] source = {"cPProductName","manufacturer","crop"};
			String[] target = {"cPProductName", "manufacturer","crop","cPProductId"};
			HashMap<String,String>[] h = bs.search(key, source, target);
			List<HashMap<String,String>> format = new ArrayList<HashMap<String,String>>();
			Set<Integer> idcheck = new HashSet<Integer>();
			if (h!=null){
				for(int i=0;i<h.length;i++){
					if (h[i]!=null){
						if (!idcheck.contains(Integer.parseInt(h[i].get("cPProductId")))){
							idcheck.add(Integer.parseInt(h[i].get("cPProductId")));
							HashMap<String,String> record = new HashMap<String,String>();
							record.put("id", h[i].get("cPProductId"));
							record.put("name", h[i].get("cPProductName"));
							record.put("source", h[i].get("crop")+" "+h[i].get("manufacturer"));
							format.add(record);
						}
					}
				}
			}
			content.put("cpproduct", format);
		} catch (ParseException | IOException | InvalidTokenOffsetsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		result.put("respCode", 200);
		result.put("message", "检索成功");
		result.put("data",content);
	  return result;
	}
	
	
	@RequestMapping(value = "/createVarietyIndex", produces="application/json")
	@ResponseBody
	public Object createVarietyIndex(HttpServletRequest request, HttpServletResponse response) {
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
	
	  return "OK";
	}
	
	
	@RequestMapping(value = "/createPetDisSpecIndex", produces="application/json")
	@ResponseBody
	public Object createPetDisSpecIndex(HttpServletRequest request, HttpServletResponse response) {
		FullTextSearch bs = null;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
		     bs = new FullTextSearch("petDisSpec","E:/index/petDisSpecIndex");
		}
		else{
			bs = new FullTextSearch("petDisSpec","/usr/local/tomcat7/webapps/lucene/petDisSpec");
		}
		bs.setHighlighterFormatter("<font color='red'>", "</font>");
		bs.createPetIndex(); // only need create index once
		String[] resource = {"petDisSpecName", "cropName","sympthon"};
		String[] result= {"petDisSpecName", "petDisSpecId", "cropName","sympthon","type"};
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
	
	
	@RequestMapping(value = "/createCPProductIndex", produces="application/json")
	@ResponseBody
	public Object createCPProductIndex(HttpServletRequest request, HttpServletResponse response) {
		FullTextSearch bs = null;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
		     bs = new FullTextSearch("petDisSpec","E:/index/petCPProductIndex");
		}
		else{
			bs = new FullTextSearch("petDisSpec","/usr/local/tomcat7/webapps/lucene/cPProduct");
		}
		bs.setHighlighterFormatter("<font color='red'>", "</font>");
		bs.createCPProductIndex(); // only need create index once
		String[] resource = {"cPProductName","manufacturer","crop"};
		String[] result = {"cPProductName", "manufacturer","crop","cPProductId"};
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
