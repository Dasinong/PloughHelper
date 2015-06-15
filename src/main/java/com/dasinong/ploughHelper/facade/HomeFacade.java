package com.dasinong.ploughHelper.facade;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;

public class HomeFacade implements IHomeFacade {
	
   // @Autowired
    private IFieldDao fieldDao;
    
  //  @Autowired
    private ITaskSpecDao taskSpecDao;
    

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IHomeFacade#LoadHome(com.dasinong.ploughHelper.model.User, java.lang.String)
	 */
	@Override
	@Transactional
	public Object LoadHome(User user,String fieldId){
		fieldDao = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		taskSpecDao = (ITaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (user.getFields()==null){
			result.put("respCode",110);
			result.put("message","用户没有田地，请先添加");
			return result;
		}
		
		
		HashMap<String,Long> fieldList = new HashMap<String,Long>();
		for (Field f: user.getFields()){
			fieldList.put(f.getFieldName(),f.getFieldId());
		}
		result.put("fieldList",fieldList);
		
		if (fieldId==null || fieldId.equals("")){
			result.put("respCode", 200);
			result.put("Message", "读取田地成功");
			Field f = user.getFields().iterator().next();
			FieldWrapper fw = new FieldWrapper(f,taskSpecDao);
			result.put("currentField",fw);
			return result;
		}
		else{
			Long fid = Long.parseLong(fieldId);
			Field f= fieldDao.findById(fid);
			if (f==null){
				result.put("respCode",120);
				result.put("message","fieldId不存在");
				return result;
			}else{
				result.put("respCode", 200);
				result.put("message", "读取田地成功");
				FieldWrapper fw = new FieldWrapper(f,taskSpecDao);
				result.put("currentField",fw);
				return result;
			}
		}
	}
	
}
