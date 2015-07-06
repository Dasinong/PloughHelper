package com.dasinong.ploughHelper.outputWrapper;

import com.dasinong.ploughHelper.model.PetSolu;

public class PetSoluWrapper {

	public Long petSoluId;
	public String petSoluDes = "";
	public String providedBy = "";
	public Long petDisSpecId;
	
	public boolean isRemedy = true; //true for cure, false for prevent
	public boolean isCPSolu = true;
	public int rank = 0;
	public String subStageId;
	
	public PetSoluWrapper(PetSolu ps){
		this.petSoluId = ps.getPetSoluId();
		this.petSoluDes = ps.getPetSoluDes();
		this.providedBy = ps.getProvidedBy();
		this.petDisSpecId = ps.getPetDisSpec().getPetDisSpecId();
		this.isRemedy = ps.getIsRemedy();
		this.isCPSolu = ps.getIsCPSolu();
		this.rank = ps.getRank();
		this.subStageId = ps.getSubStageId();
	}
}
