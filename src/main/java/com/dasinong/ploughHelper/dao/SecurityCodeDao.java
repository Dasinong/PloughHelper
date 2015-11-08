package com.dasinong.ploughHelper.dao;

import java.sql.Timestamp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.SecurityCode;

public class SecurityCodeDao extends HibernateDaoSupport implements ISecurityCodeDao {

  @Override
  public SecurityCode create(String code) {
    Timestamp createdAt = new Timestamp(System.currentTimeMillis()); 
    Timestamp expiredAt = new Timestamp(createdAt.getTime() + 1000 * 24 * 3600);
    
    SecurityCode securityCode = new SecurityCode();
    securityCode.setCode(code);
    securityCode.setCreatedAt(createdAt);
    securityCode.setCreatedAt(createdAt);
    securityCode.setExpiredAt(expiredAt);
    
    this.getHibernateTemplate().save(securityCode);
    return securityCode;
  }

  @Override
  public SecurityCode findById(Long codeId) {
    return this.getHibernateTemplate().get(SecurityCode.class, codeId);
  }

  @Override
  public void delete(SecurityCode code) {
    this.getHibernateTemplate().delete(code);
  }

}