package com.dasinong.ploughHelper.outputWrapper;

import java.io.Serializable;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.PetDisSpecDao;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.PetDisSpec;

public class PetDisWrapper implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long petDisId;
	private Long petDisSpecId;
	private String petDisSpecName;
	private Long fieldId;
	private boolean petDisStatus;
	
	public PetDisWrapper(PetDis pd){
		this.petDisId = pd.getPetDisId();
		this.petDisSpecId = pd.getPetDisSpecId();
		this.fieldId = pd.getFieldId();
		this.petDisStatus = pd.getPetDisStatus();
		PetDisSpecDao pdsd = (PetDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("petDisSpecDao");
		PetDisSpec pds = pdsd.findById(petDisSpecId);
		this.petDisSpecName = pds.getPetDisSpecName();
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

	
}
