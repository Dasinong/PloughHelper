package com.dasinong.ploughHelper.outputWrapper;

import java.io.Serializable;
import java.util.Random;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IPetDisSpecDao;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.PetDisSpec;

public class PetDisWrapper implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long petDisId;
	private Long petDisSpecId;
	private String petDisSpecName;
	private Long fieldId;
	private boolean petDisStatus;
	
	private boolean alerttype;
	private String type;
	private String description;
	
	public PetDisWrapper(PetDis pd){
		this.petDisId = pd.getPetDisId();
		this.petDisSpecId = pd.getPetDisSpecId();
		this.fieldId = pd.getFieldId();
		this.petDisStatus = pd.getPetDisStatus();
		IPetDisSpecDao pdsd = (IPetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		PetDisSpec pds = pdsd.findById(petDisSpecId);
		this.petDisSpecName = pds.getPetDisSpecName();
		
		Random rnd = new Random();
		if (rnd.nextInt(5) >2){
			setAlerttype(true);
		}
		else setAlerttype(false);
		int i = rnd.nextInt(6);
		if (i >2 && i<4){
			type = "病害";
		}else if(i<=2){
			type = "虫害";
		}else{
			type ="草害";
		}
		description = "这是一个很严重的灾害";
	}

	public Long getPetDisId() {
		return petDisId;
	}

	public void setPetDisId(Long petDisId) {
		this.petDisId = petDisId;
	}

	public Long getPetDisSpecId() {
		return petDisSpecId;
	}

	public void setPetDisSpecId(Long petDisSpecId) {
		this.petDisSpecId = petDisSpecId;
	}

	public String getPetDisSpecName() {
		return petDisSpecName;
	}

	public void setPetDisSpecName(String petDisSpecName) {
		this.petDisSpecName = petDisSpecName;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public boolean isPetDisStatus() {
		return petDisStatus;
	}

	public void setPetDisStatus(boolean petDisStatus) {
		this.petDisStatus = petDisStatus;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAlerttype() {
		return alerttype;
	}

	public void setAlerttype(boolean alerttype) {
		this.alerttype = alerttype;
	}

	
}
