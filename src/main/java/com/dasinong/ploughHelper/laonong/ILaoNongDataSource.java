package com.dasinong.ploughHelper.laonong;

import java.util.List;

import com.dasinong.ploughHelper.model.User;

public interface ILaoNongDataSource {

	public List<LaoNong> genLaoNongs(User user, Long areaId);
	
}
