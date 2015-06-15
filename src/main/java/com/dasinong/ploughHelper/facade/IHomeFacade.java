package com.dasinong.ploughHelper.facade;

import org.springframework.transaction.annotation.Transactional;

import com.dasinong.ploughHelper.model.User;

public interface IHomeFacade {

	@Transactional
	public abstract Object LoadHome(User user, String fieldId);

}