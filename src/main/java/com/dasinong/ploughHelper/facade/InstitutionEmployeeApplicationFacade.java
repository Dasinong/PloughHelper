package com.dasinong.ploughHelper.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IInstitutionDao;
import com.dasinong.ploughHelper.dao.IInstitutionEmployeeApplicationDao;
import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.exceptions.UserTypeAlreadyDefinedException;
import com.dasinong.ploughHelper.model.Institution;
import com.dasinong.ploughHelper.model.InstitutionEmployeeApplication;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.UserType;

@Transactional
public class InstitutionEmployeeApplicationFacade implements IInstitutionEmployeeApplicationFacade {

	@Override
	public Object create(User user, String cellphone, String code, String title, String region) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		IInstitutionEmployeeApplicationDao appDao = (IInstitutionEmployeeApplicationDao)
				ContextLoader.getCurrentWebApplicationContext().getBean("institutionEmployeeApplicationDao");
		IInstitutionDao instDao = (IInstitutionDao)
				ContextLoader.getCurrentWebApplicationContext().getBean("institutionDao");
		IUserDao userDao = (IUserDao)
				ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		
		if (user.getUserType() != null) {
			throw new UserTypeAlreadyDefinedException(user.getUserId(), user.getUserType());
		}
		
		Institution inst = instDao.findByCode(code);
		if (inst == null) {
			throw new InvalidParameterException("code", "String");
		}
		
		InstitutionEmployeeApplication app = new InstitutionEmployeeApplication();
		app.setCellphone(cellphone);
		app.setInstitutionId(inst.getId());
		app.setTitle(title);
		app.setRegion(region);
		appDao.save(app);
		
		System.out.println(user.getUserId());
		user.setInstitutionId(inst.getId());
		user.setUserType(UserType.SALES);
		userDao.update(user);
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("application", app);
		result.put("respCode", 200);
		result.put("message", "保存成功");
		result.put("data", data);
		return result;
	}

}
