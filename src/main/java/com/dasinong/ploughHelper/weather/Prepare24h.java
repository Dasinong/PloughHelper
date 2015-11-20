package com.dasinong.ploughHelper.weather;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dasinong.ploughHelper.datapool.AllMonitorLocation;
import com.dasinong.ploughHelper.util.Env;
import com.dasinong.ploughHelper.util.WISHourWeather;

public class Prepare24h {
	public void update24hFile() {
		String basefolder = "";
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")) {
			// basefolder =
			// Env.getEnv().WorkingDir+"/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/current";
			basefolder = "E:/weather/" + df.format(date);
		} else {
			basefolder = Env.getEnv().WorkingDir + "/data/weather/hour/" + df.format(date);
		}
		WISHourWeather wis = new WISHourWeather("0", "hforecast");
		File file = new File(basefolder);
		file.mkdir();
		// for( int id : HourCity.HourCity){
		try {
			for (Long id : AllMonitorLocation.getInstance()._allLocation.keySet()) {
				wis.setAreaId("" + id);
				String result = wis.Commute();
				try {
					file = new File(basefolder + "/" + id);
					if (!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(result);
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Prepare24h p = new Prepare24h();
		p.update24hFile();
	}
}
