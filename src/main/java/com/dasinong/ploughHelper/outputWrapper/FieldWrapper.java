package com.dasinong.ploughHelper.outputWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.Variety;

public class FieldWrapper implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long fieldId;
	private String fieldName="";
	private boolean isActive;
	private long varietyId;
	private long userId;
	private long locationId;
	private List<Long> taskIds = new ArrayList<Long>();
	private List<Long> petDisIds =  new ArrayList<Long>();
	private List<Long> natDisIds = new ArrayList<Long>();
	
	private long currentStageID;
	private Date startDate;
	private Date endDate;
	private long yield;
	
	public FieldWrapper(Field field){
		this.setFieldId(field.getFieldId());
		this.setFieldName(field.getFieldName());
		this.setActive(field.getIsActive());
		this.setVarietyId(field.getVariety().getVarietyId());
		this.setUserId(field.getUser().getUserId());
		this.setLocationId(field.getLocation().getLocationId());
		if (field.getTasks()!=null){
			for (Long tid : field.getTasks().keySet()){
				getTaskIds().add(tid);
			}
		}
		if (field.getNatDiss()!=null){
			for (Long pdid :  field.getNatDiss().keySet()){
				getPetDisIds().add(pdid);
			}
		}
		if (field.getPetDiss()!=null){
			for (Long ndid : field.getPetDiss().keySet()){
				getNatDisIds().add(ndid);
			}
		}
		this.setCurrentStageID(field.getCurrentStageID());
		this.setStartDate(field.getStartDate());
		this.setEndDate(field.getEndDate());
		this.setYield(field.getYield());
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getVarietyId() {
		return varietyId;
	}

	public void setVarietyId(long varietyId) {
		this.varietyId = varietyId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public long getCurrentStageID() {
		return currentStageID;
	}

	public void setCurrentStageID(long currentStageID) {
		this.currentStageID = currentStageID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getYield() {
		return yield;
	}

	public void setYield(long yield) {
		this.yield = yield;
	}

	public List<Long> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(List<Long> taskIds) {
		this.taskIds = taskIds;
	}

	public List<Long> getPetDisIds() {
		return petDisIds;
	}

	public void setPetDisIds(List<Long> petDisIds) {
		this.petDisIds = petDisIds;
	}

	public List<Long> getNatDisIds() {
		return natDisIds;
	}

	public void setNatDisIds(List<Long> natDisIds) {
		this.natDisIds = natDisIds;
	}
}
