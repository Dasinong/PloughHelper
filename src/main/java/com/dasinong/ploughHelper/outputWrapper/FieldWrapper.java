package com.dasinong.ploughHelper.outputWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.PetDis;
import com.dasinong.ploughHelper.model.PetDisSpec;
import com.dasinong.ploughHelper.model.SubStage;
import com.dasinong.ploughHelper.model.Task;

public class FieldWrapper implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long fieldId;
	private String fieldName;
	private boolean isActive;
	private long varietyId;
	private long userId;
	private long locationId;
	private int monitorLocationId;
	private List<TaskWrapper> taskws = new ArrayList<TaskWrapper>();
	private List<PetDisWrapper> petdisws =  new ArrayList<PetDisWrapper>();
	private List<NatDisWrapper> natdisws = new ArrayList<NatDisWrapper>();
	private List<PetDisSpecWrapper> petdisspecws = new ArrayList<PetDisSpecWrapper>();
	
	private long currentStageID;
	private Date startDate;
	private Date endDate;
	private long yield;
	private boolean workable;
	private boolean sprayable;
	private int dayToHarvest;
	
	public FieldWrapper(Field field, ITaskSpecDao taskSpecDao){
		this.setFieldId(field.getFieldId());
		this.fieldName = (field.getFieldName()==null)?"":field.getFieldName();
		this.setActive(field.getIsActive());
		this.setVarietyId(field.getVariety().getVarietyId());
		this.setUserId(field.getUser().getUserId());
		this.setLocationId(field.getLocation().getLocationId());
		this.setMonitorLocationId(field.getMonitorLocationId());
		if (field.getTasks()!=null){
			for (Task t : field.getTasks().values()){
				TaskWrapper tw = new TaskWrapper(t,taskSpecDao);
				if (tw.getSubStageId() == field.getCurrentStageID()){
					taskws.add(new TaskWrapper(t,taskSpecDao));
				}
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
		this.setWorkable(field.isWorkable());
		this.setSprayable(field.isSprayable());
		this.setDayToHarvest(field.getDayToHarvest());
		if (field.getVariety().getSubStages()!=null){
			for(SubStage ss : field.getVariety().getSubStages()){
				if (ss.getSubStageId() == field.getCurrentStageID()){
					if(ss.getPetDisSpecs()!=null){
						for(PetDisSpec pds: ss.getPetDisSpecs()){
							PetDisSpecWrapper pdsw = new PetDisSpecWrapper(pds);
							petdisspecws.add(pdsw);
						}
					}
				}
			}
		}
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

	public int getMonitorLocationId() {
		return monitorLocationId;
	}

	public void setMonitorLocationId(int monitorLocationId) {
		this.monitorLocationId = monitorLocationId;
	}

	public boolean isWorkable() {
		return workable;
	}

	public void setWorkable(boolean workable) {
		this.workable = workable;
	}

	public boolean isSprayable() {
		return sprayable;
	}

	public void setSprayable(boolean sprayable) {
		this.sprayable = sprayable;
	}

	public int getDayToHarvest() {
		return dayToHarvest;
	}

	public void setDayToHarvest(int dayToHarvest) {
		this.dayToHarvest = dayToHarvest;
	}

	public List<PetDisSpecWrapper> getPetdisspecws() {
		return petdisspecws;
	}

	public void setPetdisspecws(List<PetDisSpecWrapper> petdisspecws) {
		this.petdisspecws = petdisspecws;
	}

	
}
