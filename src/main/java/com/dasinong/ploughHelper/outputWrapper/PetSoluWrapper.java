package com.dasinong.ploughHelper.outputWrapper;

import java.util.HashSet;
import java.util.Set;

import com.dasinong.ploughHelper.model.CPProduct;
import com.dasinong.ploughHelper.model.CPProductPriority;
import com.dasinong.ploughHelper.model.PetSolu;

public class PetSoluWrapper {

	public Long petSoluId;
	public String petSoluDes = "";
	public String providedBy = "";
	public Long petDisSpecId;

	public boolean isRemedy = true; // true for cure, false for prevent
	public boolean isCPSolu = true;
	public int rank = 0;
	public String subStageId;
	public String snapshotCP;
	private Set<String> cps = new HashSet<String>();

	public PetSoluWrapper(PetSolu ps) {
		this.petSoluId = ps.getPetSoluId();
		this.petSoluDes = ps.getPetSoluDes();
		this.providedBy = ps.getProvidedBy();
		this.petDisSpecId = ps.getPetDisSpec().getPetDisSpecId();
		this.isRemedy = ps.getIsRemedy();
		this.isCPSolu = ps.getIsCPSolu();
		this.rank = ps.getRank();
		this.subStageId = ps.getSubStageId();
		StringBuilder ssCP = new StringBuilder();
		for (CPProduct cp : ps.getcPProducts()) {
			if (cp.getPriority() != CPProductPriority.HIGH) {
				continue;
			}

			if (!cps.contains(cp.getActiveIngredient())) {
				ssCP.append(cp.getActiveIngredient() + " ");
				cps.add(cp.getActiveIngredient());
				if (ssCP.length() > 100)
					break;
			}
		}
		;
		this.snapshotCP = ssCP.toString().trim();
	}
}
