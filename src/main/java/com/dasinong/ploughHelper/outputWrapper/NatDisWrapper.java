package com.dasinong.ploughHelper.outputWrapper;

import java.io.Serializable;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.NatDisSpecDao;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.NatDisSpec;

public class NatDisWrapper implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long natDisId;
	private Long natDisSpecId;
	private String natDisSpecName;
	private Long fieldId;
	private boolean natDisStatus;

	
	public NatDisWrapper(NatDis nd){
		this.setNatDisId(nd.getNatDisId());
		this.natDisSpecId = nd.getNatDisSpecId();
		this.fieldId = nd.getFieldId();
		this.natDisStatus = nd.getNatDisStatus();
		NatDisSpecDao ndsd = (NatDisSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("natDisSpecDao");
		NatDisSpec nds = ndsd.findById(this.natDisSpecId);
		this.natDisSpecName = nds.getNatDisSpecName();
	}


	public Long getNatDisId() {
		return natDisId;
	}


	public void setNatDisId(Long natDisId) {
		this.natDisId = natDisId;
	}


	public Long getNatDisSpecId() {
		return natDisSpecId;
	}


	public void setNatDisSpecId(Long natDisSpecId) {
		this.natDisSpecId = natDisSpecId;
	}


	public String getNatDisSpecName() {
		return natDisSpecName;
	}


	public void setNatDisSpecName(String natDisSpecName) {
		this.natDisSpecName = natDisSpecName;
	}


	public Long getFieldId() {
		return fieldId;
	}


	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}


	public boolean isNatDisStatus() {
		return natDisStatus;
	}


	public void setNatDisStatus(boolean natDisStatus) {
		this.natDisStatus = natDisStatus;
	}

	
}
