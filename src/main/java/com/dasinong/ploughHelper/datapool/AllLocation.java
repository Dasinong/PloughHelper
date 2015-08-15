package com.dasinong.ploughHelper.datapool;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.model.*;

public class AllLocation {

	private static AllLocation allLocation;
	private AllLocation(){
			ILocationDao locationDao = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
	}
	
	public static AllLocation getLocation(){
		if (null == allLocation){
			{
				allLocation = new AllLocation();
			}
		}
		return allLocation;
	}

	//All OwnerRideInfo reference can be directly accessed through RID
	//public Hashtable<Integer,OwnerRideInfo> _availRides;  //All available rides. Represent by RID.

    public List<Location> get_allLoc() {
		return _allLoc;
	}

	public void set_allLoc(List<Location> _allLoc) {
		this._allLoc = _allLoc;
	}

	private List<Location> _allLoc;
    
}
