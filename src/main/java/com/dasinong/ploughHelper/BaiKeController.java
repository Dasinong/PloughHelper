package com.dasinong.ploughHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.facade.IBaiKeFacade;
import com.dasinong.ploughHelper.outputWrapper.CPProductWrapper;
import com.dasinong.ploughHelper.outputWrapper.PetDisSpecWrapper;
import com.dasinong.ploughHelper.outputWrapper.VarietyWrapper;
import com.dasinong.ploughHelper.util.FullTextSearch;
import com.dasinong.ploughHelper.util.Env;

@Controller
public class BaiKeController {
	
	IBaiKeFacade baiKeFacade;
	
	private static final Logger logger = LoggerFactory.getLogger(BaiKeController.class);
	
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
			result.put("message", "参数或参数格式错误");
		};
		String type = request.getParameter("type");
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
		if (type==null || key.equals("")){
			result.put("respCode", 200);
			result.put("message", "检索成功");
			content.put("variety",baiKeFacade.searchVariety(key));
			content.put("cpproduct",baiKeFacade.searchCPProduct(key));
			content.putAll(baiKeFacade.searchPetDisSpec(key));
			result.put("data", content);
			return result;
		}
		if (type.equalsIgnoreCase("variety")){
			
			result.put("respCode", 200);
			result.put("message", "获取成功");
			result.put("data", baiKeFacade.searchVariety(key));
			return result;
		}
		if (type.equalsIgnoreCase("cpproduct")){
			result.put("respCode", 200);
			result.put("message", "获取成功");
			result.put("data", baiKeFacade.searchCPProduct(key));
			return result;
		}
		
		if (type.equalsIgnoreCase("petdisspec")){
			result.put("respCode", 200);
			result.put("message", "获取成功");
			Map<String,List<HashMap<String,String>>> orig = baiKeFacade.searchPetDisSpec(key);
			List<HashMap<String,String>> target = new ArrayList<HashMap<String,String>>();
			for(Entry<String,List<HashMap<String,String>>> es : orig.entrySet() ){
				target.addAll(es.getValue());
			}
			result.put("data", target);
			return result;
		}
		result.put("respCode", 350);
		result.put("message", "type内容不支持");
		return result;
	}
	
	
	@RequestMapping(value = "/createVarietyIndex", produces="application/json")
	@ResponseBody
	public Object createVarietyIndex(HttpServletRequest request, HttpServletResponse response) {
		FullTextSearch bs = null;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
		     bs = new FullTextSearch("variety",Env.getEnv().DataDir+"/varietyIndex");
		}
		else{
			bs = new FullTextSearch("variety",Env.getEnv().DataDir+"/lucene/variety");
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
		     bs = new FullTextSearch("petDisSpec",Env.getEnv().DataDir+"/petDisSpecIndex");
		}
		else{
			bs = new FullTextSearch("petDisSpec",Env.getEnv().DataDir+"/lucene/petDisSpec");
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
		     bs = new FullTextSearch("petDisSpec",Env.getEnv().DataDir+"/petCPProductIndex");
		}
		else{
			bs = new FullTextSearch("petDisSpec",Env.getEnv().DataDir+"/lucene/cPProduct");
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
	
	
	@RequestMapping(value = "/getVarietyBaiKeById", produces="application/json")
	@ResponseBody
	public Object getVarietyBaiKeById(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		Long id;
		try{
			id = Long.parseLong(request.getParameter("id"));
		}
		catch(Exception e){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
		VarietyWrapper vw = baiKeFacade.getVarietyById(id);
		if (vw==null){
			result.put("respCode",450);
			result.put("message", "品种未找到");
			return result;
		}
		else{
			result.put("respCode", 200);
			result.put("message","检索成功");
			result.put("data",vw);
			return result;
		}
	}
	
	@RequestMapping(value = "/getPetDisSpecBaiKeById", produces="application/json")
	@ResponseBody
	public Object getPetDisSpecById(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		Long id;
		try{
			id = Long.parseLong(request.getParameter("id"));
		}
		catch(Exception e){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
		PetDisSpecWrapper pdsw = baiKeFacade.getPetDisSpecById(id);
		if (pdsw==null){
			result.put("respCode",451);
			result.put("message", "病虫草害未找到");
			return result;
		}
		else{
			result.put("respCode", 200);
			result.put("message","检索成功");
			result.put("data",pdsw);
			return result;
		}
	}
	
	@RequestMapping(value = "/getCPProductById", produces="application/json")
	@ResponseBody
	public Object getCPProductById(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		Long id;
		try{
			id = Long.parseLong(request.getParameter("id"));
		}
		catch(Exception e){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
		CPProductWrapper cpw = baiKeFacade.getCPProductById(id);
		
    	if (cpw==null){
    		result.put("respCode", 452);
    		result.put("message","农药未找到");
    		return result;
    	}else{
    		result.put("respCode", 200);
    		result.put("message", "获得成功");
    		result.put("data", cpw);
    		return result;
    	}
	}
	
	
	@RequestMapping(value = "/browseCropByType", produces="application/json")
	@ResponseBody
	public Object browseCropByType(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		String type = request.getParameter("type");
		if (type==null || type.equals("")){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
	    return baiKeFacade.getCropByType(type);
	}
	
	
	@RequestMapping(value = "/browseVarietyByCropId", produces="application/json")
	@ResponseBody
	public Object browseVarietyByCropId(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		Long cropId;
		try{
			cropId = Long.parseLong(request.getParameter("cropId"));
		}
		catch(Exception e){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
	    return baiKeFacade.browseVarietyByCropId(cropId);
	}
	
	@RequestMapping(value = "/browsePetDisByType", produces="application/json")
	@ResponseBody
	public Object browsePetDisByType(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		String type = request.getParameter("type");
		if (type==null || type.equals("")){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
	    return baiKeFacade.browsePetDisByType(type);
	}
	
	
	@RequestMapping(value = "/browseCPProductByModel", produces="application/json")
	@ResponseBody
	public Object browseCPProductByModel(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		String model = request.getParameter("model");
		if (model==null || model.equals("")){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
	    return baiKeFacade.browseCPProductByModel(model);
	}
	
	@RequestMapping(value = "/getCPProdcutsByIngredient", produces="application/json")
	@ResponseBody
	public Object getCPProdcutsByIngredient(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		String ingredient = request.getParameter("ingredient");
		if (ingredient==null || ingredient.equals("")){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
	    return baiKeFacade.getCPProdcutsByIngredient(ingredient);
	}
	
	@RequestMapping(value = "/getVarietysByName", produces="application/json")
	@ResponseBody
	public Object getVarietysByName(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		String name = request.getParameter("name");
		if (name==null || name.equals("")){
			result.put("respCode",300);
			result.put("message","参数或参数格式错误");
			return result;
		}
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
	    return baiKeFacade.getVarietysByName(name);
	}

	@RequestMapping(value = "/browsePetDisSpecsByCropIdAndType", produces="application/json")
	@ResponseBody
	public Object browsePetDisSpecsByCropIdAndType(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		Long cropId;
		String cropIdStr = request.getParameter("cropId");
		if (cropIdStr == null || "".equals(cropIdStr)) {
			result.put("respCode", 300);
			result.put("message", "cropId缺失");
			return result;
		} else {
			cropId = Long.parseLong(cropIdStr);
		}
		
		// TODO (xiahonggao): make type Enum and validate the value here
		String type = request.getParameter("type");
		if (type == null || "".equals(type)) {
			result.put("respCode", 300);
			result.put("message", "type缺失");
			return result;
		}
		
		baiKeFacade = (IBaiKeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("baiKeFacade");
	    return baiKeFacade.browsePetDisSpecsByCropIdAndType(cropId, type);
	}
}
