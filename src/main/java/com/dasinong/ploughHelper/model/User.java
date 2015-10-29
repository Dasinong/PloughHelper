package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dasinong.ploughHelper.util.SHA256;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// salt used to encrypt password
	// DO NOT CHANGE SALT WITHOUT CHANGING DATABASE!!!
	public static final String passwordSalt = "woShiZhaoRiTian";

	private Long userId;
	private String userName;
	private String cellPhone;
	
	// TODO (xiahonggao): remove password field right after
	// encryption change is live.
	private String password;
	private String encryptedPassword;
	private String address;
	private boolean isPassSet;
	private Set<Field> fields= new HashSet<Field>();
	
	private boolean authenticated = false;
	private String pictureId="default.jpg";
	private String telephone;
	private String qqtoken;
	private String weixintoken;
	private String deviceId;
	private String channel;
	
	private Date createAt;
	private Date updateAt;
	private Date lastLogin;
	
	private String refcode;
	private Long refuid;

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
	
	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}
	
	// TODO (xiahonggao): DO NOT USE THIS METHOD SINCE IT WILL BE DELETED VERY SOON.
	// Use setAndEncryptPassword instead.
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAndEncryptPassword(String rawPassword) throws NoSuchAlgorithmException {
		String encryptedPassword = SHA256.encrypt(rawPassword, this.passwordSalt);
		this.setEncryptedPassword(encryptedPassword);
	}
	
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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

	public boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean getIsPassSet() {
		return isPassSet;
	}

	public void setIsPassSet(boolean isPassSet) {
		this.isPassSet = isPassSet;
	}

	public String getQqtoken() {
		return qqtoken;
	}

	public void setQqtoken(String qqtoken) {
		this.qqtoken = qqtoken;
	}

	public String getWeixintoken() {
		return weixintoken;
	}

	public void setWeixintoken(String weixintoken) {
		this.weixintoken = weixintoken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public String getRefcode() {
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}

	public Long getRefuid() {
		return refuid;
	}

	public void setRefuid(Long refuid) {
		this.refuid = refuid;
	}
	
	public boolean validatePassword(String password) throws NoSuchAlgorithmException {
		String encryptedPassword = SHA256.encrypt(password, User.passwordSalt);
		return encryptedPassword.equals(this.getEncryptedPassword());
	}
}
