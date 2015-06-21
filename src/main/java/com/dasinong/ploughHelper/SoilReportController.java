package com.dasinong.ploughHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.facade.ISoilFacade;
import com.dasinong.ploughHelper.model.User;

@Controller
public class SoilReportController {
	
	@RequestMapping(value = "/loadSoilS", produces="application/json")
	@ResponseBody
	public Object loadSoilS(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		ISoilFacade sf = (ISoilFacade) ContextLoader.getCurrentWebApplicationContext().getBean("soilFacade");
		return result;
	}
	
	@RequestMapping(value = "/insertSoilReport", produces="application/json")
	@ResponseBody
	public Object insertSoilReport(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		try{
			String userId = request.getParameter("userId");
			String fieldId = request.getParameter("fieldId");
			String type = request.getParameter("type");
			String color = request.getParameter("color");
			String fertility = request.getParameter("fertility");
			String humidity = request.getParameter("humidity");
			String testDate = request.getParameter("testDate");
			String phValue = request.getParameter("phValue");
			String organic = request.getParameter("organic");
			
			String an = request.getParameter("an");
			String qn = request.getParameter("qn");
			String p = request.getParameter("p");
			String qK = request.getParameter("qK");
			String sK = request.getParameter("sK");
			String fe = request.getParameter("fe");
			String mn = request.getParameter("mn");
			String cu = request.getParameter("cu");
			String zn = request.getParameter("zn");
			String b = request.getParameter("b");
			String mo = request.getParameter("mo");
			String ca = request.getParameter("ca");
			String s = request.getParameter("s");
			String si = request.getParameter("si");
			String mg = request.getParameter("mg");
			
			if (userId==null || fieldId==null){
				result.put("respCode",300);
				result.put("message", "userID 和 fieldId必填");
				return result;
			}
            
			Long uId = Long.parseLong(userId);
			Long fId = Long.parseLong(fieldId);
			type = (type==null) ? "":type;
			color = (color==null) ? "":color;
			fertility = (fertility==null) ? "":fertility;
			double humidityv = Double.parseDouble(humidity);
			Date date = new Date();
			
			try{
				date = new Date(Long.parseLong(testDate));
			}
			catch(Exception e){
				date = new Date(testDate);
			}
			double phValuev = Double.parseDouble(phValue);
			organic = (organic==null) ? "":organic;
			double anv =  Double.parseDouble(an);
        	double qnv = Double.parseDouble(qn);
			double pv = Double.parseDouble(p);
			double qKv = Double.parseDouble(qK);
			double sKv = Double.parseDouble(sK);
			double fev = Double.parseDouble(fe);
			double mnv = Double.parseDouble(mn);
			double cuv = Double.parseDouble(cu);
			double znv = Double.parseDouble(zn);
			double bv = Double.parseDouble(b);
			double mov = Double.parseDouble(mo);
			double cav = Double.parseDouble(ca);
			double sv = Double.parseDouble(s);
			double siv = Double.parseDouble(si);
			double mgv = Double.parseDouble(mg);
			
			ISoilFacade sf = (ISoilFacade) ContextLoader.getCurrentWebApplicationContext().getBean("soilFacade");
			return sf.insertSoil(uId, fId, type, color, fertility, humidityv, date, phValuev, organic, anv, qnv, pv, qKv, sKv, fev, mnv, cuv, znv, bv, mov, cav, sv, siv, mgv);
		}catch (Exception e) {
			result.put("respCode", 500);
			result.put("message", e.getCause());
			return result;
		}
	}
	
	
	@RequestMapping(value = "/getSoilReport", produces="application/json")
	@ResponseBody
	public Object getSoilReport(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
      
		String fieldId = request.getParameter("fieldId");
		try{
			ISoilFacade sf = (ISoilFacade) ContextLoader.getCurrentWebApplicationContext().getBean("soilFacade");
			if (fieldId==null){
				return sf.loadSoilReportsByUid(user.getUserId());
			}
			else{
				Long fid = Long.parseLong(fieldId);
				return sf.loadSoilReportsByFid(fid);
			}
		}catch (Exception e) {
			result.put("respCode", 500);
			result.put("message", e.getCause());
			return result;
		}

	}
	
	
	@RequestMapping(value = "/updateSoilReport", produces="application/json")
	@ResponseBody
	public Object updateSoilReport(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
      
		Long reportId;
		String type;
		String color;
		String fertility;
		double humidityv;
		double phValuev;
		String organic;
		double anv;
		double qnv;
		double pv;
		double qKv;
		double sKv;
		double fev;
		double mnv;
		double cuv;
		double znv;
		double bv;
		double mov;
		double cav;
		double sv;
		double siv;
		double mgv;

		try{
			reportId = Long.parseLong(request.getParameter("reportId"));
			type = request.getParameter("type");
			color = request.getParameter("color");
			fertility = request.getParameter("fertility");
			humidityv = Double.parseDouble(request.getParameter("humidity"));
			phValuev =  Double.parseDouble(request.getParameter("phValue"));
			organic = request.getParameter("organic");
			anv = Double.parseDouble(request.getParameter("an"));
			qnv = Double.parseDouble(request.getParameter("qn"));
			pv = Double.parseDouble(request.getParameter("p"));
			qKv = Double.parseDouble(request.getParameter("qK"));
			sKv = Double.parseDouble(request.getParameter("sK"));
			fev = Double.parseDouble(request.getParameter("fe"));
			mnv = Double.parseDouble(request.getParameter("mn"));
			cuv = Double.parseDouble(request.getParameter("cu"));
			znv = Double.parseDouble(request.getParameter("zn"));
			bv = Double.parseDouble(request.getParameter("b"));
			mov = Double.parseDouble(request.getParameter("mo"));
			cav = Double.parseDouble(request.getParameter("ca"));
			sv = Double.parseDouble(request.getParameter("s"));
			siv = Double.parseDouble(request.getParameter("si"));
			mgv = Double.parseDouble(request.getParameter("mg"));
		}catch(Exception e){
			result.put("respCode",300);
			result.put("message", "参数错误");
			return result;
		}
		
		
		try{
			ISoilFacade sf = (ISoilFacade) ContextLoader.getCurrentWebApplicationContext().getBean("soilFacade");
			return sf.updateSoil(reportId, type, color, fertility, humidityv, phValuev, organic, anv, qnv, pv, qKv, sKv, fev, mnv, cuv,
					znv, bv, mov, cav, sv, siv, mgv);
		}catch (Exception e) {
			result.put("respCode", 500);
			result.put("message", e.getCause());
			return result;
		}

	}
}
