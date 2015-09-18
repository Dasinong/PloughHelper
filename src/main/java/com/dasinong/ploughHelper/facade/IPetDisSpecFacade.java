package com.dasinong.ploughHelper.facade;

public interface IPetDisSpecFacade {

	Object getPetDisBySubStage(Long subStageId, Long varietyId);

	Object getPetDisDetail(Long petDisSpecId);

}