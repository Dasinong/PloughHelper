package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.context.ContextLoader;
import org.xml.sax.SAXException;

import com.dasinong.ploughHelper.util.Env;
import com.dasinong.ploughHelper.util.SmsService;

public class AllAgriDisForcast implements IWeatherBuffer {

	private static AllAgriDisForcast alladf;

	public static AllAgriDisForcast getadf() {
		if (alladf == null) {
			// alladf = new AllAgriDisForcast();
			alladf = (AllAgriDisForcast) ContextLoader.getCurrentWebApplicationContext().getBean("alladf");
			return alladf;
		} else {
			return alladf;
		}
	}

	private AllAgriDisForcast() {
		_alladf = new HashMap<Integer, AgriDisForcast>();
		try {
			loadContent(latestSourceFile());
		} catch (Exception e) {
			System.out.println("Initialize agriculture disaster forcast failed. " + latestSourceFile());
			SmsService.weatherAlert("Initialize adf failed on " + new Date() + " with file " + latestSourceFile());
		}
	}

	// 自动更新
	@Override
	public void updateContent() {
		updateContent(latestSourceFile());
	}

	// 强制更新
	@Override
	public void updateContent(String sourceFile) {
		HashMap<Integer, AgriDisForcast> oldadf = _alladf;
		_alladf = new HashMap<Integer, AgriDisForcast>();
		try {
			loadContent(sourceFile);
		} catch (Exception e) {
			System.out.println("update agriculture disaster forcast failed. " + e.getCause());
			SmsService.weatherAlert("Update adf failed on " + new Date() + " with file " + sourceFile);
			_alladf = oldadf;
		}
	}

	private String latestSourceFile() {
		String sourceFile;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")) {
			sourceFile = Env.getEnv().WorkingDir
					+ "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/agriculture_forcast_24hours_20150619.txt";
		} else {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			sourceFile = Env.getEnv().WorkingDir + "/data/ftp/agriculture_forcast/agriculture_forcast_24hours_"
					+ df.format(new Date()) + ".txt";
		}
		System.out.println(sourceFile);
		return sourceFile;
	}

	private void loadContent(String sourceFile) throws IOException {
		File file = new File(sourceFile);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));

		StringBuilder notification = new StringBuilder();
		notification.append("load adf on " + new Date() + ". Issue loading: ");

		String tempString = null;
		String[] fields;
		reader.readLine();
		while ((tempString = reader.readLine()) != null) {
			try {
				fields = tempString.trim().split("\t");
				if (fields.length >= 7) {
					AgriDisForcast adf = new AgriDisForcast();
					int areaId = Integer.parseInt(fields[0]);
					adf.setDate(fields[1]);
					adf.setAvgTemp(fields[2]);
					adf.setMaxTemp(fields[3]);
					adf.setMinTemp(fields[4]);
					adf.setWindSpeed(fields[5]);
					adf.setRelativeHumidity(fields[6]);
					if (fields.length == 8)
						adf.setDisasterInfo(fields[7]);
					else
						adf.setDisasterInfo("");
					_alladf.put(areaId, adf);
				} else {
					System.out.println("skip line :" + tempString);
					notification.append(fields[0] + " ");
				}
			} catch (NumberFormatException e) {
				System.out.println("parse line " + tempString + " failed.");
				notification.append(tempString.substring(0, Math.min(tempString.length(), 10)) + " ");
			}
		}
		String sms = notification.substring(0, Math.min(notification.length(), SmsService.maxLength));
		SmsService.weatherAlert(sms);
		reader.close();
	}

	private HashMap<Integer, AgriDisForcast> _alladf;

	public AgriDisForcast getadf(Integer areaId) {
		return _alladf.get(areaId);
	}

	@Override
	public String latestUpdate() {
		AgriDisForcast adf = this._alladf.get(101200805);
		if (adf != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
			return df.format(adf.date);
		} else
			return "No data found. Check whether initialize failed.";
	}

	public static void main(String[] args)
			throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException {
		System.out.println(System.getProperty("OS"));
		Iterator<Entry<Integer, AgriDisForcast>> iter = AllAgriDisForcast.getadf()._alladf.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey() + ": ");
			System.out.println(((AgriDisForcast) entry.getValue()).getAvgTemp() + " DisInfo : "
					+ ((AgriDisForcast) entry.getValue()).getDisasterInfo());
		}
	}
}
