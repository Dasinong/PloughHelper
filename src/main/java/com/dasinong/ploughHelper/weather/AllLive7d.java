package com.dasinong.ploughHelper.weather;

import java.io.IOException;
import java.util.Set;
import java.text.ParseException;
import java.util.HashMap;

public class AllLive7d {
	private static AllLive7d allLive7d;
	
	public static AllLive7d getAllLive7d() throws IOException, ParseException, InterruptedException{
		if (allLive7d==null){
			allLive7d = new AllLive7d();
			return allLive7d;
		}
		else{
			return allLive7d;
		}
	}
	private AllLive7d() throws IOException, ParseException, InterruptedException{
		_allLive7d = new HashMap<Integer,Live7dFor>();
		loadContent();
	}
	
    public void updateContent() throws IOException, ParseException{
     	HashMap<Integer,Live7dFor> oldLive7d = _allLive7d;
    	_allLive7d = new HashMap<Integer,Live7dFor>();
		try{
			loadContent();
		}
		catch(Exception e){
			System.out.println("update Live7d failed. " +  e.getCause());
			_allLive7d = oldLive7d;
		}
    }
	
	private void loadContent() throws IOException, ParseException, InterruptedException {
		Set<Integer> locations  = AllLocation.getLocation()._allLocation.keySet();
		GetLive7d glw = new GetLive7d();
		for(Integer code : locations){
			glw.setAreaId(code.toString());
			Live7dFor result = glw.getLive7d();
			_allLive7d.put(code,result);
		}
	}

	private HashMap<Integer,Live7dFor> _allLive7d;
	
	public Live7dFor getLive7d(Integer aid){
		
		return _allLive7d.get(aid);
	}
	

	
	public static void main(String[] args) throws IOException, ParseException{
		/*
		Iterator iter= AllLive7d.getAllLive7d()._allLive7d.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
		AllLive7d.getAllLive7d().getLive7d(101090301);
		*/
	}
}
