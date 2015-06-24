package com.dasinong.ploughHelper.facade;

import java.util.Date;

import com.dasinong.ploughHelper.inputParser.FieldParser;
import com.dasinong.ploughHelper.model.User;

public interface IFieldFacade {

	public abstract Object createField(User user, String fieldName,
			Date startDate, boolean isActive, boolean seedingortransplant,
			double area, long locationId, long varietyId, long currentStageId);

}