package com.dasinong.ploughHelper.weather;

import java.io.IOException;
import java.util.Set;

import com.dasinong.ploughHelper.datapool.AllMonitorLocation;

import java.text.ParseException;
import java.util.HashMap;

public class AllLiveWeather {
	private static AllLiveWeather allLiveWeather;

	public static AllLiveWeather getAllLiveWeather() throws InterruptedException {
		if (allLiveWeather == null) {
			allLiveWeather = new AllLiveWeather();
			return allLiveWeather;
		} else {
			return allLiveWeather;
		}

	}

	private AllLiveWeather() throws InterruptedException {
		_allLiveWeather = new HashMap<Long, LiveWeatherData>();
		loadContent();
	}

	public void updateContent() throws IOException, ParseException {
		HashMap<Long, LiveWeatherData> oldLiveWeather = _allLiveWeather;
		_allLiveWeather = new HashMap<Long, LiveWeatherData>();
		try {
			loadContent();
		} catch (Exception e) {
			System.out.println("update LiveWeather failed. " + e.getCause());
			_allLiveWeather = oldLiveWeather;
		}
	}

	private void loadContent() throws InterruptedException {
		Set<Long> locations = AllMonitorLocation.getInstance()._allLocation.keySet();
		GetLiveWeather glw = new GetLiveWeather();
		for (Long code : locations) {
			glw.setAreaId(code.toString());
			LiveWeatherData result = glw.getLiveWeather();
			_allLiveWeather.put(code, result);
		}
	}

	private HashMap<Long, LiveWeatherData> _allLiveWeather;

	public LiveWeatherData getLiveWeather(Integer aid) {

		return _allLiveWeather.get(aid);
	}

	public static void main(String[] args) throws IOException, ParseException {
		/*
		 * Iterator iter=
		 * AllLiveWeather.getAllLiveWeather()._allLiveWeather.entrySet().
		 * iterator(); while(iter.hasNext()){ Map.Entry entry = (Map.Entry)
		 * iter.next(); System.out.print(entry.getKey()+": ");
		 * System.out.println(entry.getValue()); }
		 * AllLiveWeather.getAllLiveWeather().getLiveWeather(101090301);
		 */
	}
}
