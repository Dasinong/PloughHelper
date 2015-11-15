package com.dasinong.ploughHelper.facade;

import java.util.Date;
import java.util.List;

import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;
import com.dasinong.ploughHelper.outputWrapper.SubStageWrapper;

public interface IFieldFacade {

	public abstract FieldWrapper createField(User user, String fieldName, Date startDate, boolean isActive,
			boolean seedingortransplant, double area, long locationId, long varietyId, Long currentStageId, Long yield)
					throws Exception;

	Object addWeatherAlert(NatDis natdis);

	public abstract FieldWrapper changeField(Long fieldId, Long currentStageId);

	List<SubStageWrapper> getStages(long varietyId);

}