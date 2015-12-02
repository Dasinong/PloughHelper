package com.dasinong.ploughHelper.facade;

import com.dasinong.ploughHelper.model.User;

public interface IInstitutionEmployeeApplicationFacade {

	public Object create(User user, String contactName, String cellphone, String code, String title, String region) throws Exception;
}
