package com.dasinong.ploughHelper.facade;

public interface ITaskSpecFacade {

	public abstract Object getTaskSpec(Long taskSpecId);

	Object getSteps(Long taskSpecId, Long fieldId);

}