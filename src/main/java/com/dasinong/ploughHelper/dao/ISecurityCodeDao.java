package com.dasinong.ploughHelper.dao;

import com.dasinong.ploughHelper.model.SecurityCode;

public interface ISecurityCodeDao {

  public SecurityCode create(String code);
  
  public SecurityCode findById(Long codeId);
  
  public void delete(SecurityCode code);
}