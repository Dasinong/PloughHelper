package com.dasinong.ploughHelper.facade;

import java.util.Date;

import com.dasinong.ploughHelper.model.NatDis;
import com.dasinong.ploughHelper.model.User;

public interface IFieldFacade {

	public abstract Object createField(User user, String fieldName,
			Date startDate, boolean isActive, boolean seedingortransplant,
			double area, long locationId, long varietyId, String currentStageId, String yield);

	Object addWeatherAlert(NatDis natdis);

	public abstract Object changeField(Long fieldId, Long currentStageId);

}