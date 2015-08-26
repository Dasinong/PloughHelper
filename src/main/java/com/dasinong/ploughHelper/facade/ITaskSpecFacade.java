package com.dasinong.ploughHelper.facade;

import java.util.List;

import com.dasinong.ploughHelper.outputWrapper.StepWrapper;

public interface ITaskSpecFacade {

	public abstract Object getTaskSpec(Long taskSpecId);

	List<StepWrapper> getSteps(Long taskSpecId, Long fieldId);

}