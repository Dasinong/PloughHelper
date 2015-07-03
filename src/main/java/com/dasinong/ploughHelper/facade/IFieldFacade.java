package com.dasinong.ploughHelper.facade;

import java.util.Date;

import com.dasinong.ploughHelper.model.User;

public interface IFieldFacade {

	public abstract Object createField(User user, String fieldName,
			Date startDate, boolean isActive, boolean seedingortransplant,
			double area, long locationId, long varietyId, String currentStageId, String yield);

}