package com.dasinong.ploughHelper.outputWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.User;

public class UserWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String userName="";
	private String password="";
	private String cellPhone="";
	private String address="";
	private List<Long> fields = new ArrayList<Long>();
	public UserWrapper(User user){
		this.userId= user.getUserId();
		this.userName=user.getUserName();
		this.password=user.getPassword();
		this.address=user.getAddress();
		this.cellPhone = user.getCellPhone();
		if (user.getFields()!=null){
			for (Field f :  user.getFields()){
				getFields().add(f.getFieldId());
			}
		}
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Long> getFields() {
		return fields;
	}
	public void setFields(List<Long> fields) {
		this.fields = fields;
	}

}
