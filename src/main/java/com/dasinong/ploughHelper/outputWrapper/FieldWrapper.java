package com.dasinong.ploughHelper.outputWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.Task;

public class FieldWrapper implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long fieldId;
	private String fieldName;
	private boolean isActive;
	private long varietyId;
	private long userId;
	private long locationId;
	private List<TaskWrapper> taskws = new ArrayList<TaskWrapper>();
	private List<PetDisWrapper> petdisws =  new ArrayList<PetDisWrapper>();
	private List<NatDisWrapper> natdisws = new ArrayList<NatDisWrapper>();
	
	private long currentStageID;
	private Date startDate;
	private Date endDate;
	private long yield;
	
	public FieldWrapper(Field field){
		this.setFieldId(field.getFieldId());
		this.fieldName = (field.getFieldName()==null)?"":field.getFieldName();
		this.setActive(field.getIsActive());
		this.setVarietyId(field.getVariety().getVarietyId());
		this.setUserId(field.getUser().getUserId());
		this.setLocationId(field.getLocation().getLocationId());
		if (field.getTasks()!=null){
			for (Task t : field.getTasks().values()){
				taskws.add(new TaskWrapper(t));
			}
		}
		if (field.getNatDiss()!=null){
			for (NatDis nd :  field.getNatDiss().values()){
				natdisws.add(new NatDisWrapper(nd));
			}
		}
		if (field.getPetDiss()!=null){
			for (PetDis pd : field.getPetDiss().values()){
				petdisws.add(new PetDisWrapper(pd));
			}
		}
		this.setCurrentStageID(field.getCurrentStageID());
		this.startDate = (field.getStartDate()==null)?null:field.getStartDate();
		this.endDate = (field.getEndDate()==null)?null:field.getEndDate();
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

	public List<TaskWrapper> getTaskws() {
		return taskws;
	}

	public void setTaskws(List<TaskWrapper> taskws) {
		this.taskws = taskws;
	}

	public List<PetDisWrapper> getPetdisws() {
		return petdisws;
	}

	public void setPetdisws(List<PetDisWrapper> petdisws) {
		this.petdisws = petdisws;
	}

	public List<NatDisWrapper> getNatdisws() {
		return natdisws;
	}

	public void setNatdisws(List<NatDisWrapper> natdisws) {
		this.natdisws = natdisws;
	}

	
}
