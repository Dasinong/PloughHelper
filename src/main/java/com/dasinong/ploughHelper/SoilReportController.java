package com.dasinong.ploughHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.exceptions.MissingParameterException;
import com.dasinong.ploughHelper.facade.ISoilFacade;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

@Controller
public class SoilReportController extends RequireUserLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(SoilReportController.class);
	
	@RequestMapping(value = "/insertSoilReport", produces="application/json")
	@ResponseBody
	public Object insertSoilReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();

		HttpServletRequestX requestX = new HttpServletRequestX(request);
		
		Long uId = requestX.getLong("userId");
		Long fId =  requestX.getLong("fieldId");
		String type = requestX.getStringOptional("type", "");
		String color = requestX.getStringOptional("color", "");
		String fertility = requestX.getStringOptional("fertility", "");
		double humidityv = requestX.getDouble("humidity");
		Date date = requestX.getDate("testDate");
		double phValuev = requestX.getDouble("phValue");
		String organic = requestX.getStringOptional("organic","");
	
			
		double anv = requestX.getDouble("an");
		double qnv = requestX.getDouble("qn");	
		double pv = requestX.getDouble("p");
		double qKv = requestX.getDouble("qK");
		double sKv = requestX.getDouble("sK");
		double fev = requestX.getDouble("fe");
		double mnv = requestX.getDouble("mn");
		double cuv = requestX.getDouble("cu");
		double znv = requestX.getDouble("zn");
		double bv = requestX.getDouble("b");
		double mov = requestX.getDouble("mo");
		double cav = requestX.getDouble("ca");
		double sv = requestX.getDouble("s");
		double siv = requestX.getDouble("si");
		double mgv = requestX.getDouble("mg");
				
		ISoilFacade sf = (ISoilFacade) ContextLoader.getCurrentWebApplicationContext().getBean("soilFacade");
		result.put("respCode", 200);
		result.put("message", "插入成功");
		result.put("data", sf.insertSoil(uId, fId, type, color, fertility, humidityv, date, phValuev, organic, anv, qnv, pv, qKv, sKv, fev, mnv, cuv, znv, bv, mov, cav, sv, siv, mgv));
		return result;
	}
	
	
	@RequestMapping(value = "/getSoilReport", produces="application/json")
	@ResponseBody
	public Object getSoilReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = this.getLoginUser(request);
		Map<String,Object> result = new HashMap<String,Object>();
		
		ISoilFacade sf = (ISoilFacade) ContextLoader.getCurrentWebApplicationContext().getBean("soilFacade");
		
		String reportId = request.getParameter("reportId");
		if (reportId!=null && !reportId.equals("")){
			Long rId;
			try{
				rId = Long.parseLong(reportId);
			}
			catch(Exception e){
				throw new InvalidParameterException("reportId","Long");
			}
	    	result.put("respCode", 200);
	    	result.put("message", "读取成功");
	    	result.put("data", sf.loadSoilReportsByRid(rId));
			return result;
		}
      
		String fieldId = request.getParameter("fieldId");
		
		if (fieldId==null){
			return sf.loadSoilReportsByUid(user.getUserId());
		}
		else{
			Long fid;
			try{
				fid = Long.parseLong(fieldId);
			}
			catch(Exception e){
				throw new InvalidParameterException("fieldId","Long");
			}
	    	result.put("respCode", 200);
	    	result.put("message", "读取成功");
	    	result.put("data", sf.loadSoilReportsByFid(fid));
			return result;
		}
	}
	
	
	@RequestMapping(value = "/updateSoilReport", produces="application/json")
	@ResponseBody
	public Object updateSoilReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
      
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		
		Long reportId = requestX.getLong("userId");
		String type = requestX.getStringOptional("type", "");
		String color = requestX.getStringOptional("color", "");
		String fertility = requestX.getStringOptional("fertility", "");
		double humidityv = requestX.getDouble("humidity");
		double phValuev = requestX.getDouble("phValue");
		String organic = requestX.getStringOptional("organic","");
	
		double anv = requestX.getDouble("an");
		double qnv = requestX.getDouble("qn");	
		double pv = requestX.getDouble("p");
		double qKv = requestX.getDouble("qK");
		double sKv = requestX.getDouble("sK");
		double fev = requestX.getDouble("fe");
		double mnv = requestX.getDouble("mn");
		double cuv = requestX.getDouble("cu");
		double znv = requestX.getDouble("zn");
		double bv = requestX.getDouble("b");
		double mov = requestX.getDouble("mo");
		double cav = requestX.getDouble("ca");
		double sv = requestX.getDouble("s");
		double siv = requestX.getDouble("si");
		double mgv = requestX.getDouble("mg");
	
		ISoilFacade sf = (ISoilFacade) ContextLoader.getCurrentWebApplicationContext().getBean("soilFacade");
		
		result.put("respCode", 200);
		result.put("message", "更新报告成功");	    
		result.put("data", sf.updateSoil(reportId, type, color, fertility, humidityv, phValuev, organic, anv, qnv, pv, qKv, sKv, fev, mnv, cuv,
					znv, bv, mov, cav, sv, siv, mgv));
		return result;
	}
}
