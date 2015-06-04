package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long userId;
	private String userName;
	private String password;
	private String cellPhone;
	private String address;
	private Set<Field> fields= new HashSet<Field>();

	public User(){}
	
	public User(String userName, String password, String cellPhone,
			String address) {
		super();
		this.userName = userName;
		this.password = password;
		this.cellPhone = cellPhone;
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Field> getFields() {
		return fields;
	}
	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}

}
