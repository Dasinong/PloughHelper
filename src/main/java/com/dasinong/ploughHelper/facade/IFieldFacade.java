package com.dasinong.ploughHelper.facade;

import java.util.Date;

import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;

public interface IFieldFacade {

	public abstract FieldWrapper createField(User user, String fieldName,
			Date startDate, boolean isActive, boolean seedingortransplant,
			double area, long locationId, long varietyId, String currentStageId, String yield) throws Exception;

	Object addWeatherAlert(NatDis natdis);

	public abstract Object changeField(Long fieldId, Long currentStageId);

}