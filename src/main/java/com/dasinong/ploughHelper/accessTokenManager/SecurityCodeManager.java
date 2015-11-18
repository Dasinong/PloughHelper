package com.dasinong.ploughHelper.accessTokenManager;

import java.sql.Timestamp;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IDasinongAppDao;
import com.dasinong.ploughHelper.dao.ISecurityCodeDao;
import com.dasinong.ploughHelper.model.SecurityCode;

public class SecurityCodeManager {

	private ISecurityCodeDao codeDao;

	public SecurityCodeManager(ISecurityCodeDao codeDao) {
		this.codeDao = codeDao;
	}

	public SecurityCodeManager() {
		this.codeDao = (ISecurityCodeDao) ContextLoader.getCurrentWebApplicationContext().getBean("securityCodeDao");
	}

	public SecurityCode generate(String code) throws Exception {
		Timestamp createdAt = new Timestamp(System.currentTimeMillis());
		Timestamp expiredAt = new Timestamp(createdAt.getTime() + 1000 * 24 * 3600);

		SecurityCode securityCode = new SecurityCode();
		securityCode.setCode(code);
		securityCode.setCreatedAt(createdAt);
		securityCode.setExpiredAt(expiredAt);

		this.codeDao.save(securityCode);

		return securityCode;
	}
}
