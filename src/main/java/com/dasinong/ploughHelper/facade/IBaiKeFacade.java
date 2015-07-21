package com.dasinong.ploughHelper.facade;

public interface IBaiKeFacade {

	public abstract Object getCPProductById(Long id);

	public abstract Object getVarietyById(Long id);

	public abstract Object getPetDisSpecById(Long id);

	Object getCropByType(String type);

	Object browseVarietyByCropId(Long cropId);

	Object browseCPProductByModel(String model);

	Object browsePetDisByType(String type);

	Object getVarietysByName(String name);

	Object getCPProdcutsByIngredient(String ingredient);

}