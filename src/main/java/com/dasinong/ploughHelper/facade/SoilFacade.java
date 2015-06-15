package com.dasinong.ploughHelper.facade;

import java.util.Date;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ISoilTestReportDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.SoilTestReport;

public class SoilFacade implements ISoilFacade {

	// @Autowired
    private IFieldDao fieldDao;
    
    //  @Autowired
    private ISoilTestReportDao soilTestReportDao;
    
    
    /* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.ISoilFacade#loadSoilHome(com.dasinong.ploughHelper.model.Field)
	 */
    @Override
	public Object loadSoilHome(Field f){

    	HashMap<String,Object> result  = new HashMap<String,Object>();
    	
    	return result;
    	
    }
   
    /*
     * 		Long uId = Long.parseLong(userId);
			Long fId = Long.parseLong(fieldId);
			type = (type==null) ? "":type;
			color = (color==null) ? "":color;
			fertility = (fertility==null) ? "":fertility;
			double humidityv = Double.parseDouble(humidity);
			Date date = new Date(testDate);
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
     */
    /* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.ISoilFacade#insertSoilHome(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, double, java.util.Date, double, java.lang.String, double, double, double, double, double, double, double, double, double, double, double, double, double, double, double)
	 */
    @Override
	@Transactional
    public Object insertSoilHome(Long uId,Long fId,String type, String color, String fertility, double humidityv,
    		Date date,double phValuev, String organic, double anv, double qnv, double pv, double qKv,double sKv,
    		double fev,double mnv,double cuv,double znv,double bv, double mov, double cav,double sv,double siv,
    		double mgv){
    	
    	HashMap<String,Object> result  = new HashMap<String,Object>();
		fieldDao = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		soilTestReportDao = (ISoilTestReportDao) ContextLoader.getCurrentWebApplicationContext().getBean("soilTestReportDao");
		Field field = fieldDao.findById(fId);
		
    	SoilTestReport str =  new SoilTestReport(uId,field,type,color,fertility,humidityv,date,phValuev,organic,anv,qnv,pv,
    			qKv,sKv,fev,mnv,cuv,znv,bv,mov,cav,sv,siv,mgv);
    	soilTestReportDao.save(str);
    	field.getSoilTestReports().add(str);
    	result.put("respCode", 200);
    	result.put("message", "插入成功");
    	return result;
    }
   
}
